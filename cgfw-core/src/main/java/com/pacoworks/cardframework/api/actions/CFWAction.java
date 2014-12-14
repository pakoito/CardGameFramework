
package com.pacoworks.cardframework.api.actions;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "type"
})
public abstract class CFWAction implements ICFWAction {
    @JsonProperty("type")
    private String type;

    @JsonProperty("condition")
    private List<Condition> condition = new ArrayList<Condition>();

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The condition
     */
    @JsonProperty("condition")
    public List<Condition> getCondition() {
        return condition;
    }

    /**
     * @param condition The condition
     */
    @JsonProperty("condition")
    public void setCondition(List<Condition> condition) {
        this.condition = condition;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
