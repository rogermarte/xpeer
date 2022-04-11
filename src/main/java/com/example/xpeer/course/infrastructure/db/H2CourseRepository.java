package com.example.xpeer.course.infrastructure.db;

import com.example.xpeer.course.domain.Module;
import com.example.xpeer.course.domain.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.inject.Named;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Named
public class H2CourseRepository implements CourseRepository {

    private JPACourseRepository courseRepository;


    public H2CourseRepository(JPACourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void save(Course course) {
        courseRepository.save(
                CourseEntity.from(course)
        );
    }

    @Override
    public Course getBy(CourseId courseId) {
        return courseRepository.findById(courseId.getId().toString())
                .map(
                        getCourseEntityCourseFunction()
                ).orElseThrow(CourseNotFoundException::new);
    }

    @Override
    public List<Course> search(int page, int results) {
        return courseRepository.findAll(PageRequest.of(page, results))
                .map(
                        getCourseEntityCourseFunction()
                ).stream().toList();
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll().stream()
                .map(
                        getCourseEntityCourseFunction()
                ).toList();
    }

    private Function<CourseEntity, Course> getCourseEntityCourseFunction() {
        return course -> new Course(
                new CourseId(UUID.fromString(course.getId())),
                Name.of(course.getNameKey(), course.getName().stream().collect(Collectors.toMap(
                        CoursesI18NEntity::getLocale, CoursesI18NEntity::getContent
                ))),
                new Localization(course.getCountry(), course.getCity()),
                Description.of(course.getDescriptionKey(), course.getDescription().stream().collect(Collectors.toMap(
                        CoursesI18NEntity::getLocale, CoursesI18NEntity::getContent
                ))),
                Status.valueOf(course.getPublishStatus()),
                new Publication(course.getPublicationDate()),
                course.getModules().stream().map(
                        module -> new Module(
                                new ModuleId(UUID.fromString(module.getModuleId())),
                                Name.of(module.getModuleNameKey(), module.getModuleName().stream().collect(Collectors.toMap(
                                        ModulesI18NEntity::getLocale, ModulesI18NEntity::getContent
                                ))))
                ).toList()
        );
    }

}

