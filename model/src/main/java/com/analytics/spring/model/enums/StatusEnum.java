package com.analytics.spring.model.enums;

/**
 * Created by Dmitry Natalenko on 29.04.2015.
 */
public enum StatusEnum {
    ACTIVE,
    NON_ACTIVE,
    BLOCKED;

    StatusEnum() {
    }

    @Override
    public String toString() {
        String lowercase = name().toLowerCase(java.util.Locale.US);
        return lowercase;
    }
}
