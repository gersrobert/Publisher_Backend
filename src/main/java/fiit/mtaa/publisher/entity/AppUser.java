package fiit.mtaa.publisher.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;

@Entity
public class AppUser extends AbstractEntity {
    @Column(unique = true)
    protected String subject;

    protected String userName;

    protected String firstName;

    protected String lastName;

    protected byte[] photo;

    @OneToMany(mappedBy = "article", cascade=CascadeType.PERSIST)
    protected List<Comment> comments;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
