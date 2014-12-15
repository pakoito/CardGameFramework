
package com.pacoworks.cardframework.api.model.conditions;

import com.pacoworks.cardframework.api.CFWContext;

/**
 * Created by Paco on 14/12/2014. See LICENSE.md
 */
public class NoCondition extends CFWCondition {
    @Override
    public boolean getResult(CFWContext cfwContext) {
        return true;
    }
}
