
package com.pacoworks.cardframework.eventbus;

import com.pacoworks.cardframework.eventbus.events.BaseEvent;

import javax.inject.Singleton;

/**
 * Created by Paco on 20/09/2014. See LICENSE.md
 */
@Singleton
public interface IEventCommander {
    public void postAnyEvent(BaseEvent event);
}
