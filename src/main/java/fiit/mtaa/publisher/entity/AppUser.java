package fiit.mtaa.publisher.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class AppUser extends AbstractEntity {

    @Column(unique = true)
    protected String userName;

    protected String firstName;

    protected String lastName;

    protected String passwordHash;

    @ManyToMany
    @JoinTable(name = "app_user_liked_articles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "article_id") })
    protected List<Article> likedArticles;

    protected byte[] photo;

    @OneToMany(mappedBy = "article", cascade=CascadeType.PERSIST)
    protected List<Comment> comments;

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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public List<Article> getLikedArticles() {
        return likedArticles;
    }

    public void setLikedArticles(List<Article> likedArticles) {
        this.likedArticles = likedArticles;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
