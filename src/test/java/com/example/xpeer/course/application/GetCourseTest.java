package com.example.xpeer.course.application;

import com.example.xpeer.course.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GetCourseTest {
    @Mock
    private CourseRepository courseRepository;

    private GetCourse getCourse;

    @BeforeEach
    void setUp() {
        getCourse = new GetCourse(courseRepository);
    }

    @Test
    void should_get_course() {
        CourseId courseId = new CourseId(UUID.randomUUID());
        Course expectedCourse = new Course(courseId,
                Name.of("akey", Collections.emptyMap()),
                new Localization("Spain", "BCN"),
                Description.of("anotherkey", Collections.emptyMap()),
                Status.ARCHIVED,
                new Publication(LocalDate.now()),
                Collections.emptyList()
        );
        given(courseRepository.getBy(courseId)).willReturn(expectedCourse);

        Course course = getCourse.execute(courseId);

        Assertions.assertThat(course).usingRecursiveComparison().isEqualTo(expectedCourse);
    }

}