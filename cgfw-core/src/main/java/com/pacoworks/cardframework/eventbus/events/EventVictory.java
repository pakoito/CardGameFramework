
package com.pacoworks.cardframework.eventbus.events;

import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * Created by Paco on 21/09/2014.
 */
@Value(staticConstructor = "create")
@EqualsAndHashCode(callSuper = true)
public class EventVictory extends BaseEvent {
}
