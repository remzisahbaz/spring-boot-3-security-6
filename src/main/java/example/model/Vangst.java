package example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Vangst {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDate datum;

    @JsonBackReference
    @ManyToOne()
    private Blog blog;



    @JsonBackReference
    @ManyToOne()
    private Redactor redactor;

//    public Vangst(long id, Blog blog, Redactor redactor) {
//        this.id = id;
//        this.blog = blog;
//        this.redactor = redactor;
//    }

    public long getId() {
        return id;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public Blog getBlog() {
        return blog;
    }

    public Redactor getRedactor() {
        return redactor;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public void setRedactor(Redactor redactor) {
        this.redactor = redactor;
    }
}
