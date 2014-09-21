
package com.pacoworks.cardframework.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ResourceFinder;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

import com.pacoworks.cardframework.eventbus.OttoBus;
import com.squareup.otto.Subscribe;

/**
 * Created by Paco on 20/09/2014.
 */
public class Stuff implements ResourceFinder {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter script name");
//        String script = keyboard.nextLine();
        String script = "main.lua";
        Stuff event = new Stuff();
        OttoBus.getBus().register(event);
        OttoBus.getBus().post(script);
    }

    @Subscribe
    public void myStuff(String script) {
        System.out.println("Opening " + script);
        Globals globals = JsePlatform.debugGlobals();
        globals.finder = this;
        LuaValue viewobj = CoerceJavaToLua.coerce(this);
        globals.loadfile(script).call(viewobj);
    }

    public void hello(String str){
        System.out.println(str);
    }

    @Override
    public InputStream findResource(String filename) {
        try {
            return new FileInputStream(new File(filename));
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
