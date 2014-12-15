/**
 * Copyright 2014: Thomson Reuters Global Resources.
 * All Rights Reserved. Proprietary and Confidential information of TRGR.
 * Disclosure, Use or Reproduction without the written authorization of TRGR is prohibited.
 */

package com.pacoworks.cardframework.api.model.conditions;

import com.artemis.World;

public class Context {

    private final World world;

    public World getWorld() {
        return world;
    }

    public Context(World world) {
        this.world = world;
    }
}
