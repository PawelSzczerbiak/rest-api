package pl.szczerbiak.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.szczerbiak.blog.model.Student;
import pl.szczerbiak.blog.model.StudentDetails;
import pl.szczerbiak.blog.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/students/all") // endpoint from webservice
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping("/students/add") // another endpoint
    public Student addNewStudent(@ModelAttribute Student student,
                                 @ModelAttribute StudentDetails studentDetails) {

        Student result = new Student(
                student.getName(),
                student.getEmail(),
                studentDetails
        );

        return studentRepository.save(result);
    }

    @PutMapping("/students/update/{id}")
    public String updateStudentById(@ModelAttribute Student student,
                                    @ModelAttribute StudentDetails studentDetails,
                                    @PathVariable("id") Long id) {

        Optional<Student> resultOptional = studentRepository.findById(id);

        resultOptional.ifPresent(result -> {
//        result = resultOptional.get(); // TODO: safe?
            if (student.getEmail() != null) // TODO: better method?
                result.setEmail(student.getEmail());
            if (studentDetails.getLastname() != null)
                result.getStudentDetails().setLastname(studentDetails.getLastname());
            if (studentDetails.getPhoneNumber() != null)
                result.getStudentDetails().setPhoneNumber(studentDetails.getPhoneNumber());
            studentRepository.save(result);
        });

        return "Student with id = " + id + " updated";
    }

    @DeleteMapping("/students/delete/{id}")
    public String deleteStudentById(@PathVariable("id") Long id) {

        Optional<Student> resultOptional = studentRepository.findById(id);

        resultOptional.ifPresent(result -> {
            studentRepository.deleteById(id);
        });

        return "Student with id = " + id + " deleted";

    }
}