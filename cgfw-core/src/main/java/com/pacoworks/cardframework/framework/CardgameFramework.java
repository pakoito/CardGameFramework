
package com.pacoworks.cardframework.framework;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.experimental.Builder;
import lombok.extern.slf4j.Slf4j;

import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.pacoworks.cardframework.eventbus.EventCommander;
import com.pacoworks.cardframework.eventbus.events.EventGameEnded;
import com.pacoworks.cardframework.eventbus.events.EventGameProcessed;
import com.pacoworks.cardframework.eventbus.events.EventGameStarted;
import com.pacoworks.cardframework.luaj.LuaJEngine;
import com.pacoworks.cardframework.systems.BasePhaseSystem;
import com.pacoworks.cardframework.systems.GameSystem;
import com.pacoworks.cardframework.systems.IVictoryDecider;

/**
 * Created by Paco on 20/09/2014. License available in LICENSE.md
 */
@Slf4j
public class CardgameFramework {
    private Entity mGame;

    private AtomicBoolean isStarted = new AtomicBoolean(false);

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

    @Builder(builderClassName = "CFBuilder")
    private CardgameFramework(@NonNull EventCommander eventCommander,
            @NonNull IVictoryDecider victoryChecker, @NonNull List<BasePhaseSystem> phaseSystems,
            List<EntitySystem> otherSystems, String scriptsPath, boolean debuggableScripts) {
        this.mGameSystem = new GameSystem(victoryChecker);
        mCommander = eventCommander;
        mLuaEngine = LuaJEngine.create((scriptsPath == null) ? "" : scriptsPath, debuggableScripts,
                mCommander);
        mWorld = new World(new WorldConfiguration().register(mCommander));
        mWorld.setManager(new GroupManager());
        mWorld.setManager(new TagManager());
        mWorld.setSystem(mGameSystem);
        for (BasePhaseSystem phaseSystem : phaseSystems) {
            mWorld.setSystem(phaseSystem, true);
        }
        if (otherSystems != null) {
            for (EntitySystem system : otherSystems) {
                mWorld.setSystem(system, true);
            }
        }
        mWorld.initialize();
        EntityFactory.createGame(mWorld, phaseSystems.get(0));
        isStarted.set(true);
        mCommander.postAnyEvent(EventGameStarted.create());
        log.info("CardFramework started");
    }

    public void process() throws IllegalStateException {
        if (!isStarted.get()) {
            throw new IllegalStateException("CardFramework not started.");
        }
        mCommander.postAnyEvent(EventGameProcessed.create());
        mWorld.process();
    }

    public void end() {
        isStarted.set(false);
        mCommander.postAnyEvent(EventGameEnded.create());
        mWorld.dispose();
        mWorld = null;
        mCommander = null;
        log.info("CardFramework ended");
    }
}
