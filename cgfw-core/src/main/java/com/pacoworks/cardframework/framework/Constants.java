
package com.pacoworks.cardframework.framework;

import java.util.HashMap;

/**
 * Created by Paco on 20/09/2014. See LICENSE.md
 */
public final class Constants {
    static {
        Tags.PLAYER_CHARACTERS.put(1, "dagger");
        Tags.PLAYER_CHARACTERS.put(2, "scyte");
        Tags.PLAYER_CHARACTERS.put(3, "axe");
        Tags.PLAYER_CHARACTERS.put(4, "mace");
        Tags.PLAYER_CHARACTERS_BY_NAME.put("dagger", 1);
        Tags.PLAYER_CHARACTERS_BY_NAME.put("scyte", 2);
        Tags.PLAYER_CHARACTERS_BY_NAME.put("axe", 3);
        Tags.PLAYER_CHARACTERS_BY_NAME.put("mace", 4);
    }

    public static class Tags {
        public static final String GAME = "Game";

        public static final HashMap<Integer, String> PLAYER_CHARACTERS = new HashMap<Integer, String>();

        public static final HashMap<String, Integer> PLAYER_CHARACTERS_BY_NAME = new HashMap<String, Integer>();
    }

    public class Groups {
        public static final String FRAMEWORK = "F";

        public static final String CARD = "C";

        public static final String PLAYER = "P";
    }
}
