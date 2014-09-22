
package com.pacoworks.cardframework.components;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.artemis.Component;

/**
 * Created by Paco on 20/09/2014. License available in LICENSE.md
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseComponent extends Component implements Serializable {
}
