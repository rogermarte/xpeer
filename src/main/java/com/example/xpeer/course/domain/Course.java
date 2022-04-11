package com.example.xpeer.course.domain;

import java.util.List;
import java.util.Objects;

public class Course {
    private CourseId id;
    private Name name;
    private Localization localization;
    private Description description;
    private Status publishStatus;
    private Publication publication;
    private List<Module> modules;

    public Course(CourseId id, Name name, Localization localization, Description description, Status publishStatus, Publication publication, List<Module> modules) {
        this.id = id;
        this.name = name;
        this.localization = localization;
        this.description = description;
        this.publishStatus = publishStatus;
        this.publication = publication;
        this.modules = modules;
    }

    public CourseId getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Localization getLocalization() {
        return localization;
    }

    public Description getDescription() {
        return description;
    }

    public Status getPublishStatus() {
        return publishStatus;
    }

    public Publication getPublication() {
        return publication;
    }

    public List<Module> getModules() {
        return modules;
    }
}
