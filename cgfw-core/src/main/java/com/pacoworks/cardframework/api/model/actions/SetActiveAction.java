
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
public class SetActiveAction extends CFWAction {
    public static final String ACTION_NAME = "set_active";

    @JsonProperty("target")
    private String target;

    private Entity oldWinner;

    @Override
    public void doAction(boolean passesConditions, Entity entity) {
        World world = WorldFetcher.fetchWorld();
        TagManager tagManager = world.getManager(TagManager.class);
        oldWinner = tagManager.getEntity(CFWConstants.PlayerGroups.SELECTED_PLAYER);
        tagManager.register(CFWConstants.PlayerGroups.SELECTED_PLAYER, entity);
    }

    @Override
    public void undoAction() {
        World world = WorldFetcher.fetchWorld();
        TagManager tagManager = world.getManager(TagManager.class);
        tagManager.register(CFWConstants.PlayerGroups.SELECTED_PLAYER, oldWinner);
    }
}
