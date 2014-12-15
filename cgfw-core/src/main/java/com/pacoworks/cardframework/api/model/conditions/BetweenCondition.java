
package com.pacoworks.cardframework.api.model.conditions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pacoworks.cardframework.api.CFWContext;
import com.pacoworks.cardframework.api.model.values.ICFWValue;
import lombok.ToString;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "value", "ceil_ex", "floor_ex"
})
@ToString
public class BetweenCondition extends CFWCondition {
    public static final String CONDITION_NAME = "between";

    @JsonProperty("value")
    private ICFWValue value;

    @JsonProperty("ceil_ex")
    private List<ICFWValue> ceilEx = new ArrayList<ICFWValue>();

    @JsonProperty("floor_ex")
    private List<ICFWValue> floorEx = new ArrayList<ICFWValue>();

    /**
     * @return The value
     */
    @JsonProperty("value")
    public ICFWValue getValue() {
        return value;
    }

    /**
     * @param value The value
     */
    @JsonProperty("value")
    public void setValue(ICFWValue value) {
        this.value = value;
    }

    /**
     * @return The ceilEx
     */
    @JsonProperty("ceil_ex")
    public List<ICFWValue> getCeilEx() {
        return ceilEx;
    }

    /**
     * @param ceilEx The ceil_ex
     */
    @JsonProperty("ceil_ex")
    public void setCeilEx(List<ICFWValue> ceilEx) {
        this.ceilEx = ceilEx;
    }

    /**
     * @return The floorEx
     */
    @JsonProperty("floor_ex")
    public List<ICFWValue> getFloorEx() {
        return floorEx;
    }

    /**
     * @param floorEx The floor_ex
     */
    @JsonProperty("floor_ex")
    public void setFloorEx(List<ICFWValue> floorEx) {
        this.floorEx = floorEx;
    }

    @Override
    public boolean getResult(CFWContext cfwContext) {
        if (value == null) {
            return false;
        }
        for (ICFWValue floor: floorEx){
            if (value.getValue(cfwContext) < floor.getValue(cfwContext)) {
                return false;
            }
        }
        for (ICFWValue ceil: ceilEx){
            if (value.getValue(cfwContext) > ceil.getValue(cfwContext)) {
                return false;
            }
        }
        return true;
    }
}
