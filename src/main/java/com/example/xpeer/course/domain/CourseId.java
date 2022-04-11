package com.example.xpeer.course.domain;

import java.util.UUID;

public class CourseId {
    private UUID id;

    public CourseId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
