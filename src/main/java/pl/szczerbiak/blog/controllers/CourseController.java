package pl.szczerbiak.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.szczerbiak.blog.model.Course;
import pl.szczerbiak.blog.model.Instructor;
import pl.szczerbiak.blog.model.Student;
import pl.szczerbiak.blog.repositories.CourseRepository;
import pl.szczerbiak.blog.repositories.InstructorRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    InstructorRepository instructorRepository;

    @GetMapping("/courses/all")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @PostMapping("/courses/add")
    public Course addNewCourse(@ModelAttribute Course course) {
        return courseRepository.save(course);
    }

    @PutMapping("/courses/update/{id}")
    public String updateCourseById(@ModelAttribute Course course,
                                   @PathVariable("id") Long id) {

        Optional<Course> resultOptional = courseRepository.findById(id);

        resultOptional.ifPresent(result -> {
            if (course.getTitle() != null) // TODO: better method?
                result.setTitle(course.getTitle());
            if (course.getLevel() != null)
                result.setLevel(course.getLevel());
            courseRepository.save(result);
        });

        return "Course with id = " + id + " updated";
    }

    @DeleteMapping("/courses/delete/{id}")
    public String deleteCourseById(@PathVariable("id") Long id) {

        Optional<Course> resultOptional = courseRepository.findById(id);

        resultOptional.ifPresent(result -> {
            courseRepository.deleteById(id);
        });

        return "Course with id = " + id + " deleted";
    }

    // ManyToMany

/*    @GetMapping("/courses/{idCourse}/addinstructor/{idInstructor}")
    public String addInstructorToCourse(@PathVariable("idInstructor") Long idInstructor,
                                        @PathVariable("idCourse") Long idCourse) {

        Optional<Course> courseOptional = courseRepository.findById(idCourse);

        courseOptional.ifPresent(result -> { // TODO: second ifPresent
            Optional<Instructor> instructorOptional = instructorRepository.findById(idInstructor);
            List<Instructor> instructors = courseOptional.get().getInstructors();
            instructors.add(instructorOptional.get());
            result.setInstructors(instructors);
            courseRepository.save(result);
        });

        return "Course has new instructor!";
    }*/

    // OneToMany

    @GetMapping("/courses/{idCourse}/setinstructor/{idInstructor}")
    public String setInstructorToCourse(@PathVariable("idInstructor") Long idInstructor,
                                        @PathVariable("idCourse") Long idCourse) {

        Optional<Course> courseOptional = courseRepository.findById(idCourse);

        courseOptional.ifPresent(course -> { // TODO: second ifPresent
            Optional<Instructor> instructorOptional = instructorRepository.findById(idInstructor);
            course.setInstructor(instructorOptional.get());
            courseRepository.save(course);
        });

        return "Course has new instructor!";

    }

}
