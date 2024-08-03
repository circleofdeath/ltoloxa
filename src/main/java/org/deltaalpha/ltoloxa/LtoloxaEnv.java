package org.deltaalpha.ltoloxa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LtoloxaEnv {
    public final Map<String, String> variables = new HashMap<>();
    public final List<String> path = new ArrayList<>();
    public String pathCache;
    public boolean varedit;

    public void updateCache() {
        pathCache = String.join("", path);
    }
}