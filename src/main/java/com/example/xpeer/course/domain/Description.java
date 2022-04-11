package com.example.xpeer.course.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Description {
    private String key;
    private List<Multilingual> descriptions;

    private Description(String key, List<Multilingual> descriptions) {
        this.key = key;
        this.descriptions = descriptions;
    }

    public static Description of(String key, Map<String, String> descriptions) {
        return new Description(key,
                descriptions
                        .keySet()
                        .stream()
                        .map(name -> new Multilingual(name, descriptions.get(name))).collect(Collectors.toList()));
    }

    public String getKey() {
        return key;
    }

    public List<Multilingual> getDescriptions() {
        return descriptions;
    }
}
