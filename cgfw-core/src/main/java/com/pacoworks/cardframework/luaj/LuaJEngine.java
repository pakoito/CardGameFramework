
package com.pacoworks.cardframework.luaj;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ResourceFinder;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

import com.pacoworks.cardframework.eventbus.EventCommander;

/**
 * Created by Paco on 20/09/2014. See LICENSE.md
 */
@Slf4j
@RequiredArgsConstructor(staticName = "create")
public final class LuaJEngine implements ResourceFinder {
    private final String basePath;

    private final boolean debug;

    private final EventCommander commander;

    @Getter(lazy = true, value = AccessLevel.PROTECTED)
    private final LuaValue luaCommander = startCommander();

    @Getter(lazy = true)
    private final Globals globals = startGlobals();

    public void loadScript(String fileName) {
        getGlobals().loadfile(fileName).call(getLuaCommander());
    }

    public void loadScript(String fileName, String method, Object parameter1) {
        getGlobals().finder = this;
        LuaValue luaParameters = CoerceJavaToLua.coerce(parameter1);
        getGlobals().loadfile(fileName).call(getLuaCommander(), luaParameters);
    }

    public void loadScript(String fileName, String method, Object parameter1, Object parameter2) {
        getGlobals().finder = this;
        LuaValue luaParameters1 = CoerceJavaToLua.coerce(parameter1);
        LuaValue luaParameters2 = CoerceJavaToLua.coerce(parameter2);
        getGlobals().loadfile(fileName).call(getLuaCommander(), luaParameters1, luaParameters2);
    }

    public void loadScript(String fileName, String method, String stringParameter) {
        getGlobals().loadfile(fileName).call(getLuaCommander(), LuaValue.valueOf(stringParameter));
    }

    public void loadScript(String fileName, String method, Object... parameters) {
        LuaValue luaParameters = CoerceJavaToLua.coerce(parameters);
        getGlobals().loadfile(fileName).call(luaParameters);
    }

    public void loadMethodScript(String fileName, String method, Object parameter1) {
        getGlobals().finder = this;
        LuaValue luaParameters = CoerceJavaToLua.coerce(parameter1);
        getGlobals().loadfile(fileName).call(getLuaCommander(), luaParameters);
    }

    public void loadMethodScript(String fileName, String method, Object parameter1,
            Object parameter2) {
        getGlobals().finder = this;
        LuaValue luaParameters1 = CoerceJavaToLua.coerce(parameter1);
        LuaValue luaParameters2 = CoerceJavaToLua.coerce(parameter2);
        getGlobals().loadfile(fileName).call(getLuaCommander(), luaParameters1, luaParameters2);
    }

    public void loadMethodScript(String fileName, String method, String stringParameter) {
        getGlobals().loadfile(fileName).call(getLuaCommander(), LuaValue.valueOf(stringParameter));
    }

    public void loadMethodScript(String fileName, String method, Object... parameters) {
        LuaValue luaParameters = CoerceJavaToLua.coerce(parameters);
        getGlobals().loadfile(fileName).call(luaParameters);
    }

    @Override
    public InputStream findResource(String filename) {
        try {
            return new FileInputStream(new File(basePath + "/" + filename));
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    private Globals startGlobals() {
        Globals newGlobals = (debug) ? JsePlatform.debugGlobals() : JsePlatform.standardGlobals();
        newGlobals.finder = this;
        return newGlobals;
    }

    private LuaValue startCommander() {
        return CoerceJavaToLua.coerce(commander);
    }
}
