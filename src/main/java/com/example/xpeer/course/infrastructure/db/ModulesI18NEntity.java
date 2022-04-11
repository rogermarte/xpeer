package com.example.xpeer.course.infrastructure.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "modules_i18n")
public class ModulesI18NEntity {
    @Id
    private String id;
    @Column
    private String locale;
    @Column
    private String key;
    @Column
    private String content;

    public static ModulesI18NEntity create(String id, String key, String locale, String content) {
        ModulesI18NEntity modulesI18NEntity = new ModulesI18NEntity();
        modulesI18NEntity.id = id;
        modulesI18NEntity.key = key;
        modulesI18NEntity.locale = locale;
        modulesI18NEntity.content = content;
        return modulesI18NEntity;
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
