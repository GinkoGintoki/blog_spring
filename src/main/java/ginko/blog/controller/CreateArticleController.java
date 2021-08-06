package ginko.blog.controller;

import ginko.blog.entity.Article;
import ginko.blog.repository.ArticleRepository;
import ginko.blog.repository.CategoryRepository;
import ginko.blog.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping(path = "/create_article")
public class CreateArticleController {

    final
    CategoryRepository categoryRepository;

    final
    UserRepository userRepository;

    final
    ArticleRepository articleRepository;

    public CreateArticleController(CategoryRepository categoryRepository, UserRepository userRepository, ArticleRepository articleRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    @GetMapping
    public String showCreateArticlePage(Model model) {
        model.addAttribute("categoryModel", categoryRepository.findAll());
        return "/public/create_article_page";
    }

    @PostMapping
    public String createArticle(@RequestParam(name = "headline") String headline,
                                @RequestParam(name = "category") Long categoryId,
                                @RequestParam(name = "content") String content,
                                Authentication authentication) {
        Article article = new Article();
        article.setCategory(categoryRepository.getById(categoryId));
        article.setUser(userRepository.getByLogin(authentication.getName()));
        article.setHeadline(headline);
        article.setContent(content);
        article.setWritingDate(LocalDateTime.now());
        article.setIsPublished(false);

        articleRepository.save(article);

        return "public/main_page";
    }
}
