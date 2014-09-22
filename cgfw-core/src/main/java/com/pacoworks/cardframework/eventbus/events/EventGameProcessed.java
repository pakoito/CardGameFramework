
package com.pacoworks.cardframework.eventbus.events;

import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * Created by Paco on 20/09/2014. License available in LICENSE.md
 */
@Value(staticConstructor = "create")
@EqualsAndHashCode(callSuper = true)
public class EventGameProcessed extends BaseEvent {
}
