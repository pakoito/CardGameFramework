package com.pacoworks.cardframework.api.model.conditions;

/**
 * Created by Paco on 14/12/2014.
 * See LICENSE.md
 */

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.pacoworks.cardframework.api.CFWContext;

/**
 * Created by Paco on 14/12/2014. See LICENSE.md
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BetweenCondition.class, name = BetweenCondition.CONDITION_NAME),
        @JsonSubTypes.Type(value = BiggerThanCondition.class, name = BiggerThanCondition.CONDITION_NAME),
        @JsonSubTypes.Type(value = InputKeyCondition.class, name = InputKeyCondition.CONDITION_NAME),
        @JsonSubTypes.Type(value = LowerThanCondition.class, name = LowerThanCondition.CONDITION_NAME),
        @JsonSubTypes.Type(value = EqualsCondition.class, name = EqualsCondition.CONDITION_NAME),
        @JsonSubTypes.Type(value = NotEqualsCondition.class, name = NotEqualsCondition.CONDITION_NAME),
        @JsonSubTypes.Type(value = ScriptCondition.class, name = ScriptCondition.CONDITION_NAME)
})
public interface ICFWCondition {
    boolean getResult(CFWContext cfwContext);
}
