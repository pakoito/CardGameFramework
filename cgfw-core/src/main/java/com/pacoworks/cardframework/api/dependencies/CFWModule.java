
package com.pacoworks.cardframework.api.dependencies;

import com.artemis.World;
import com.pacoworks.cardframework.api.CFWContext;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Paco on 23/11/2014. See LICENSE.md
 */
@Module
public class CFWModule {
    @Provides
    @Singleton
    CFWContext provideContext(World world) {
        return new CFWContext(world);
    }
}
