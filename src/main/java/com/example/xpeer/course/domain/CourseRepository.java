package com.example.xpeer.course.domain;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    void save(Course course);

    @Transactional
    Course getBy(CourseId courseId);

    @Transactional
    List<Course> search(int page, int results);

    @Transactional
    List<Course> findAll();
}
