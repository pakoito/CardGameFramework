package com.pacoworks.cardframework.systems;

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
 * Created by Paco on 20/09/2014.
 */
@Wire
@Slf4j
public class GameSystem extends EntityProcessingSystem {
    private static final Aspect ASPECT = Aspect.getAspectForAll(GamePhases.class);

    ComponentMapper<GamePhases> gamePhasesComponentMapper;

    private EventCommander mCommander;

    private IVictoryDecider gameSystemListener;

    /**
     * Creates an entity system that uses the specified aspect as a matcher
     * against entities.
     */
    public GameSystem(IVictoryDecider gameSystemListener) {
        super(ASPECT);
        this.gameSystemListener = gameSystemListener;
    }

    @Override
    protected void process(Entity e) {
        GamePhases phases = gamePhasesComponentMapper.get(e);
        ConcurrentStack<BasePhaseSystem> phaseSystems = phases.getPhaseSystems();
        BasePhaseSystem system = phaseSystems.pop();
        system.process();
        BasePhaseSystem nextPhase = system.pushSystem();
        if (nextPhase != null){
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
