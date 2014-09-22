
package com.pacoworks.cardframework.eventbus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

import com.pacoworks.cardframework.eventbus.events.BaseEvent;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by Paco on 21/09/2014. License available in LICENSE.md
 */
public class EventCommander {
    @Getter(lazy = true, value = AccessLevel.PROTECTED)
    @Accessors(prefix = "m")
    private final Bus mEventBus = createBus();

    private Bus createBus() {
        return new Bus(ThreadEnforcer.ANY);
    }

    public void subscribe(Object subscriptor) {
        getEventBus().register(subscriptor);
    }

    public void unsubscribe(Object subscriptor) {
        getEventBus().unregister(subscriptor);
    }

    public void postAnyEvent(BaseEvent event) {
        getEventBus().post(event);
    }
}
