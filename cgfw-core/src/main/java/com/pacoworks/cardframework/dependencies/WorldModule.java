
package com.pacoworks.cardframework.dependencies;

import com.artemis.EntitySystem;
import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.managers.*;
import com.pacoworks.cardframework.eventbus.IEventCommander;
import com.pacoworks.cardframework.systems.BasePhaseSystem;
import com.pacoworks.cardframework.systems.GameSystem;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Paco on 23/11/2014. See LICENSE.md
 */
@Module
public class WorldModule {
    private final Iterable<? extends BasePhaseSystem> phaseSystems;

    private final Iterable<? extends EntitySystem> otherSystems;

    public WorldModule(Iterable<? extends BasePhaseSystem> phaseSystems,
                       Iterable<? extends EntitySystem> otherSystems) {
        this.phaseSystems = phaseSystems;
        this.otherSystems = otherSystems;
    }

    @Provides
    @Singleton
    World provideWorld(GameSystem gameSystem, IEventCommander commander) {
        World world = new World(new WorldConfiguration().register(commander));
        world.setManager(new GroupManager());
        world.setManager(new TagManager());
        world.setManager(new TeamManager());
        world.setManager(new PlayerManager());
        world.setManager(new UuidEntityManager());
        world.setSystem(gameSystem);
        for (BasePhaseSystem phaseSystem : phaseSystems) {
            world.setSystem(phaseSystem, true);
        }
        if (otherSystems != null) {
            for (EntitySystem system : otherSystems) {
                world.setSystem(system, true);
            }
        }
        return world;
    }
}
