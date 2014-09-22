
package com.pacoworks.cardframework.components;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.pacoworks.cardframework.custom.ConcurrentStack;
import com.pacoworks.cardframework.systems.BasePhaseSystem;

/**
 * Created by Paco on 20/09/2014. See LICENSE.md
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(prefix = "m")
public class GamePhases extends BaseComponent {
    private ConcurrentStack<BasePhaseSystem> mPhaseSystems = new ConcurrentStack<BasePhaseSystem>();

    public GamePhases(BasePhaseSystem phaseSystem) {
        mPhaseSystems.push(phaseSystem);
    }
}
