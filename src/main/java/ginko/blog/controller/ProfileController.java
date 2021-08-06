package ginko.blog.controller;

import ginko.blog.entity.User;
import ginko.blog.repository.ArticleRepository;
import ginko.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Controller
@RequestMapping(path = "/profile")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping
    public String showProfile(Authentication authentication, Model model) {
        if (authentication != null) {
            User user = userRepository.getByLogin(authentication.getName());
            model.addAttribute("user", user);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
            model.addAttribute("formatter", dateTimeFormatter);
            model.addAttribute("articles", articleRepository.getArticlesByUser(user));
            return "public/profile_page";
        }

        return "public/main_page";
    }
}
