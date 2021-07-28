package ginko.blog.controller;

import ginko.blog.entity.Article;
import ginko.blog.repository.ArticleRepository;
import ginko.blog.repository.CategoryRepository;
import ginko.blog.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
                                @RequestParam(name = "content") String content) {
        Article article = new Article();
        article.setCategory(categoryRepository.getById(categoryId));
        article.setUser(userRepository.getById(1L));
        article.setHeadline(headline);
        article.setContent(content);
        article.setWritingDate(LocalDateTime.now());
        article.setIsPublished(false);

        articleRepository.save(article);

        return "public/main_page";
    }
}
