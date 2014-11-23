
package com.pacoworks.cardframework.eventbus;

import com.pacoworks.cardframework.eventbus.events.BaseEvent;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Created by Paco on 20/09/2014. See LICENSE.md
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
