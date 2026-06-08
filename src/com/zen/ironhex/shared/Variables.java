package com.zen.ironhex.shared;

import java.util.HashMap;
import java.util.Map;

public class Variables {
    public final static Map<String, String> vars;
    public final static Map<String, Integer> entitiesMaxSize;

    static {
        vars = new HashMap<>();
        entitiesMaxSize = Map.of(
                "--account", 8,
                "--card", 12,
                "--contact", 6,
                "--note", 4,
                "--pass", 4
        );
    }
}
