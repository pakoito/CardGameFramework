
package com.pacoworks.cardframework.api.conditions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pacoworks.cardframework.api.values.ICFWNumericalValue;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "value", "ceil_ex", "floor_ex"
})
public class BetweenCondition extends CFWCondition {
    public static final String CONDITION_NAME = "between";

    @JsonProperty("value")
    private ICFWNumericalValue value;

    @JsonProperty("ceil_ex")
    private List<ICFWNumericalValue> ceilEx = new ArrayList<ICFWNumericalValue>();

    @JsonProperty("floor_ex")
    private List<ICFWNumericalValue> floorEx = new ArrayList<ICFWNumericalValue>();

    /**
     * @return The value
     */
    @JsonProperty("value")
    public ICFWNumericalValue getValue() {
        return value;
    }

    /**
     * @param value The value
     */
    @JsonProperty("value")
    public void setValue(ICFWNumericalValue value) {
        this.value = value;
    }

    /**
     * @return The ceilEx
     */
    @JsonProperty("ceil_ex")
    public List<ICFWNumericalValue> getCeilEx() {
        return ceilEx;
    }

    /**
     * @param ceilEx The ceil_ex
     */
    @JsonProperty("ceil_ex")
    public void setCeilEx(List<ICFWNumericalValue> ceilEx) {
        this.ceilEx = ceilEx;
    }

    /**
     * @return The floorEx
     */
    @JsonProperty("floor_ex")
    public List<ICFWNumericalValue> getFloorEx() {
        return floorEx;
    }

    /**
     * @param floorEx The floor_ex
     */
    @JsonProperty("floor_ex")
    public void setFloorEx(List<ICFWNumericalValue> floorEx) {
        this.floorEx = floorEx;
    }

    @Override
    public boolean getResult() {
        return false;
    }
}
