
package com.pacoworks.cardframework.api.values;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "type", "value"
})
public class NumericalValue extends CFWValue implements ICFWNumericalValue {
    @JsonProperty("value")
    private float value;

    /**
     * @return The value
     */
    @JsonProperty("value")
    public Float getValue() {
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
