package com.example.xpeer.course.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Name {
    private String key;
    private List<Multilingual> names;

    private Name(String key, List<Multilingual> names) {
        this.key = key;
        this.names = names;
    }

    public static Name of(String key, Map<String, String> names) {
        return new Name(key,
                names
                        .keySet()
                        .stream()
                        .map(name -> new Multilingual(name, names.get(name))).collect(Collectors.toList()));
    }

    public String getKey() {
        return key;
    }

    public List<Multilingual> getNames() {
        return names;
    }
}
