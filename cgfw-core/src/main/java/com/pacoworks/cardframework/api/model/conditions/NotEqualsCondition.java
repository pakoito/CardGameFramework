
package com.pacoworks.cardframework.api.model.conditions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pacoworks.cardframework.api.model.values.ICFWValue;
import lombok.ToString;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "type", "left", "right"
})
@ToString
public class NotEqualsCondition extends CFWCondition {
    public static final String CONDITION_NAME = "not_equals";

    @JsonProperty("left")
    private ICFWValue left;

    @JsonProperty("right")
    private ICFWValue right;

    /**
     * @return The left
     */
    @JsonProperty("left")
    public ICFWValue getLeft() {
        return left;
    }

    /**
     * @param left The left
     */
    @JsonProperty("left")
    public void setLeft(ICFWValue left) {
        this.left = left;
    }

    /**
     * @return The right
     */
    @JsonProperty("right")
    public ICFWValue getRight() {
        return right;
    }

    /**
     * @param right The right
     */
    @JsonProperty("right")
    public void setRight(ICFWValue right) {
        this.right = right;
    }

    @Override
    public boolean getResult() {
        if (left == null || right == null) {
            return false;
        }
        return left.getValue() != right.getValue();
    }
}
