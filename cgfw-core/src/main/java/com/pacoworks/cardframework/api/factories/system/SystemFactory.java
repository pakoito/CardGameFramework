
package com.pacoworks.cardframework.api.factories.system;

import com.artemis.Aspect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pacoworks.cardframework.api.CFWConstants;
import com.pacoworks.cardframework.api.model.CFWSystem;
import com.pacoworks.cardframework.components.Player;
import com.pacoworks.cardframework.components.UnpackedComponent;
import com.pacoworks.cardframework.systems.BasePhaseSystem;
import lombok.NonNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Paco on 14/12/2014. See LICENSE.md
 */
public class SystemFactory {
    private SystemFactory() {
    }

    public static BasePhaseSystem create(@NonNull URL filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CFWSystem cfwSystem = mapper.readValue(filePath, CFWSystem.class);
        return create(cfwSystem);
    }

    public static BasePhaseSystem create(@NonNull File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CFWSystem cfwSystem = mapper.readValue(file, CFWSystem.class);
        return create(cfwSystem);
    }

    public static BasePhaseSystem create(@NonNull CFWSystem system){
        SystemPrototype systemPrototype = new SystemPrototype(getAspectForString(system.getTargetEntities()));
        systemPrototype.setCFWSystem(system);
        return systemPrototype;
    }

    private static Aspect getAspectForString(@NonNull String targetEntities){
        if (CFWConstants.PlayerGroups.ALL_PLAYERS.equals(targetEntities)){
            return Aspect.getAspectForAll(Player.class, UnpackedComponent.class);
        }
        if (CFWConstants.PlayerGroups.SELECTED_PLAYER.equals(targetEntities)){
            return Aspect.getAspectForAll(Player.class, UnpackedComponent.class);
        }
        return Aspect.getEmpty();
    }
}
