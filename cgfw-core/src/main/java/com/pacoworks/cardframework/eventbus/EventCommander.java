
package com.pacoworks.cardframework.eventbus;

import lombok.Getter;
import lombok.experimental.Accessors;

import com.pacoworks.cardframework.eventbus.events.BaseEvent;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by Paco on 21/09/2014.
 */
public class EventCommander {
    @Getter(lazy = true)
    @Accessors(prefix = "m")
    private final Bus mEventBus = createBus();

    private Bus createBus() {
        return new Bus(ThreadEnforcer.ANY);
    }

    public void postAnyEvent(BaseEvent event) {
        getEventBus().post(event);
    }
}
