package example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Gastvisser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name is missing")
    private String name;

    @JsonBackReference
    @ManyToOne()
    private Redactor redactor;

    @NotNull
    private String hoedanigheid;

//    public Gastvisser(long id, String name, Redactor redactor, String hoedanigheid) {
//        this.id = id;
//        this.name = name;
//        this.redactor = redactor;
//        this.hoedanigheid = hoedanigheid;
//    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Redactor getRedactor() {
        return redactor;
    }

    public void setRedactor(Redactor redactor) {
        this.redactor = redactor;
    }

    public String getHoedanigheid() {
        return hoedanigheid;
    }

    public void setHoedanigheid(String hoedanigheid) {
        this.hoedanigheid = hoedanigheid;
    }
}
