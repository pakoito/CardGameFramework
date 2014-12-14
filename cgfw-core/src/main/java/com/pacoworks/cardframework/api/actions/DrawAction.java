
package com.pacoworks.cardframework.api.actions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pacoworks.cardframework.api.values.NumericalValue;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "type", "target", "amount"
})
public class DrawAction extends CFWAction {
    public static final String ACTION_NAME = "draw";

    @JsonProperty("target")
    private String target;

    @JsonProperty("amount")
    private NumericalValue amount;

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
    public NumericalValue getAmount() {
        return amount;
    }

    /**
     * @param amount The amount
     */
    @JsonProperty("amount")
    public void setAmount(NumericalValue amount) {
        this.amount = amount;
    }

    @Override
    public void doAction() {
    }

    @Override
    public void undoAction() {
    }
}
