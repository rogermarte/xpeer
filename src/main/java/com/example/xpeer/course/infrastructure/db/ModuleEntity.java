package com.example.xpeer.course.infrastructure.db;

import com.example.xpeer.course.domain.Course;
import com.example.xpeer.course.domain.Module;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "modules")
public class ModuleEntity implements Serializable {
    @Id
    private String moduleId;
    @Column
    private String moduleNameKey;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "key", referencedColumnName = "moduleNameKey")
    private List<ModulesI18NEntity> moduleName;
    @Column
    private String courseId;

    public static ModuleEntity from(Module module, Course course) {
        ModuleEntity moduleEntity = new ModuleEntity();
        moduleEntity.moduleId = module.getId().getId().toString();
        moduleEntity.moduleNameKey = module.getName().getKey();
        moduleEntity.moduleName = module.getName().getNames().stream().map(
                name -> ModulesI18NEntity.create(name.getId(), module.getName().getKey(), name.getLocale(), name.getContent())
        ).collect(Collectors.toList());
        moduleEntity.courseId = course.getId().getId().toString();
        return moduleEntity;
    }

    public String getModuleId() {
        return moduleId;
    }

    public String getModuleNameKey() {
        return moduleNameKey;
    }

    public List<ModulesI18NEntity> getModuleName() {
        return moduleName;
    }

    public String getCourseId() {
        return courseId;
    }
}
