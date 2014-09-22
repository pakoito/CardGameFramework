
package com.pacoworks.cardframework.systems;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.artemis.Aspect;
import com.artemis.systems.EntityProcessingSystem;

/**
 * Created by Paco on 20/09/2014. See LICENSE.md
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BasePhaseSystem extends EntityProcessingSystem implements IPhaseSystem {
    /**
     * Creates an entity system that uses the specified aspect as a matcher against entities.
     *
     * @param aspect to match against entities
     */
    public BasePhaseSystem(Aspect aspect) {
        super(aspect);
    }
}
