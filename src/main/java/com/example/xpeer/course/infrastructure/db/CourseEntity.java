package com.example.xpeer.course.infrastructure.db;

import com.example.xpeer.course.domain.Course;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "courses")
public class CourseEntity implements Serializable {
    @Id
    private String id;
    @Column
    private String nameKey;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "key", referencedColumnName = "nameKey")
    private List<CoursesI18NEntity> name;
    @Column
    private String country;
    @Column
    private String city;
    @Column
    private String descriptionKey;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "key", referencedColumnName = "descriptionKey")
    private List<CoursesI18NEntity> description;
    @Column
    private String publishStatus;
    @Column
    private LocalDate publicationDate;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "courseId", referencedColumnName = "id")
    private List<ModuleEntity> modules;


    public static CourseEntity from(Course course) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.id = course.getId().getId().toString();
        courseEntity.nameKey = course.getName().getKey();
        courseEntity.name = course.getName().getNames().stream().map(
                name -> CoursesI18NEntity.create(name.getId(), course.getName().getKey(), name.getLocale(), name.getContent())
        ).collect(Collectors.toList());
        courseEntity.country = course.getLocalization().getCountry();
        courseEntity.city = course.getLocalization().getCity();
        courseEntity.descriptionKey = course.getDescription().getKey();
        courseEntity.description = course.getDescription().getDescriptions().stream().map(
                description -> CoursesI18NEntity.create(description.getId(), course.getDescription().getKey(), description.getLocale(), description.getContent())
        ).collect(Collectors.toList());
        courseEntity.publishStatus = course.getPublishStatus().name();
        courseEntity.publicationDate = course.getPublication().getDate();
        courseEntity.modules = course.getModules().stream().map(
                module -> ModuleEntity.from(module, course)
        ).collect(Collectors.toList());
        return courseEntity;
    }

    public String getId() {
        return id;
    }

    public List<CoursesI18NEntity> getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public List<CoursesI18NEntity> getDescription() {
        return description;
    }

    public String getPublishStatus() {
        return publishStatus;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public List<ModuleEntity> getModules() {
        return modules;
    }

    public String getNameKey() {
        return nameKey;
    }

    public String getDescriptionKey() {
        return descriptionKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEntity that = (CourseEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(nameKey, that.nameKey) && Objects.equals(name, that.name) && Objects.equals(country, that.country) && Objects.equals(city, that.city) && Objects.equals(descriptionKey, that.descriptionKey) && Objects.equals(description, that.description) && Objects.equals(publishStatus, that.publishStatus) && Objects.equals(publicationDate, that.publicationDate) && Objects.equals(modules, that.modules);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameKey, name, country, city, descriptionKey, description, publishStatus, publicationDate, modules);
    }
}
