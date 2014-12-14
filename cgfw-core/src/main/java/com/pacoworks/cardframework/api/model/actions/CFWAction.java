
package com.pacoworks.cardframework.api.model.actions;

import com.artemis.Entity;
import com.fasterxml.jackson.annotation.*;
import com.pacoworks.cardframework.api.model.conditions.ICFWCondition;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "type"
})
public abstract class CFWAction implements ICFWAction {
    @JsonProperty("type")
    private String type;

    @JsonProperty("condition")
    private List<ICFWCondition> condition = new ArrayList<ICFWCondition>();

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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public void doAction(Entity entity) {
        boolean passesConditions = true;
        for (ICFWCondition cfwCondition: condition){
            if (!cfwCondition.getResult()){
                passesConditions = false;
                break;
            }
        }
        doAction(passesConditions, entity);
    }

    protected abstract void doAction(boolean passesConditions, Entity entity);

    @Override
    public void undoAction() {

    }
}
