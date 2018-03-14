package pl.szczerbiak.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.szczerbiak.blog.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
