package com.example.xpeer.course.application;

import com.example.xpeer.course.domain.Course;
import com.example.xpeer.course.domain.CourseId;
import com.example.xpeer.course.domain.CourseRepository;

import javax.inject.Named;

@Named
public class GetCourse {

    private CourseRepository courseRepository;

    public GetCourse(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course execute(CourseId courseId) {
        return courseRepository.getBy(courseId);
    }
}
