
package com.pacoworks.cardframework.eventbus.events;

import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * Created by Paco on 20/09/2014. See LICENSE.md
 */
@Value(staticConstructor = "create")
@EqualsAndHashCode(callSuper = true)
public class EventVictory extends BaseEvent {
}
