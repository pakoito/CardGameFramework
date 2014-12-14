
package com.pacoworks.cardframework.api.conditions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "type", "params", "script"
})
@ToString
public class ScriptCondition extends CFWCondition {
    public static final String CONDITION_NAME = "script_condition";

    @JsonProperty("params")
    private List<String> params = new ArrayList<String>();

    @JsonProperty("script")
    private String script;

    /**
     * @return The params
     */
    @JsonProperty("params")
    public List<String> getParams() {
        return params;
    }

    /**
     * @param params The params
     */
    @JsonProperty("params")
    public void setParams(List<String> params) {
        this.params = params;
    }

    /**
     * @return The value
     */
    @JsonProperty("script")
    public String getScript() {
        return script;
    }

    /**
     * @param value The value
     */
    @JsonProperty("script")
    public void setScript(String script) {
        this.script = script;
    }

    @Override
    public boolean getResult() {
        return false;
    }
}
