package org.deltaalpha.ltoloxa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LtoloxaEnv {
    public static final Pattern pattern = Pattern.compile("\\$(.*?)\\$");
    public final Map<String, String> variables = new HashMap<>();
    public final Map<String, String> output = new HashMap<>();
    public final List<String> path = new ArrayList<>();
    public String pathCache;
    public boolean varedit;

    public void updateCache() {
        pathCache = String.join("", path);
    }

    public void append(String string) {
        path.add(string);
        updateCache();
    }

    public void prevdir() {
        path.remove(path.size() - 1);
        updateCache();
    }

    public String format(String input) {
        Matcher matcher = pattern.matcher(input);
        StringBuilder builder = new StringBuilder();
        while(matcher.find()) {
            matcher.appendReplacement(builder, variables.get(matcher.group(1)));
        }
        matcher.appendTail(builder);
        return builder.toString();
    }

    public void handle(String key, String value) {
        (varedit ? variables : output).put(pathCache + key, value);
    }
}