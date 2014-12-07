
package com.pacoworks.cardframework.dependencies;

import com.pacoworks.cardframework.eventbus.IEventCommander;
import com.pacoworks.cardframework.systems.GameSystem;
import com.pacoworks.cardframework.systems.IVictoryDecider;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Paco on 23/11/2014. See LICENSE.md
 */
@Module
public class GameSystemModule {
    private final IVictoryDecider victoryChecker;

    private final IEventCommander commander;

    public GameSystemModule(IVictoryDecider victoryChecker, IEventCommander commander) {
        this.victoryChecker = victoryChecker;
        this.commander = commander;
    }

    @Provides
    @Singleton
    GameSystem provideGameSystem() {
        return new GameSystem(victoryChecker);
    }

    @Provides
    @Singleton
    IEventCommander provideCommander() {
        return commander;
    }
}
