
package com.pacoworks.cardframework.api.actions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "target", "condition"
})
public class DeclareWinnerAction extends CFWAction {
        @Override
        public void doAction() {

        }

        @Override
        public void undoAction() {

        }
}
