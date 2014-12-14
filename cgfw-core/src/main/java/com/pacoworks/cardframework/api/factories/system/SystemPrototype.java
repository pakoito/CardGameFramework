
package com.pacoworks.cardframework.api.factories.system;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.pacoworks.cardframework.api.model.CFWState;
import com.pacoworks.cardframework.api.model.CFWSystem;
import com.pacoworks.cardframework.api.model.actions.ICFWAction;
import com.pacoworks.cardframework.api.model.conditions.ICFWCondition;
import com.pacoworks.cardframework.systems.BasePhaseSystem;

/**
 * Created by Paco on 14/12/2014. See LICENSE.md
 */
public class SystemPrototype extends BasePhaseSystem {
    private CFWSystem systemDef;

    public SystemPrototype(Aspect aspect) {
        super(aspect);
    }

    @Override
    protected void process(Entity e) {
        for (CFWState state : systemDef.getStates()) {
            boolean passesConditions = true;
            for (ICFWCondition condition : state.getCondition()) {
                if (!condition.getResult()) {
                    passesConditions = false;
                    break;
                }
            }
            if (!passesConditions){
                continue;
            }
            for (ICFWAction action : state.getActions()) {
                action.doAction();
            }
            break;
        }
    }

    @Override
    public BasePhaseSystem[] pushSystems() {
        return new BasePhaseSystem[0];
    }

    public void setCFWSystem(CFWSystem CFWSystem) {
        this.systemDef = CFWSystem;
    }

    public CFWSystem getCFWSystem() {
        return systemDef;
    }
}
