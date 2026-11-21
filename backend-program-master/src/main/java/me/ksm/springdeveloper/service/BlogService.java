package me.ksm.springdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.ksm.springdeveloper.dao.Article;
import me.ksm.springdeveloper.dto.ArticleCreationRequest;
import me.ksm.springdeveloper.dto.ArticleResponse;
import me.ksm.springdeveloper.dto.ArticleUpdateRequest;
import me.ksm.springdeveloper.repository.BlogRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(ArticleCreationRequest articleCreationRequest) {
        return blogRepository.save(articleCreationRequest.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void deleteById(Long id) {
        blogRepository.deleteById(id);
    }

    public Article getById(@PathVariable Long id) { return blogRepository.findById(id).get(); }

    @Transactional
    public Article updateArticle(Long id, ArticleUpdateRequest req) {
        Article article = blogRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Article not found"));
        article.update(req.getTitle(), req.getContent());
        return article;
    }
}