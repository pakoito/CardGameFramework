
package com.pacoworks.cardframework.dependencies;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.managers.*;
import com.pacoworks.cardframework.eventbus.IEventCommander;
import com.pacoworks.cardframework.systems.GameSystem;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Paco on 23/11/2014. See LICENSE.md
 */
@Module
public class WorldModule {

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
        return world;
    }
}
