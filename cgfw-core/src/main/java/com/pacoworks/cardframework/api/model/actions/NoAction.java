
package com.pacoworks.cardframework.api.model.actions;

import com.artemis.Entity;

/**
 * Created by Paco on 14/12/2014. See LICENSE.md
 */
public class NoAction extends CFWAction {
    @Override
    public void doAction(boolean passesConditions, Entity entity) {
    }

    @Override
    public void undoAction() {
    }
}
