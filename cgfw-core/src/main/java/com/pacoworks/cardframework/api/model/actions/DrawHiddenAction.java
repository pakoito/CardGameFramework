
package com.pacoworks.cardframework.api.model.actions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pacoworks.cardframework.api.model.values.ICFWValue;
import lombok.ToString;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "type", "target", "amount"
})
@ToString
public class DrawHiddenAction extends CFWAction {
    public static final String ACTION_NAME = "draw_hidden";

    @JsonProperty("target")
    private String target;

    @JsonProperty("amount")
    private ICFWValue amount;

    /**
     * @return The target
     */
    @JsonProperty("target")
    public String getTarget() {
        return target;
    }

    /**
     * @param target The target
     */
    @JsonProperty("target")
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * @return The amount
     */
    @JsonProperty("amount")
    public ICFWValue getAmount() {
        return amount;
    }

    /**
     * @param amount The amount
     */
    @JsonProperty("amount")
    public void setAmount(ICFWValue amount) {
        this.amount = amount;
    }

    @Override
    public void doAction(boolean passesConditions) {
        if (!passesConditions){
            return;
        }
    }

    @Override
    public void undoAction() {

    }
}
