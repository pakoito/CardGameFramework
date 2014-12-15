
package com.pacoworks.cardframework.components;

import com.pacoworks.cardframework.api.model.components.CFWComponent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Paco on 14/12/2014. See LICENSE.md
 */
public class UnpackedComponent extends CFWComponent {
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public void setElement(String key, Object object) {
        additionalProperties.put(key, object);
    }

    public Object getElement(String key) {
        return additionalProperties.get(key);
    }

    @Override
    public Float getValue(String attribute) {
        Object dump = additionalProperties.get(attribute);
        Float result = 0f;
        if (dump != null) {
            try {
                /* Doing this rather than cast in case the user decides to send numbers as strings */
                result = Float.parseFloat(dump.toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
