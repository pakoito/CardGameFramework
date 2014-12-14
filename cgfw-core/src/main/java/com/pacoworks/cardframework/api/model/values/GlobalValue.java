
package com.pacoworks.cardframework.api.model.values;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pacoworks.cardframework.api.constants.CFWConstants;
import lombok.ToString;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "type", "name"
})
@ToString
public class GlobalValue extends CFWValue {
    public static final String VALUE_NAME = "global";

    @JsonProperty("name")
    private String name;

    /**
     * @return The value
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * @param value The value
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public Float getValue() {
        return CFWConstants.GlobalValues.getValue(name);
    }
}
