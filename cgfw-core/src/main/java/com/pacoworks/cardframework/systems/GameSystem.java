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
    private static final Aspect ASPECT = Aspect.getAspectForAll(GamePhases.class);

    @Mapper
    ComponentMapper<GamePhases> gamePhasesComponentMapper;

    /**
     * Creates an entity system that uses the specified aspect as a matcher
     * against entities.
     */
    public GameSystem() {
        super(ASPECT);
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
