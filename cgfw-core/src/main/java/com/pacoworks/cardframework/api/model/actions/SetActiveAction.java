
package com.pacoworks.cardframework.api.model.actions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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

    @Override
    public void doAction(boolean passesConditions) {
    }

    @Override
    public void undoAction() {
    }
}
