
package com.pacoworks.cardframework.api.factories.system;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.managers.TagManager;
import com.pacoworks.cardframework.api.CFWConstants;
import com.pacoworks.cardframework.api.model.CFWState;
import com.pacoworks.cardframework.api.model.CFWSystem;
import com.pacoworks.cardframework.api.model.actions.ICFWAction;
import com.pacoworks.cardframework.api.model.conditions.ICFWCondition;
import com.pacoworks.cardframework.systems.BasePhaseSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paco on 14/12/2014. See LICENSE.md
 */
@Wire
public class SystemPrototype extends BasePhaseSystem {
    private TagManager tagManager;

    private CFWSystem systemDef;

    private BasePhaseSystem[] basePhaseSystems;

    private Entity selectedPlayer;

    private boolean isFinalize;

    public void setCFWSystem(CFWSystem CFWSystem) {
        this.systemDef = CFWSystem;
    }

    public CFWSystem getCFWSystem() {
        return systemDef;
    }

    public SystemPrototype(Aspect aspect) {
        super(aspect);
    }

    @Override
    protected void begin() {
        super.begin();
        selectedPlayer = tagManager.getEntity(CFWConstants.PlayerGroups.SELECTED_PLAYER);
        isFinalize = false;
    }

    @Override
    protected void process(Entity e) {
        tagManager.register(CFWConstants.PlayerGroups.CURRENT_PLAYER, e);
        boolean selectedPlayerOnly = CFWConstants.PlayerGroups.SELECTED_PLAYER.equals(systemDef.getTargetEntities())
                && selectedPlayer != null && selectedPlayer.getUuid() != e.getUuid();
        if (isFinalize || selectedPlayerOnly) {
            return;
        }
        for (CFWState state : systemDef.getStates()) {
            boolean passesConditions = true;
            for (ICFWCondition condition : state.getCondition()) {
                if (!condition.getResult()) {
                    passesConditions = false;
                    break;
                }
            }
            if (!passesConditions) {
                continue;
            }
            for (ICFWAction action : state.getActions()) {
                action.doAction();
            }
            List<String> next = state.getNext();
            List<BasePhaseSystem> sanitizedSystemList = new ArrayList<BasePhaseSystem>();
            for (String nextSystem : next) {
                BasePhaseSystem newSystem = CFWConstants.CustomSystems.getSystem(nextSystem);
                if (newSystem != null) {
                    sanitizedSystemList.add(newSystem);
                }
            }
            basePhaseSystems = sanitizedSystemList.toArray(new BasePhaseSystem[sanitizedSystemList
                    .size()]);
            isFinalize = state.isFinalize();
            break;
        }
    }

    @Override
    protected void end() {
        super.end();
        tagManager.register(CFWConstants.PlayerGroups.CURRENT_PLAYER, null);
    }

    @Override
    public BasePhaseSystem[] pushSystems() {
        return basePhaseSystems;
    }
}
