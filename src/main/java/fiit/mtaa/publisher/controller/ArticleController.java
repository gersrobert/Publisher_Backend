package fiit.mtaa.publisher.controller;

import fiit.mtaa.publisher.bl.service.ArticleService;
import fiit.mtaa.publisher.bl.service.UserService;
import fiit.mtaa.publisher.dto.*;
import fiit.mtaa.publisher.entity.AppUser;
import fiit.mtaa.publisher.exception.ConflictException;
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
public class ArticleController extends AbstractController {

    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDetailedDTO> getArticleById(
            @PathVariable String id,
            @RequestHeader(value = "Authorization", required = false) String userId) {

        var user = userService.checkIfUserExists(userId);

        ArticleDetailedDTO article;
        try {
            article = articleService.getById(UUID.fromString(id), user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).build();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).build();
        }

        return ResponseEntity.ok(article);
    }

    @GetMapping("")
    public ResponseEntity<Page<ArticleSimpleDTO>> getArticles(
            @RequestHeader(name = "Authorization", required = false) String accessToken,
            @RequestParam(required = false, defaultValue = "") String author,
            @RequestParam(required = false, defaultValue = "") String title,
            @RequestParam(required = false, defaultValue = "") String category,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        var user = userService.checkIfUserExists(accessToken);

        var filter = new FilterCriteria();
        filter.setAuthor(author);
        filter.setTitle(title);
        filter.setCategory(category);
        filter.setPage(page);
        filter.setPageSize(pageSize);

        try {
            var articles = articleService.getFiltered(filter, user);
            return ResponseEntity.ok(articles);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping(value = "", headers = "Accept=application/json", produces = "application/json")
    public ResponseEntity<IdDTO> insertArticle(
        @RequestHeader(name = "Authorization", required = false) String accessToken,
        @RequestBody ArticleInsertDTO article) {

        var user = userService.checkIfUserExists(accessToken);

        IdDTO idDTO = new IdDTO();
        try {
            idDTO.setId(articleService.insertArticle(article, user).toString());
            return ResponseEntity.ok(idDTO);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping(value = "/{id}", headers = "Accept=application/json", produces = "application/json")
    public ResponseEntity<IdDTO> updateArticle(
        @PathVariable String id,
        @RequestHeader(name = "Authorization", required = false) String accessToken,
        @RequestBody ArticleInsertDTO article) {

        var user = userService.checkIfUserExists(accessToken);

        IdDTO idDTO = new IdDTO();
        try {
            idDTO.setId(articleService.updateArticle(UUID.fromString(id), article, user).toString());
            return ResponseEntity.ok(idDTO);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<?> deleteArticle(
        @PathVariable String articleId,
        @RequestHeader(name = "Authorization", required = false) String accessToken
        ) {
        var user = userService.checkIfUserExists(accessToken);

        try {
            articleService.deleteArticle(UUID.fromString(articleId), user);
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
    public ResponseEntity<Integer> likeArticle(
            @RequestHeader("Authorization") String userId,
            @PathVariable String articleId) {

        var user = userService.checkIfUserExists(userId);

        try {
            articleService.likeArticle(UUID.fromString(articleId), user);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).build();
        } catch (ConflictException e) {
            return ResponseEntity.status(409).build();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping(value = "/{articleId}/unlike")
    public ResponseEntity<Integer> unlikeArticle(@RequestHeader("Authorization") String userId,
                                                 @PathVariable String articleId) {

        var user = userService.checkIfUserExists(userId);

        try {
            articleService.unlikeArticle(UUID.fromString(articleId), user);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).build();
        } catch (ConflictException e) {
            return ResponseEntity.status(409).build();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }
}
