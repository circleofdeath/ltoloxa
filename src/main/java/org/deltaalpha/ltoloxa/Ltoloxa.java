package org.deltaalpha.ltoloxa;

import java.util.HashMap;
import java.util.Map;

public class Ltoloxa {
    public Map<String, String> map = new HashMap<>();

    public void set(String key, String value) {
        map.put(key, value);
    }

    public String get(String key) {
        if(key == null || !map.containsKey(key)) {
            throw new IllegalStateException("Value not found or key is null");
        }

        return map.get(key);
    }

    public void load(Map<String, String> other) {
        if(other != null) other.forEach(this::set);
    }
}