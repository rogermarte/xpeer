package com.example.xpeer.course.infrastructure.api.dto;

import com.example.xpeer.course.domain.Course;
import com.example.xpeer.course.domain.Multilingual;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CourseResponse {
    private String id;
    private Map<String, String> name;
    private String country;
    private String city;
    private Map<String, String> description;
    private String publishStatus;
    private String publicationDate;
    private List<ModuleResponse> modules;

    public static CourseResponse from(Course course) {
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.id = course.getId().getId().toString();
        courseResponse.name = course.getName().getNames().stream().collect(Collectors.toMap(Multilingual::getLocale, Multilingual::getContent));
        courseResponse.country = course.getLocalization().getCountry();
        courseResponse.city = course.getLocalization().getCity();
        courseResponse.description = course.getDescription().getDescriptions().stream().collect(Collectors.toMap(Multilingual::getLocale, Multilingual::getContent));
        courseResponse.publishStatus = course.getPublishStatus().name();
        courseResponse.publicationDate = course.getPublication().getDate().toString();
        courseResponse.modules = course.getModules().stream().map(
                module -> ModuleResponse.from(module)
        ).collect(Collectors.toList());
        return courseResponse;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getName() {
        return name;
    }

    public void setName(Map<String, String> name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Map<String, String> getDescription() {
        return description;
    }

    public void setDescription(Map<String, String> description) {
        this.description = description;
    }

    public String getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<ModuleResponse> getModules() {
        return modules;
    }

    public void setModules(List<ModuleResponse> modules) {
        this.modules = modules;
    }
}
