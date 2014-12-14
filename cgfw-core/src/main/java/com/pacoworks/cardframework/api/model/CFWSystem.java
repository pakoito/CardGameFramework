
package com.pacoworks.cardframework.api.model;

import com.fasterxml.jackson.annotation.*;
import lombok.ToString;

import javax.annotation.Generated;
import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "type", "name", "target_entities", "fsm"
})
@ToString
public class CFWSystem {
    @JsonProperty("type")
    private String type;

    @JsonProperty("name")
    private String name;

    @JsonProperty("target_entities")
    private String targetEntities;

    @JsonProperty("fsm")
    private List<CFWState> fsm = new ArrayList<CFWState>();

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The targetEntities
     */
    @JsonProperty("target_entities")
    public String getTargetEntities() {
        return targetEntities;
    }

    /**
     * @param targetEntities The target_entities
     */
    @JsonProperty("target_entities")
    public void setTargetEntities(String targetEntities) {
        this.targetEntities = targetEntities;
    }

    /**
     * @return The fsm
     */
    @JsonProperty("fsm")
    public List<CFWState> getStates() {
        return fsm;
    }

    /**
     * @param fsm The fsm
     */
    @JsonProperty("fsm")
    public void setStates(List<CFWState> fsm) {
        this.fsm = fsm;
        this.fsm.sort(new Comparator<CFWState>() {
            @Override
            public int compare(CFWState o1, CFWState o2) {
                if (o1.getPriority() < o2.getPriority()) {
                    return -1;
                } else if (o1.getPriority() > o2.getPriority()) {
                    return 1;
                }
                return 0;
            }
        });
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
