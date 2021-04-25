package com.oneyoung.common.message;

import lombok.Getter;

/**
 * @author oneyoung
 */
public enum SeverityType {
    /**
     *
     */
    MINOR(0),
    /**
     *
     */
    MAJOR(5),
    /**
     *
     */
    BLOCK(10);

    /**
     *
     */
    @Getter
    private int value;

    SeverityType(int value) {
        this.value = value;
    }
}
