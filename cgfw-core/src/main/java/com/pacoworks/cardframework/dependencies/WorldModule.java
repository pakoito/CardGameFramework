
package com.pacoworks.cardframework.dependencies;

import com.artemis.EntitySystem;
import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.managers.GroupManager;
import com.artemis.managers.PlayerManager;
import com.artemis.managers.TagManager;
import com.artemis.managers.TeamManager;
import com.pacoworks.cardframework.eventbus.EventCommander;
import com.pacoworks.cardframework.systems.BasePhaseSystem;
import com.pacoworks.cardframework.systems.GameSystem;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import java.util.List;

/**
 * Created by Paco on 23/11/2014. See LICENSE.md
 */
@Module
public class WorldModule {
    private final List<? extends BasePhaseSystem> phaseSystems;

    private final List<? extends EntitySystem> otherSystems;

    public WorldModule(List<? extends BasePhaseSystem> phaseSystems,
            List<? extends EntitySystem> otherSystems) {
        this.phaseSystems = phaseSystems;
        this.otherSystems = otherSystems;
    }

    @Provides
    @Singleton
    World provideWorld(GameSystem gameSystem, EventCommander commander) {
        World world = new World(new WorldConfiguration().register(commander));
        world.setManager(new GroupManager());
        world.setManager(new TagManager());
        world.setManager(new TeamManager());
        world.setManager(new PlayerManager());
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
