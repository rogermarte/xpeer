package com.example.xpeer.course.infrastructure.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface JPACourseRepository extends PagingAndSortingRepository<CourseEntity, String>, JpaRepository<CourseEntity, String> {
}
