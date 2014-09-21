package com.pacoworks.cardframework.eventbus;

import lombok.Getter;
import lombok.experimental.Accessors;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by Paco on 20/09/2014.
 */
public class OttoBus {

    @Getter
    @Accessors(prefix = "m")
    private static final Bus mBus = new Bus(ThreadEnforcer.ANY);

}
