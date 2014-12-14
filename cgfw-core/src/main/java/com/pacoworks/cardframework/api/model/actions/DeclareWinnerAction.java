
package com.pacoworks.cardframework.api.model.actions;

import com.artemis.Entity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "target", "condition"
})
@ToString
public class DeclareWinnerAction extends CFWAction {
        public static final String ACTION_NAME = "declare_winner";

        @Override
        public void doAction(boolean passesConditions, Entity entity) {

        }

        @Override
        public void undoAction() {

        }
}
