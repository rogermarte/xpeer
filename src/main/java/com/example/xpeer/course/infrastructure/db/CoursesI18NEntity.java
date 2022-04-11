package com.example.xpeer.course.infrastructure.db;

import javax.persistence.*;

@Entity
@Table(name = "courses_i18n")
public class CoursesI18NEntity {
    @Id
    private String id;
    @Column
    private String locale;
    @Column
    private String key;
    @Column
    private String content;

    public static CoursesI18NEntity create(String id, String key, String locale, String content) {
        CoursesI18NEntity coursesI18NEntity = new CoursesI18NEntity();
        coursesI18NEntity.id = id;
        coursesI18NEntity.key = key;
        coursesI18NEntity.locale = locale;
        coursesI18NEntity.content = content;
        return coursesI18NEntity;
    }

    public String getId() {
        return id;
    }

    public String getLocale() {
        return locale;
    }

    public String getKey() {
        return key;
    }

    public String getContent() {
        return content;
    }
}
