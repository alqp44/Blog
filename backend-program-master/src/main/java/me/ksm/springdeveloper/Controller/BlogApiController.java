package me.ksm.springdeveloper.Controller;

import lombok.RequiredArgsConstructor;
import me.ksm.springdeveloper.dao.Article;
import me.ksm.springdeveloper.dto.ArticleCreationRequest;
import me.ksm.springdeveloper.dto.ArticleResponse;
import me.ksm.springdeveloper.dto.ArticleUpdateRequest;import me.ksm.springdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogApiController {
    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody ArticleCreationRequest articleCreationRequest) {
        Article savedArticle = blogService.save(articleCreationRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> getAllArticles() {
        /*
        List<Article> articleList = blogService.findAll();
        List<ArticleResponse> articleResponseList = new ArrayList<>();
        for(Article article : articleList) {
            articleResponseList.add(new ArticleResponse(article));
        }
         */
        List<ArticleResponse> articleResponseList = blogService.findAll().stream().map(ArticleResponse::new).toList();
        return ResponseEntity.ok().body(articleResponseList);
    }
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findById(@PathVariable Long id) {
        Article article = blogService.findById(id);
        return ResponseEntity.ok().body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        blogService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(@PathVariable Long id ,
                                                         @RequestBody ArticleUpdateRequest request) {
        Article article = blogService.updateArticle(id, request);
        ArticleResponse articleResponse = new ArticleResponse(article);
        return ResponseEntity.ok().body(articleResponse);
    }
}