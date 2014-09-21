
package com.pacoworks.cardframework.framework;

import lombok.Getter;
import lombok.experimental.Accessors;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.pacoworks.cardframework.eventbus.EventGameEnded;
import com.pacoworks.cardframework.eventbus.EventGameProcessed;
import com.pacoworks.cardframework.eventbus.EventGameStarted;
import com.pacoworks.cardframework.systems.BasePhaseSystem;
import com.pacoworks.cardframework.systems.GameSystem;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by Paco on 20/09/2014.
 */
public class CardgameFramework {
    @Getter
    @Accessors(prefix = "m")
    private World mWorld;

    private Entity mGame;

    private boolean isStarted = false;

    private GameSystem mGameSystem;

    @Getter
    @Accessors(prefix = "m")
    private Bus mEventBus;

    public void start(GameSystem gameSystem, BasePhaseSystem phaseSystems) {
        this.mGameSystem = gameSystem;
        mEventBus = new Bus(ThreadEnforcer.ANY);
        mWorld = new World();
        mWorld.setManager(new GroupManager());
        mWorld.setManager(new TagManager());
        mWorld.initialize();
        mWorld.setSystem(gameSystem);
        mWorld.inject(mEventBus);
        mWorld.initialize();
        EntityFactory.createGame(mWorld, phaseSystems);
        isStarted = true;
        mEventBus.post(EventGameStarted.create());
    }

    public void process() throws IllegalStateException {
        if (!isStarted) {
            throw new IllegalStateException("Framework not started.");
        }
        mWorld.process();
        mEventBus.post(EventGameProcessed.create());
    }

    public void end() {
        mEventBus.post(EventGameEnded.create());
        isStarted = false;
        mWorld.dispose();
        mWorld = null;
        mEventBus = null;
    }
}
