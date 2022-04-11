package com.example.xpeer.course.domain;

import java.util.UUID;

public class ModuleId {
    private UUID id;

    public ModuleId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
