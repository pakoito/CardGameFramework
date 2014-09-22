
package com.pacoworks.cardframework.components;

import com.artemis.Component;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by Paco on 20/09/2014. See LICENSE.md
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseComponent extends Component implements Serializable {
}
