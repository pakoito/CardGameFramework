
package com.pacoworks.cardframework.api.conditions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pacoworks.cardframework.api.values.ICFWNumericalValue;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "type", "left", "right"
})
public class BiggerThanCondition extends CFWCondition {
    public static final String CONDITION_NAME = "bigger_than";

    @JsonProperty("left")
    private ICFWNumericalValue left;

    @JsonProperty("right")
    private ICFWNumericalValue right;

    /**
     * @return The left
     */
    @JsonProperty("left")
    public ICFWNumericalValue getLeft() {
        return left;
    }

    /**
     * @param left The left
     */
    @JsonProperty("left")
    public void setLeft(ICFWNumericalValue left) {
        this.left = left;
    }

    /**
     * @return The right
     */
    @JsonProperty("right")
    public ICFWNumericalValue getRight() {
        return right;
    }

    /**
     * @param right The right
     */
    @JsonProperty("right")
    public void setRight(ICFWNumericalValue right) {
        this.right = right;
    }

    @Override
    public boolean getResult() {
        return false;
    }
}
