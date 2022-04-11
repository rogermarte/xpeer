package com.example.xpeer.course.application;

import com.example.xpeer.course.domain.Course;
import com.example.xpeer.course.domain.CourseRepository;

import javax.inject.Named;

@Named
public class AddCourse {

    private CourseRepository courseRepository;

    public AddCourse(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void execute(Course course) {
        courseRepository.save(course);
    }
}
