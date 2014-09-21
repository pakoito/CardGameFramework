
package com.pacoworks.cardframework.luaj;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ResourceFinder;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

/**
 * Created by Paco on 21/09/2014.
 */
@Slf4j
@RequiredArgsConstructor(staticName = "create")
public class LuaJEngine implements ResourceFinder {
    private final String basePath;

    private final boolean debug;

    public void loadScript(String fileName) {
        Globals globals = getGlobals();
        globals.finder = this;
        globals.loadfile(fileName).call();
    }

    public void loadMethodScript(String fileName, String method, Object parameter1) {
        Globals globals = getGlobals();
        globals.finder = this;
        LuaValue luaParameters = CoerceJavaToLua.coerce(parameter1);
        globals.loadfile(fileName).call(luaParameters);
    }

    public void loadMethodScript(String fileName, String method, Object parameter1,
            Object parameter2) {
        Globals globals = getGlobals();
        globals.finder = this;
        LuaValue luaParameters1 = CoerceJavaToLua.coerce(parameter1);
        LuaValue luaParameters2 = CoerceJavaToLua.coerce(parameter2);
        globals.loadfile(fileName).call(luaParameters1, luaParameters2);
    }

    public void loadMethodScript(String fileName, String method, Object parameter1,
            Object parameter2, Object parameter3) {
        Globals globals = getGlobals();
        globals.finder = this;
        LuaValue luaParameters1 = CoerceJavaToLua.coerce(parameter1);
        LuaValue luaParameters2 = CoerceJavaToLua.coerce(parameter2);
        LuaValue luaParameters3 = CoerceJavaToLua.coerce(parameter3);
        globals.loadfile(fileName).call(luaParameters1, luaParameters2, luaParameters3);
    }

    public void loadMethodScript(String fileName, String method, String stringParameter) {
        Globals globals = getGlobals();
        globals.finder = this;
        globals.loadfile(fileName).call(stringParameter);
    }

    public void loadMethodScript(String fileName, String method, Object... parameters) {
        Globals globals = getGlobals();
        globals.finder = this;
        LuaValue luaParameters = CoerceJavaToLua.coerce(this);
        globals.loadfile(fileName).call(luaParameters);
    }

    @Override
    public InputStream findResource(String filename) {
        try {
            return new FileInputStream(new File(basePath + "/" + filename));
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    private Globals getGlobals() {
        return (debug) ? JsePlatform.debugGlobals() : JsePlatform.standardGlobals();
    }
}
