package fiit.mtaa.publisher.controller;

import fiit.mtaa.publisher.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/article")
public class ArticleController extends AbstractController{

    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDetailedDTO> getArticleById(@PathVariable String id, @RequestHeader("Auth-Token") String userId) {
        throw new RuntimeException("Not yet implemented");
    }

    @GetMapping("")
    public ResponseEntity<ArticleSimpleListDTO> getArticles(@RequestHeader("Auth-Token") String userId,
            @RequestParam(required = false, defaultValue = "") String author,
            @RequestParam(required = false, defaultValue = "") String title,
            @RequestParam(required = false, defaultValue = "") String category,
            @RequestParam(required = false, defaultValue = "") String publisher,
            @RequestParam(required = false, defaultValue = "0") Integer lowerIndex,
            @RequestParam(required = false, defaultValue = "-1") Integer upperIndex) {

        throw new RuntimeException("Not yet implemented");
    }

    @GetMapping("/index/{lowerIndex}-{upperIndex}")
    public ResponseEntity<ArticleSimpleListDTO> getArticlesInRange(
            @PathVariable int lowerIndex, @PathVariable int upperIndex, @RequestHeader("Auth-Token") String userId) {
        throw new RuntimeException("Not yet implemented");
    }

    @GetMapping("/author/{authorId}/{lowerIndex}-{upperIndex}")
    public ResponseEntity<ArticleSimpleListDTO> getArticlesByAuthor(
            @PathVariable String authorId, @PathVariable int lowerIndex, @PathVariable int upperIndex, @RequestHeader("Auth-Token") String currentUserId) {
        throw new RuntimeException("Not yet implemented");
    }

    @GetMapping("/publisher/{publisherId}/{page}/{pageSize}")
    public ResponseEntity<ArticleSimpleListDTO> getArticlesByPublisher(
            @PathVariable String publisherId, @PathVariable int page, @PathVariable int pageSize, @RequestHeader("Auth-Token") String currentUserId) {
        throw new RuntimeException("Not yet implemented");
    }

    @PostMapping(value = "", headers = "Accept=application/json", produces = "application/json")
    public ResponseEntity<IdDTO> insertArticle(@RequestBody ArticleInsertDTO article) {
        throw new RuntimeException("Not yet implemented");
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity deleteArticle(@PathVariable String articleId) {
        throw new RuntimeException("Not yet implemented");
    }

    @PostMapping(value = "/comment", headers = "Accept=application/json", produces = "application/json")
    public ResponseEntity insertComment(@RequestBody CommentInsertDTO comment) {
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

    @GetMapping(value = "/{articleId}/export")
    public ResponseEntity<DataDTO> exportArticle(@PathVariable String articleId) {
        throw new RuntimeException("Not yet implemented");
    }
}
