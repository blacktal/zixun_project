package com.wcl.zixunproject.util;

import java.util.HashMap;
import java.util.Map;

public class ViewObject {
    
    Map<String, Object> vo = new HashMap<>();
    
    public void set(String key, Object object) {
        vo.put(key, object);
    }
    
    public Object get(String key) {
        return vo.get(key);
    }

}
