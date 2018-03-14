package pl.szczerbiak.blog.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

// JSON parsing according to ID
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    // OLD

//    @OneToMany(mappedBy = "student",
//            cascade = CascadeType.ALL, // gdy usuwamy studenta to usuwamy też jego kursy
//            fetch = FetchType.LAZY) // nie zaciąga od razu wszystkich rekordów

    // NEW

//   @JsonManagedReference // or JsonIdentityInfo (see above)
    @ManyToMany
    @JoinTable(name = "course_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_details_id")
    private StudentDetails studentDetails;

    //=========== gett sett constr ==============

    public Student() {
    }

    public Student(String name, String email, StudentDetails studentDetails) {
        this.name = name;
        this.email = email;
        this.studentDetails = studentDetails;
    }

    public Student(String name, String email, Set<Course> courses, StudentDetails studentDetails) {
        this.name = name;
        this.email = email;
        this.courses = courses;
        this.studentDetails = studentDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public StudentDetails getStudentDetails() {
        return studentDetails;
    }

    public void setStudentDetails(StudentDetails studentDetails) {
        this.studentDetails = studentDetails;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
