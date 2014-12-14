
package com.pacoworks.cardframework.api.constants;

import com.artemis.Component;
import com.pacoworks.cardframework.systems.BasePhaseSystem;

import java.util.HashMap;

/**
 * Created by Paco on 14/12/2014. See LICENSE.md
 */
public class CFWConstants {
    public static class PlayerGroups {
        public static final String ALL_PLAYERS = "all_players";

        public static final String SELECTED_PLAYER = "selected_player";

        public static final String CURRENT_PLAYER = "current_player";

        public static final String UNKNOWN = "";
    }

    // FIXME ugly global
    public static class CustomComponents {
        private static final HashMap<String, Class<? extends Component>> CUSTOM_COMPONENTS = new HashMap<String, Class<? extends Component>>();

        public static void registerComponent(String key, Class<? extends Component> clazz) {
            CUSTOM_COMPONENTS.put(key, clazz);
        }

        public static Class<? extends Component> getComponent(String key) {
            return CUSTOM_COMPONENTS.get(key);
        }
    }

    // FIXME ugly global
    public static class CustomSystems {
        private static final HashMap<String, BasePhaseSystem> CUSTOM_SYSTEMS = new HashMap<String,BasePhaseSystem>();

        public static void registerSystem(String key, BasePhaseSystem system) {
            CUSTOM_SYSTEMS.put(key, system);
        }

        public static BasePhaseSystem getSystem(String key) {
            return CUSTOM_SYSTEMS.get(key);
        }
    }
}
