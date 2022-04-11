package com.example.xpeer.course.infrastructure.api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Map;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ModuleRequest {
    private String moduleId;
    private Map<String, String> moduleName;

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
