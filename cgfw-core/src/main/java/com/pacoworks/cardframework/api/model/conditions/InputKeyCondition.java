
package com.pacoworks.cardframework.api.model.conditions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "type", "value"
})
@ToString
public class InputKeyCondition extends CFWCondition {
    public static final String CONDITION_NAME = "input_key";

    @JsonProperty("value")
    private String value;

    /**
     * @return The value
     */
    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    /**
     * @param value The value
     */
    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean getResult() {
        return Math.random() > 0.5d;
    }
}
