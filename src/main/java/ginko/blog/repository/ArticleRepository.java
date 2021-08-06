package ginko.blog.repository;

import ginko.blog.entity.Article;
import ginko.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    List<Article> getArticlesByUser(User user);
}
