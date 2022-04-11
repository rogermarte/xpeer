package com.example.xpeer.course.domain;

public class Module {
    private ModuleId id;
    private Name name;

    public Module(ModuleId id, Name name) {
        this.id = id;
        this.name = name;
    }

    public ModuleId getId() {
        return id;
    }

    public Name getName() {
        return name;
    }
}
