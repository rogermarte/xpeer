package com.example.xpeer.course.application;

import com.example.xpeer.course.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpdateCourseTest {

    @Mock
    private CourseRepository courseRepository;

    private UpdateCourse updateCourse;

    @BeforeEach
    void setUp() {
        updateCourse = new UpdateCourse(courseRepository);
    }

    @Test
    void should_update_a_course() {
        Course course = new Course(new CourseId(UUID.randomUUID()),
                Name.of("akey", Collections.emptyMap()),
                new Localization("Spain", "BCN"),
                Description.of("anotherkey", Collections.emptyMap()),
                Status.ARCHIVED,
                new Publication(LocalDate.now()),
                Collections.emptyList()
        );

        updateCourse.execute(course);

        verify(courseRepository).save(course);
    }
}