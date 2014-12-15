
package com.pacoworks.cardframework.api.model;

import com.fasterxml.jackson.annotation.*;
import com.pacoworks.cardframework.api.model.actions.ICFWAction;
import com.pacoworks.cardframework.api.model.conditions.ICFWCondition;
import lombok.ToString;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "condition", "action", "next", "priority", "finalize"
})
@ToString
public class CFWState {
    @JsonProperty("condition")
    private List<ICFWCondition> condition = new ArrayList<ICFWCondition>();

    @JsonProperty("action")
    private List<ICFWAction> action = new ArrayList<ICFWAction>();

    @JsonProperty("next")
    private List<String> next = new ArrayList<String>();

    @JsonProperty("priority")
    private Integer priority;

    @JsonProperty("finalize")
    private Boolean isFinalize = false;

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
    public List<ICFWAction> getActions() {
        return action;
    }

    /**
     * @param action The action
     */
    @JsonProperty("action")
    public void setActions(List<ICFWAction> action) {
        this.action = action;
    }

    /**
     * @return The next
     */
    @JsonProperty("next")
    public List<String> getNext() {
        return next;
    }

    /**
     * @param next The next
     */
    @JsonProperty("next")
    public void setNext(List<String> next) {
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

    /**
     * @return The continuity
     */
    @JsonProperty("finalize")
    public Boolean isFinalize() {
        return isFinalize;
    }

    /**
     * @param continueAfter The continuity
     */
    @JsonProperty("finalize")
    public void setFinalize(Boolean finalizeAfter) {
        this.isFinalize = finalizeAfter;
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
