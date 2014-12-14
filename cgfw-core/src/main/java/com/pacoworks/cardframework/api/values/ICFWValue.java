
package com.pacoworks.cardframework.api.values;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by Paco on 14/12/2014. See LICENSE.md
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = NumericalValue.class, name = NumericalValue.VALUE_NAME),
        @JsonSubTypes.Type(value = PlayerAttributeValue.class, name = PlayerAttributeValue.VALUE_NAME),
        @JsonSubTypes.Type(value = ScriptNumericalValue.class, name = ScriptNumericalValue.VALUE_NAME)
})
public interface ICFWValue {
    Object getValue();
}
