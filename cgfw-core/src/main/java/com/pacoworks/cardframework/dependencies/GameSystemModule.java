
package com.pacoworks.cardframework.dependencies;

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

    public GameSystemModule(IVictoryDecider victoryChecker) {
        this.victoryChecker = victoryChecker;
    }

    @Provides
    @Singleton
    GameSystem provideGameSystem() {
        return new GameSystem(victoryChecker);
    }
}
