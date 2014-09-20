
package com.pacoworks.cardframework.systems;

/**
 * Created by Paco on 20/09/2014.
 */
public interface IGameSystemListener {
    public boolean isVictoryCondition();

    public void triggerVictory();
}
