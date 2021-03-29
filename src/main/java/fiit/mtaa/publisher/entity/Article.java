package fiit.mtaa.publisher.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
public class Article extends AbstractEntity {

    protected String title;

    @ManyToMany
    protected List<Category> categories;

    @OneToMany(mappedBy = "article", cascade=CascadeType.PERSIST)
    protected List<Comment> comments;

    @Type(type="text")
    protected String content;

    protected int likeCount;

    @ManyToOne
    protected AppUser author;

    @ManyToMany
    @JoinTable(name = "app_user_liked_articles",
            joinColumns = { @JoinColumn(name = "article_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    protected List<AppUser> likedUsers;

    @Transient
    protected boolean liked;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public AppUser getAuthor() {
        return author;
    }

    public void setAuthor(AppUser author) {
        this.author = author;
    }

    public List<AppUser> getLikedUsers() {
        return likedUsers;
    }

    public void setLikedUsers(List<AppUser> likedUsers) {
        this.likedUsers = likedUsers;
    }
}
