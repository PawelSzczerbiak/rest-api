package pl.szczerbiak.blog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "level")
    private String level;

    // OLD

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "student_id")
////    @Column(name = "student_id")

    // NEW

    @JsonBackReference // IMPORTANT: do not display 'student' field
    @ManyToMany
    @JoinTable(name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> student;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}) // not remove !!!
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

//    @ManyToMany
//    @JoinTable(name = "course_instructor",
//            joinColumns =  @JoinColumn(name = "course_id"),
//            inverseJoinColumns = @JoinColumn(name = "instructor_id"))
//    private List<Instructor> instructors;

    //=========== gett sett constr ==============

    public Course() {
    }

    public Course(String title, String level) {
        this.title = title;
        this.level = level;
    }

    public Course(String title, String level, List<Student> student, Instructor instructor) {
        this.title = title;
        this.level = level;
        this.student = student;
        this.instructor = instructor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}

