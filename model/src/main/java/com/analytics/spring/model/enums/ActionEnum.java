package com.analytics.spring.model.enums;

/**
 * Created by Dmitry Natalenko on 03.05.2015.
 */
public enum ActionEnum {

    BUY,
    SELL;

    ActionEnum() {
    }

    @Override
    public String toString() {
        String lowercase = name().toLowerCase(java.util.Locale.US);
        return lowercase;
    }
}
