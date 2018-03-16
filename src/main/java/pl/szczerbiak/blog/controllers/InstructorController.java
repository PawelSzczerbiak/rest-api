package pl.szczerbiak.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.szczerbiak.blog.model.Course;
import pl.szczerbiak.blog.model.Instructor;
import pl.szczerbiak.blog.repositories.CourseRepository;
import pl.szczerbiak.blog.repositories.InstructorRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class InstructorController {

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/instructors/all")
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    // ManyToMany ( = OneToMany in this case)

/*    @GetMapping("/instructors/{idInstructor}/addcourse/{idCourse}")
    public String addCourseToInstructor(@PathVariable("idInstructor") Long idInstructor,
                                        @PathVariable("idCourse") Long idCourse) {

        Optional<Instructor> instructorOptional = instructorRepository.findById(idInstructor);

        instructorOptional.ifPresent(result -> { // TODO: second ifPresent
            Optional<Course> courseOptional = courseRepository.findById(idCourse);
            Set<Course> courses = instructorOptional.get().getCourses();
            courses.add(courseOptional.get());
            result.setCourses(courses);
            instructorRepository.save(result);
        });

        return "Instructor has new course!";
    }*/


}
