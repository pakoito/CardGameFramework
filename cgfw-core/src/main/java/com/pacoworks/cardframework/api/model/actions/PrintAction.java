
package com.pacoworks.cardframework.api.model.actions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pacoworks.cardframework.api.CFWContext;
import com.pacoworks.cardframework.api.model.values.ICFWValue;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paco on 14/12/2014. See LICENSE.md
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "type", "message", "elements"
})
public class PrintAction extends CFWAction {
    public static final String ACTION_NAME = "print";

    @JsonProperty("message")
    private String message = "";

    @JsonProperty("elements")
    private List<ICFWValue> elements = new ArrayList<ICFWValue>();

    /**
     * @return The target
     */
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    /**
     * @param message The target
     */
    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return The amount
     */
    @JsonProperty("elements")
    public List<ICFWValue> getElements() {
        return elements;
    }

    /**
     * @param elements The amount
     */
    @JsonProperty("elements")
    public void setElements(List<ICFWValue> elements) {
        this.elements = elements;
    }

    @Override
    public void doAction(CFWContext cfwContext, boolean passesConditions) {
        if (!passesConditions){
            return;
        }
        StringBuilder stringBuilder = new StringBuilder(message);
        if (elements.size() > 0) {
            for (int i = 0; i < elements.size(); i++) {
                ICFWValue value = elements.get(i);
                stringBuilder.append(value.getValue(cfwContext));
                if (i + 1 != elements.size()) {
                    stringBuilder.append(" / ");
                }
            }
        }
        System.out.println(stringBuilder.toString());
    }

    @Override
    public void undoAction(CFWContext cfwContext) {
    }
}
