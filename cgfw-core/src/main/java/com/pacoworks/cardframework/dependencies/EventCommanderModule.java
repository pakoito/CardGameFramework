
package com.pacoworks.cardframework.dependencies;

import com.pacoworks.cardframework.eventbus.EventCommander;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Paco on 23/11/2014. See LICENSE.md
 */
@Module
public class EventCommanderModule {
    @Provides
    @Singleton
    EventCommander provideEventCommander() {
        return new EventCommander();
    }
}
