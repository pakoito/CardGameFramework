
package com.pacoworks.cardframework.framework;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.pacoworks.cardframework.eventbus.EventCommander;
import com.pacoworks.cardframework.eventbus.events.EventGameEnded;
import com.pacoworks.cardframework.eventbus.events.EventGameProcessed;
import com.pacoworks.cardframework.eventbus.events.EventGameStarted;
import com.pacoworks.cardframework.luaj.LuaJEngine;
import com.pacoworks.cardframework.systems.BasePhaseSystem;
import com.pacoworks.cardframework.systems.GameSystem;

/**
 * Created by Paco on 20/09/2014.
 */
@Slf4j
public class CardgameFramework {
    private Entity mGame;

    private boolean isStarted = false;

    private GameSystem mGameSystem;

    @Getter
    @Accessors(prefix = "m")
    private World mWorld;

    @Getter
    @Accessors(prefix = "m")
    private EventCommander mCommander;

    @Getter
    @Accessors(prefix = "m")
    private LuaJEngine mLuaEngine;

    public void start(EventCommander eventCommander, GameSystem gameSystem,
            BasePhaseSystem startingPhase, String sriptsPath, boolean debuggableScripts) {
        this.mGameSystem = gameSystem;
        mCommander = eventCommander;
        mLuaEngine = LuaJEngine.create(sriptsPath, debuggableScripts, mCommander);
        mWorld = new World();
        mWorld.setManager(new GroupManager());
        mWorld.setManager(new TagManager());
        mWorld.initialize();
        mWorld.inject(mCommander);
        mWorld.setSystem(gameSystem);
        mWorld.initialize();
        EntityFactory.createGame(mWorld, startingPhase);
        isStarted = true;
        mCommander.postAnyEvent(EventGameStarted.create());
        log.info("CardFramework started");
    }

    public void process() throws IllegalStateException {
        if (!isStarted) {
            throw new IllegalStateException("CardFramework not started.");
        }
        mWorld.process();
        mCommander.postAnyEvent(EventGameProcessed.create());
    }

    public void end() {
        mCommander.postAnyEvent(EventGameEnded.create());
        isStarted = false;
        mWorld.dispose();
        mWorld = null;
        mCommander = null;
        log.info("CardFramework ended");
    }
}
