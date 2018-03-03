package com.wcl.zixunproject.util;

public enum EntityType {
    QUESTION(1),
    COMMENT(2),
    USER(3);
    
    private int value;
    
    EntityType(int value) {
        this.value = value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
