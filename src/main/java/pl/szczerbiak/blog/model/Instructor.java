package pl.szczerbiak.blog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

//@Data // Lombok: gett, sett and Object method (toString, hashCode)
//@AllArgsConstructor // default access - PUBLIC
//@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "instructor",
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH}) // not remove !!!
    private Set<Course> courses;

//    @ManyToMany
//    @JoinTable(name = "course_instructor",
//            joinColumns = @JoinColumn(name = "instructor_id"),
//            inverseJoinColumns = @JoinColumn(name = "course_id"))
//    private List<Course> courses;

    //=========== gett sett constr ==============

    public Instructor() {
    }

    public Instructor(String name) {
        this.name = name;
    }

    public Instructor(String name, Set<Course> courseSet) {
        this.name = name;
        this.courses = courseSet;
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

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
