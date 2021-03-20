package fiit.mtaa.publisher.controller;

import fiit.mtaa.publisher.bl.service.ArticleService;
import fiit.mtaa.publisher.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@RestController
@RequestMapping(path = "/article")
public class ArticleController extends AbstractController{

    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDetailedDTO> getArticleById(@PathVariable String id, @RequestHeader("Auth-Token") String userId) {
        ArticleDetailedDTO article;
        try {
            article = articleService.getById(UUID.fromString(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).build();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).build();
        }

        return ResponseEntity.ok(article);
    }

    @GetMapping("")
    public ResponseEntity<Page<ArticleSimpleDTO>> getArticles(@RequestHeader("Auth-Token") String userId,
                                                              @RequestParam(required = false, defaultValue = "") String author,
                                                              @RequestParam(required = false, defaultValue = "") String title,
                                                              @RequestParam(required = false, defaultValue = "") String category,
                                                              @RequestParam(required = false, defaultValue = "0") Integer page,
                                                              @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        var filter = new FilterCriteria();
        filter.setAuthor(author);
        filter.setTitle(title);
        filter.setCategory(category);
        filter.setPage(page);
        filter.setPageSize(pageSize);

        try {
            var articles = articleService.getFiltered(filter);
            return ResponseEntity.ok(articles);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping(value = "", headers = "Accept=application/json", produces = "application/json")
    public ResponseEntity<IdDTO> insertArticle(@RequestBody ArticleInsertDTO article) {
        throw new RuntimeException("Not yet implemented");
    }

    @PutMapping(value = "", headers = "Accept=application/json", produces = "application/json")
    public ResponseEntity<IdDTO> updateArticle(@RequestBody ArticleInsertDTO article) {
        throw new RuntimeException("Not yet implemented");
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<?> deleteArticle(@PathVariable String articleId) {
        try {
            articleService.deleteArticle(UUID.fromString(articleId));
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).build();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping(value = "/comment", headers = "Accept=application/json", produces = "application/json")
    public ResponseEntity<?> insertComment(@RequestBody CommentInsertDTO comment) {
        throw new RuntimeException("Not yet implemented");
    }

    @PutMapping(value = "/{articleId}/like")
    public ResponseEntity<Integer> likeArticle(@RequestHeader("Auth-Token") String userId, @PathVariable String articleId) {
        throw new RuntimeException("Not yet implemented");
    }

    @PutMapping(value = "/{articleId}/unlike")
    public ResponseEntity<Integer> unlikeArticle(@RequestHeader("Auth-Token") String userId, @PathVariable String articleId) {
        throw new RuntimeException("Not yet implemented");
    }
}
