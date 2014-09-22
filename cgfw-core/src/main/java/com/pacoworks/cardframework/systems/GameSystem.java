
package com.pacoworks.cardframework.systems;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.pacoworks.cardframework.components.GamePhases;
import com.pacoworks.cardframework.custom.ConcurrentStack;
import com.pacoworks.cardframework.eventbus.EventCommander;
import com.pacoworks.cardframework.eventbus.events.EventVictory;

/**
 * Created by Paco on 20/09/2014. See LICENSE.md
 */
@Wire
@Slf4j
public class GameSystem extends EntityProcessingSystem {
    private static final Aspect ASPECT = Aspect.getAspectForAll(GamePhases.class);

    ComponentMapper<GamePhases> gamePhasesComponentMapper;

    @Wire
    private EventCommander mCommander;

    private IVictoryDecider gameSystemListener;

    public GameSystem(@NonNull IVictoryDecider gameSystemListener) {
        super(ASPECT);
        this.gameSystemListener = gameSystemListener;
    }

    @Override
    protected void process(Entity e) {
        GamePhases phases = gamePhasesComponentMapper.get(e);
        ConcurrentStack<BasePhaseSystem> phaseSystems = phases.getPhaseSystems();
        BasePhaseSystem system = phaseSystems.pop();
        system.process();
        BasePhaseSystem[] nextPhases = system.pushSystems();
        if (nextPhases != null && nextPhases.length > 0) {
            for (BasePhaseSystem nextPhase : nextPhases)
            phaseSystems.push(nextPhase);
        }
        if (gameSystemListener.isVictoryCondition()) {
            mCommander.postAnyEvent(EventVictory.create());
            while (phaseSystems.pop() != null) {
                // POP POP!
                log.info("Popping all events on the stack");
            }
        }
    }
}
