
package com.pacoworks.cardframework.api;

import com.fasterxml.jackson.annotation.*;
import com.pacoworks.cardframework.api.actions.ICFWAction;
import com.pacoworks.cardframework.api.conditions.ICFWCondition;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "condition", "action", "next", "priority"
})
public class State {
    @JsonProperty("condition")
    private List<ICFWCondition> condition = new ArrayList<ICFWCondition>();

    @JsonProperty("action")
    private List<ICFWAction> action = new ArrayList<ICFWAction>();

    @JsonProperty("next")
    private String next;

    @JsonProperty("priority")
    private Integer priority;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The condition
     */
    @JsonProperty("condition")
    public List<ICFWCondition> getCondition() {
        return condition;
    }

    /**
     * @param condition The condition
     */
    @JsonProperty("condition")
    public void setCondition(List<ICFWCondition> condition) {
        this.condition = condition;
    }

    /**
     * @return The action
     */
    @JsonProperty("action")
    public List<ICFWAction> getAction() {
        return action;
    }

    /**
     * @param action The action
     */
    @JsonProperty("action")
    public void setAction(List<ICFWAction> action) {
        this.action = action;
    }

    /**
     * @return The next
     */
    @JsonProperty("next")
    public String getNext() {
        return next;
    }

    /**
     * @param next The next
     */
    @JsonProperty("next")
    public void setNext(String next) {
        this.next = next;
    }

    /**
     * @return The priority
     */
    @JsonProperty("priority")
    public Integer getPriority() {
        return priority;
    }

    /**
     * @param priority The priority
     */
    @JsonProperty("priority")
    public void setPriority(Integer priority) {
        this.priority = priority;
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
