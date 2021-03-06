
package com.pacoworks.cardframework.api.model.values;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pacoworks.cardframework.api.CFWContext;
import lombok.ToString;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "type", "value"
})
@ToString
public class NumericalValue extends CFWValue {
    public static final String VALUE_NAME = "numerical";

    @JsonProperty("value")
    private float value;

    /**
     * @return The value
     * @param cfwContext
     */
    @JsonProperty("value")
    public Float getValue(CFWContext cfwContext) {
        return value;
    }

    /**
     * @param value The value
     */
    @JsonProperty("value")
    public void setValue(float value) {
        this.value = value;
    }
}
