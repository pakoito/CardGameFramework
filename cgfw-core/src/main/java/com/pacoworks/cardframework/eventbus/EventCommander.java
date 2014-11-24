
package com.pacoworks.cardframework.eventbus;

import com.pacoworks.cardframework.eventbus.events.BaseEvent;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Paco on 20/09/2014. See LICENSE.md
 */
@Singleton
public class EventCommander {
    @Getter(lazy = true, value = AccessLevel.PROTECTED)
    @Accessors(prefix = "m")
    private final Bus mEventBus = createBus();

    @Inject
    public EventCommander() {
    }

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
