--
-- Created by IntelliJ IDEA.
-- User: Paco
-- Date: 21/09/2014
-- Time: 14:05
-- License: See LICENSE.md
--
jframe = luajava.bindClass("Thing")
frame = luajava.newInstance("Thing", "Thing");
frame:hello()

