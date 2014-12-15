
package com.pacoworks.cardframework.api.model.actions;

import com.pacoworks.cardframework.api.CFWContext;

/**
 * Created by Paco on 14/12/2014. See LICENSE.md
 */
public class NoAction extends CFWAction {
    @Override
    public void doAction(CFWContext cfwContext, boolean passesConditions) {
    }

    @Override
    public void undoAction(CFWContext cfwContext) {
    }
}
