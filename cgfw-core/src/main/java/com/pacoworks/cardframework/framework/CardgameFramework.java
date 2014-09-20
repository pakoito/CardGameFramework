
package com.pacoworks.cardframework.framework;

import java.util.concurrent.ScheduledExecutorService;

import lombok.Getter;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.pacoworks.cardframework.systems.BasePhaseSystem;
import com.pacoworks.cardframework.systems.GameSystem;

/**
 * Created by Paco on 20/09/2014.
 */
public class CardgameFramework {
    @Getter
    private World world;

    private Entity game;

    private boolean isStarted = false;

    private GameSystem gameSystem;

    private ScheduledExecutorService executorService;

    public void start(GameSystem gameSystem, BasePhaseSystem phaseSystems) {
        this.gameSystem = gameSystem;
        world = new World();
        world.setManager(new GroupManager());
        world.setManager(new TagManager());
        world.initialize();
        world.setSystem(gameSystem);
        world.initialize();
        EntityFactory.createGame(world, phaseSystems);
        isStarted = true;
    }

    public void process() throws IllegalStateException {
        if (!isStarted) {
            throw new IllegalStateException("Framework not started.");
        }
        world.process();
    }

    public void end() {
        isStarted = false;
        world.dispose();
        world = null;
    }
}
