package com.pacoworks.cardframework.systems;

import com.artemis.Aspect;
import com.artemis.EntitySystem;

/**
 * Created by Paco on 20/09/2014.
 */
public abstract class BasePhaseSystem extends EntitySystem implements IPhaseSystem{
    /**
     * Creates an entity system that uses the specified aspect as a matcher
     * against entities.
     *
     * @param aspect to match against entities
     */
    public BasePhaseSystem(Aspect aspect) {
        super(aspect);
    }
}
