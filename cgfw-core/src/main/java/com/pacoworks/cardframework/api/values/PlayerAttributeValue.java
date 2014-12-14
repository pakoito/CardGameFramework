
package com.pacoworks.cardframework.api.values;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "type", "target", "attribute"
})
@ToString
public class PlayerAttributeValue extends CFWValue {
    public static final String VALUE_NAME = "player_attribute";

    @JsonProperty("target")
    private String target;

    @JsonProperty("value")
    private String attribute;

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
     * @return The value
     */
    @JsonProperty("attribute")
    public String getAttribute() {
        return attribute;
    }

    /**
     * @param value The value
     */
    @JsonProperty("attribute")
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public Float getValue() {
        return 0f;
    }
}
