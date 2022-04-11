package com.example.xpeer.course.infrastructure.api.dto;

import com.example.xpeer.course.domain.Module;
import com.example.xpeer.course.domain.Multilingual;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Map;
import java.util.stream.Collectors;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ModuleResponse {
    private String moduleId;
    private Map<String, String> moduleName;

    public static ModuleResponse from(Module module) {
        ModuleResponse moduleResponse = new ModuleResponse();
        moduleResponse.moduleId = module.getId().getId().toString();
        moduleResponse.moduleName = module.getName().getNames().stream().collect(Collectors.toMap(
                Multilingual::getLocale, Multilingual::getContent
        ));
        return moduleResponse;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public Map<String, String> getModuleName() {
        return moduleName;
    }

    public void setModuleName(Map<String, String> moduleName) {
        this.moduleName = moduleName;
    }
}
