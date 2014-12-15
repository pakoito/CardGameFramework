
package com.pacoworks.cardframework.api.model.values;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.TagManager;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pacoworks.cardframework.api.CFWConstants;
import com.pacoworks.cardframework.api.CFWContext;
import com.pacoworks.cardframework.api.model.components.CFWComponent;
import com.pacoworks.cardframework.components.UnpackedComponent;
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

    public Float getValue(CFWContext cfwContext) {
        World world = cfwContext.getWorld();
        TagManager tgm = world.getManager(TagManager.class);
        Entity entity = tgm.getEntity(target);
        Float value = 0f;
        if (entity != null){
            Class<? extends CFWComponent> componentClass = CFWConstants.CustomComponents.getComponentClass(attribute);
            if (componentClass == null){
                componentClass = UnpackedComponent.class;
            }
            CFWComponent valueHolder = world.getMapper(componentClass).getSafe(entity);
            value = valueHolder.getValue(attribute);
        }
        return value;
    }
}
