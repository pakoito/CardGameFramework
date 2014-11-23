
package com.pacoworks.cardframework.dependencies;

import com.pacoworks.cardframework.eventbus.EventCommander;
import com.pacoworks.cardframework.luaj.LuaJEngine;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by Paco on 23/11/2014. See LICENSE.md
 */
@Module
public class LuaJModule {
    private final String scriptsPath;

    private final boolean debuggableScripts;

    public LuaJModule(String scriptsPath, boolean debuggableScripts) {
        this.scriptsPath = scriptsPath;
        this.debuggableScripts = debuggableScripts;
    }

    @Provides
    @Singleton
    LuaJEngine provideLuaJEngine(EventCommander eventCommander) {
        return LuaJEngine.create((scriptsPath == null) ? "" : scriptsPath, debuggableScripts,
                eventCommander);
    }
}
