
package com.pacoworks.cardframework.api.util;

import com.artemis.World;
import com.pacoworks.cardframework.framework.CardgameFramework;

import javax.inject.Inject;

/**
 * Created by Paco on 14/12/2014. See LICENSE.md
 */
public class WorldFetcher {
    @Inject
    World world;

    private WorldFetcher() {
    }

    public static World fetchWorld() {
        WorldFetcher fetcher = new WorldFetcher();
        CardgameFramework.getComponentInjector().inject(fetcher);
        return fetcher.world;
    }
}
