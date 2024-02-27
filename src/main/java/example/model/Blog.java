package example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "URL is missing")
    private String url;

//    @NotNull(message = "title cannot be null")
    private String title;

    private Date redDate;

    @NotBlank(message = "contact is missing")
    private String contact;

    private boolean isNew;

    @NotNull(message = "status can not be empty")
    @Enumerated(EnumType.STRING)
    private Status status;

//    @NotNull(message = "comment cannot be null")
    private String comment;
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mening> menings;

    @ElementCollection
    private List<Integer> vangsten;

    public Blog(long id, String url, String title, boolean isNew, Date redDate, String contact, Status status, String comment) {
        this.id = id;
        this.url = url;
        this.title = (title == null || title.isBlank()) ? url : title;
        this.isNew = isNew;
        this.redDate = redDate;
        this.contact = contact;
        this.status = status;
        this.comment = comment;
    }

    public Blog(String url, String contact, Status status) {
        this.url = url;
        this.contact = contact;
        this.status = status;
    }

    public Blog() {
    }

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getRedDate() {
        return redDate;
    }

    public void setRedDate(String date) {
        this.redDate = Date.from(LocalDate.parse(date).atStartOfDay().toInstant(ZoneOffset.UTC));
    }
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(boolean aNew) {
        isNew = aNew;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
