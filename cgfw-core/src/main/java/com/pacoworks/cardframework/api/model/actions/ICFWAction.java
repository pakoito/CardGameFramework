
package com.pacoworks.cardframework.api.model.actions;

import com.artemis.Entity;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by Paco on 14/12/2014. See LICENSE.md
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DeclareWinnerAction.class, name = DeclareWinnerAction.ACTION_NAME),
        @JsonSubTypes.Type(value = SetWinnerAction.class, name = SetWinnerAction.ACTION_NAME),
        @JsonSubTypes.Type(value = SetActiveAction.class, name = SetActiveAction.ACTION_NAME),
        @JsonSubTypes.Type(value = DrawHiddenAction.class, name = DrawHiddenAction.ACTION_NAME),
        @JsonSubTypes.Type(value = DrawAction.class, name = DrawAction.ACTION_NAME)
})
public interface ICFWAction {
    void doAction(Entity entity);

    void undoAction();
}
