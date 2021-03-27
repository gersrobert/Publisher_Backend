package fiit.mtaa.publisher.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class AppUser extends AbstractEntity {
    // protected String sub;
	// protected String givenName;
	// protected String familyName;
	// protected String nickname;
	// protected String name;
	// protected String picture;
	// protected String locale;
	// protected String updatedAt;

    @Column(unique = true)
    protected String subject;

    protected String userName;

    protected String firstName;

    protected String lastName;


    @ManyToMany
    @JoinTable(name = "app_user_liked_articles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "article_id") })
    protected List<Article> likedArticles;

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
