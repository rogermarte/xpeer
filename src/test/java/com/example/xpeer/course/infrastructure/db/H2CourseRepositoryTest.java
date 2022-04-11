package com.example.xpeer.course.infrastructure.db;

import com.example.xpeer.course.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;

@ExtendWith(MockitoExtension.class)
class H2CourseRepositoryTest {

    @Mock
    private JPACourseRepository jpaCourseRepository;

    private H2CourseRepository h2CourseRepository;

    @BeforeEach
    void setUp() {
        h2CourseRepository = new H2CourseRepository(jpaCourseRepository);
    }

    @Test
    void should_save_a_course() {
        Course course = new Course(new CourseId(UUID.randomUUID()),
                Name.of("akey", Collections.emptyMap()),
                new Localization("Spain", "BCN"),
                Description.of("anotherkey", Collections.emptyMap()),
                Status.ARCHIVED,
                new Publication(LocalDate.now()),
                Collections.emptyList()
        );
        CourseEntity courseEntity = CourseEntity.from(course);

        h2CourseRepository.save(course);

        verify(jpaCourseRepository).save(courseEntity);
    }

    @Test
    void should_get_a_course() {
        CourseId courseId = new CourseId(UUID.randomUUID());
        Course expectedCourse = new Course(courseId,
                Name.of("akey", Collections.emptyMap()),
                new Localization("Spain", "BCN"),
                Description.of("anotherkey", Collections.emptyMap()),
                Status.ARCHIVED,
                new Publication(LocalDate.now()),
                Collections.emptyList()
        );
        CourseEntity courseEntity = CourseEntity.from(expectedCourse);
        given(jpaCourseRepository.findById(courseId.getId().toString())).willReturn(Optional.of(courseEntity));

        Course course = h2CourseRepository.getBy(courseId);

        assertThat(course).usingRecursiveComparison().isEqualTo(expectedCourse);
    }

    @Test
    void should_fail_when_course_does_not_exist() {
        CourseId courseId = new CourseId(UUID.randomUUID());
        given(jpaCourseRepository.findById(courseId.getId().toString())).willReturn(Optional.empty());

        Throwable t = catchThrowable(() -> h2CourseRepository.getBy(courseId));

        assertThat(t).isExactlyInstanceOf(CourseNotFoundException.class);

    }

    @Test
    void should_list_courses() {
        CourseId courseId = new CourseId(UUID.randomUUID());
        Course expectedCourse = new Course(courseId,
                Name.of("akey", Collections.emptyMap()),
                new Localization("Spain", "BCN"),
                Description.of("anotherkey", Collections.emptyMap()),
                Status.ARCHIVED,
                new Publication(LocalDate.now()),
                Collections.emptyList()
        );
        CourseEntity courseEntity = CourseEntity.from(expectedCourse);
        given(jpaCourseRepository.findAll()).willReturn(Collections.singletonList(courseEntity));

        List<Course> courses = h2CourseRepository.findAll();

        assertThat(courses.get(0)).usingRecursiveComparison().isEqualTo(expectedCourse);
    }

    @Test
    void should_search_courses() {
        CourseId courseId = new CourseId(UUID.randomUUID());
        Course expectedCourse = new Course(courseId,
                Name.of("akey", Collections.emptyMap()),
                new Localization("Spain", "BCN"),
                Description.of("anotherkey", Collections.emptyMap()),
                Status.ARCHIVED,
                new Publication(LocalDate.now()),
                Collections.emptyList()
        );
        CourseEntity courseEntity = CourseEntity.from(expectedCourse);
        given(jpaCourseRepository.findAll(PageRequest.of(0, 1))).willReturn(new PageImpl(Collections.singletonList(courseEntity)));

        List<Course> courses = h2CourseRepository.search(0, 1);

        assertThat(courses.get(0)).usingRecursiveComparison().isEqualTo(expectedCourse);
    }
}