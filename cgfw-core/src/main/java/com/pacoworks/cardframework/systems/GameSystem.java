package com.pacoworks.cardframework.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.pacoworks.cardframework.components.GamePhases;
import com.pacoworks.cardframework.custom.ConcurrentStack;

/**
 * Created by Paco on 20/09/2014.
 */
public abstract class GameSystem extends EntityProcessingSystem {
    @Mapper
    ComponentMapper<GamePhases> gamePhasesComponentMapper;

    /**
     * Creates an entity system that uses the specified aspect as a matcher
     * against entities.
     */
    public GameSystem() {
        super(Aspect.getAspectForAll(GamePhases.class));
    }

    @Override
    protected void process(Entity e) {
        ConcurrentStack<BasePhaseSystem> phaseSystems = gamePhasesComponentMapper.get(e).getPhaseSystems();
        BasePhaseSystem system = phaseSystems.pop();
        system.process();
        BasePhaseSystem nextPhase = system.pushSystem();
        if (nextPhase != null){
            phaseSystems.push(nextPhase);
        }
        if (isVictoryCondition()){
            while (phaseSystems.pop() != null) {
                // POP POP!
            }
            triggerVictory();
        }
    }

    protected abstract boolean isVictoryCondition();

    protected abstract void triggerVictory();
}
