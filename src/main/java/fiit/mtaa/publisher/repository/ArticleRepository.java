package fiit.mtaa.publisher.repository;

import fiit.mtaa.publisher.entity.Article;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ArticleRepository extends JpaRepository<Article, UUID>, JpaSpecificationExecutor<Article> {

    static Specification<Article> hasAuthor(String name) {
        name = "%" + name + "%";
        return hasAuthorFirstName(name)
                .or(hasAuthorLastName(name))
                .or(hasAuthorUserName(name));
    }

    static Specification<Article> hasTitle(String name) {
        return (article, query, builder) -> builder
                .like(builder.lower(article.get("title")), "%" + name.toLowerCase() + "%");
    }

    static Specification<Article> hasCategory(String name) {
        return (article, query, builder) -> builder
                .like(builder.lower(article.join("categories").get("name")), "%" + name.toLowerCase() + "%");
    }

    private static Specification<Article> hasAuthorFirstName(String name) {
        return (article, query, builder) -> builder
                .like(builder.lower(article.get("author").get("firstName")), name.toLowerCase());
    }

    private static Specification<Article> hasAuthorLastName(String name) {
        return (article, query, builder) -> builder
                .like(builder.lower(article.get("author").get("lastName")), name.toLowerCase());
    }

    private static Specification<Article> hasAuthorUserName(String name) {
        return (article, query, builder) -> builder
                .like(builder.lower(article.get("author").get("userName")), name.toLowerCase());
    }
}
