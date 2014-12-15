
package com.pacoworks.cardframework.api.model.actions;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.TagManager;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pacoworks.cardframework.api.CFWConstants;
import com.pacoworks.cardframework.api.CFWContext;
import lombok.ToString;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "target"
})
@ToString
public class SetActiveAction extends CFWAction {
    public static final String ACTION_NAME = "set_active";

    @JsonProperty("target")
    private String target;

    private Entity oldWinner;

    @Override
    public void doAction(CFWContext cfwContext, boolean passesConditions) {
        if (!passesConditions){
            return;
        }
        World world = cfwContext.getWorld();
        TagManager tagManager = world.getManager(TagManager.class);
        oldWinner = tagManager.getEntity(CFWConstants.PlayerGroups.SELECTED_PLAYER);
        Entity currentEntity = tagManager.getEntity(target);
        tagManager.register(CFWConstants.PlayerGroups.SELECTED_PLAYER, currentEntity);
    }

    @Override
    public void undoAction(CFWContext cfwContext) {
        World world = cfwContext.getWorld();
        TagManager tagManager = world.getManager(TagManager.class);
        tagManager.register(CFWConstants.PlayerGroups.SELECTED_PLAYER, oldWinner);
    }
}
