package me.ksm.springdeveloper.repository;

import me.ksm.springdeveloper.dao.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}