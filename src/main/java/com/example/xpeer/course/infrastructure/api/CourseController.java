package com.example.xpeer.course.infrastructure.api;

import com.example.xpeer.course.application.AddCourse;
import com.example.xpeer.course.application.GetCourse;
import com.example.xpeer.course.application.UpdateCourse;
import com.example.xpeer.course.domain.*;
import com.example.xpeer.course.domain.Module;
import com.example.xpeer.course.infrastructure.api.dto.CourseRequest;
import com.example.xpeer.course.infrastructure.api.dto.CourseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/courses",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class CourseController {

    private AddCourse addCourse;
    private GetCourse getCourse;
    private UpdateCourse updateCourse;
    private CourseRepository courseRepository;



    public CourseController(AddCourse addCourse, GetCourse getCourse, UpdateCourse updateCourse, CourseRepository courseRepository) {
        this.addCourse = addCourse;
        this.getCourse = getCourse;
        this.updateCourse = updateCourse;
        this.courseRepository = courseRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@RequestBody CourseRequest courseRequest) {
        addCourse.execute(getCourse(courseRequest));
    }

    @GetMapping("{courseId}")
    public CourseResponse get(@PathVariable("courseId") String courseId) {
        Course course = getCourse.execute(new CourseId(UUID.fromString(courseId)));
        return CourseResponse.from(course);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void put(@RequestBody CourseRequest courseRequest) {
        updateCourse.execute(getCourse(courseRequest));
    }

    private Course getCourse(CourseRequest courseRequest) {
        return new Course(
                new CourseId(UUID.fromString(courseRequest.getId())),
                Name.of("course.name." + courseRequest.getId(), courseRequest.getName()),
                new Localization(courseRequest.getCountry(), courseRequest.getCity()),
                Description.of("course.description." + courseRequest.getId(), courseRequest.getDescription()),
                Status.valueOf(courseRequest.getPublishStatus().toUpperCase()),
                new Publication(LocalDate.parse(courseRequest.getPublicationDate())),
                courseRequest
                        .getModules()
                        .stream()
                        .map(module -> new Module(
                                new ModuleId(UUID.fromString(module.getModuleId())),
                                Name.of("module.name." + module.getModuleId(), module.getModuleName())
                        )).collect(Collectors.toList())
        );
    }

    @GetMapping("/search")
    public List<CourseResponse> search(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return courseRepository.search(page, size).stream().map(
                course -> CourseResponse.from(course)
        ).collect(Collectors.toList());
    }

    @GetMapping("/list")
    public List<CourseResponse> list() {
        return courseRepository.findAll().stream().map(
                course -> CourseResponse.from(course)
        ).collect(Collectors.toList());
    }
}
