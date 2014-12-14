package com.pacoworks.cardframework.api.util;

import com.pacoworks.cardframework.api.model.components.CFWComponent;

/**
 * Created by Paco on 14/12/2014.
 * See LICENSE.md
 */
public final class NamingUtils {
    private NamingUtils() {
    }
    public static String getNameForComponentClass(Class<? extends CFWComponent> clazz){
        return clazz.getSimpleName().replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase();
    }
}
