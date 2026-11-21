package me.ksm.springdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ksm.springdeveloper.dao.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ArticleCreationRequest {
    private String title;
    private String content;
    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}