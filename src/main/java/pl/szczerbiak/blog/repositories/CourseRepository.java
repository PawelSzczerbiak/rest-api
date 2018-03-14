package pl.szczerbiak.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.szczerbiak.blog.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
