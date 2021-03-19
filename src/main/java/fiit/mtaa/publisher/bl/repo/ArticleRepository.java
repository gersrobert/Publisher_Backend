package fiit.mtaa.publisher.bl.repo;

import fiit.mtaa.publisher.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArticleRepository extends JpaRepository<Article, UUID> {
}
