
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
        "target", "condition"
})
@ToString
public class SetWinnerAction extends CFWAction {
    public static final String ACTION_NAME = "set_winner";

    public Entity oldWinner;

    @JsonProperty("target")
    private String target;

    @Override
    public void doAction(CFWContext cfwContext, boolean passesConditions) {
        if (!passesConditions){
            return;
        }
        World world = cfwContext.getWorld();
        TagManager tagManager = world.getManager(TagManager.class);
        oldWinner = tagManager.getEntity(CFWConstants.PlayerGroups.WINNER_PLAYER);
        Entity selectedEntity = tagManager.getEntity(target);
        tagManager.register(CFWConstants.PlayerGroups.WINNER_PLAYER, selectedEntity);
    }

    @Override
    public void undoAction(CFWContext cfwContext) {
        World world = cfwContext.getWorld();
        TagManager tagManager = world.getManager(TagManager.class);
        tagManager.register(CFWConstants.PlayerGroups.WINNER_PLAYER, oldWinner);
    }
}
