package com.github.longkerdandy.evo.api.message;

import java.util.Map;

/**
 * Trigger
 * Device sends certain type of notification(trigger) when state changed
 */
@SuppressWarnings("unused")
public class Trigger {

    private String triggerId;               // Trigger Id
    private int policy;                     // Attributes Override Policy
    private Map<String, Object> attributes; // Attributes

    public String getTriggerId() {
        return triggerId;
    }

    public void setTriggerId(String triggerId) {
        this.triggerId = triggerId;
    }

    public int getPolicy() {
        return policy;
    }

    public void setPolicy(int policy) {
        this.policy = policy;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
