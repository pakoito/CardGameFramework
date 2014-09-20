
package com.pacoworks.cardframework.framework;

import com.artemis.Entity;
import com.artemis.World;
import com.pacoworks.cardframework.systems.BasePhaseSystem;

/**
 * Created by Paco on 20/09/2014.
 */
public class CardgameFramework {
    private World world;

    private Entity game;

    public void start(BasePhaseSystem phaseSystems) {
        world = new World();
        EntityFactory.createGame(world, phaseSystems);
    }
}
