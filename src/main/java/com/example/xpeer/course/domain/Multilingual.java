package com.example.xpeer.course.domain;

import java.util.UUID;

public class Multilingual {
    private String id;
    private String locale;
    private String content;

    public Multilingual(String locale, String content) {
        this.id = UUID.randomUUID().toString();
        this.locale = locale;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getLocale() {
        return locale;
    }

    public String getContent() {
        return content;
    }
}
