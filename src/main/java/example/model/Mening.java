package example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Mening {

    @Id
    @PrimaryKeyJoinColumns({
            @PrimaryKeyJoinColumn(name = "blog_id", referencedColumnName = "id"),
            @PrimaryKeyJoinColumn(name = "redactor_id", referencedColumnName = "id")
    })

    @JsonBackReference
    @ManyToOne()    
    private Blog blog;

    @JsonBackReference
    @ManyToOne()
    private Redactor redactor;

    @NotBlank(message = "Message can not be empty")
    private String message;

//    public Mening(Blog blog, Redactor redactor, String message) {
//        this.blog = blog;
//        this.redactor = redactor;
//        this.message = message;
//    }

    public Blog getBlog() {
        return blog;
    }

    public Redactor getRedactor() {
        return redactor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message; 
    }
}
