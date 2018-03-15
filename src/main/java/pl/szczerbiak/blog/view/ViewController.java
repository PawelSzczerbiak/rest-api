package pl.szczerbiak.blog.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.szczerbiak.blog.model.Student;
import pl.szczerbiak.blog.repositories.StudentRepository;

import java.util.Optional;

@Controller
public class ViewController {

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping("/update/student/phonenumber")
    public String updatePhoneNumber(){
        return "updateStudent";
    }

    @PostMapping("/students/form/update/phone")
    public String UpdateStudentByForm(@RequestParam Long id,
                                      @RequestParam String phoneNumber){

        Optional<Student> studentOptional = studentRepository.findById(id);

        studentOptional.ifPresent(result ->{
            result = studentOptional.get();
            result.getStudentDetails().setPhoneNumber(phoneNumber);
            studentRepository.save(result);
        });

        return "updateStudent";
    }
}
