package example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Redactor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name is missing")
    private String name;

    @JsonBackReference
    @OneToOne()
    private Blog blog;

//    public Redactor(long id, String name, Blog blog) {
//        this.id = id;
//        this.name = name;
//        this.blog = blog;
//    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Blog getBlog() {
        return blog;
    }
}
