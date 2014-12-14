
package com.pacoworks.cardframework.api.model.actions;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.TagManager;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pacoworks.cardframework.api.constants.CFWConstants;
import com.pacoworks.cardframework.api.util.WorldFetcher;
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
    public void doAction(boolean passesConditions, Entity entity) {
        World world = WorldFetcher.fetchWorld();
        TagManager tagManager = world.getManager(TagManager.class);
        oldWinner = tagManager.getEntity(CFWConstants.PlayerGroups.WINNER_PLAYER);
        Entity selectedEntity = entity;
        if (CFWConstants.PlayerGroups.SELECTED_PLAYER.equals(target)) {
            selectedEntity = tagManager.getEntity(CFWConstants.PlayerGroups.SELECTED_PLAYER);
        }
        tagManager.register(CFWConstants.PlayerGroups.WINNER_PLAYER, selectedEntity);
    }

    @Override
    public void undoAction() {
        World world = WorldFetcher.fetchWorld();
        TagManager tagManager = world.getManager(TagManager.class);
        tagManager.register(CFWConstants.PlayerGroups.WINNER_PLAYER, oldWinner);
    }
}
