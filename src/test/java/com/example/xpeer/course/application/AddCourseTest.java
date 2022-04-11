package com.example.xpeer.course.application;

import com.example.xpeer.course.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddCourseTest {

    @Mock
    private CourseRepository courseRepository;

    private AddCourse addCourse;

    @BeforeEach
    void setUp() {
        addCourse = new AddCourse(courseRepository);
    }

    @Test
    void should_add_new_course() {
        Course course = new Course(new CourseId(UUID.randomUUID()),
                Name.of("akey", Collections.emptyMap()),
                new Localization("Spain", "BCN"),
                Description.of("anotherkey", Collections.emptyMap()),
                Status.ARCHIVED,
                new Publication(LocalDate.now()),
                Collections.emptyList()
        );

        addCourse.execute(course);

        verify(courseRepository).save(course);
    }
}