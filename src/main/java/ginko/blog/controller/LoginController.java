package ginko.blog.controller;

import ginko.blog.entity.User;
import ginko.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping()
    public String showPage() {
        return "public/login_page";
    }

    @PostMapping
    public String login(@RequestParam(name = "login") String login,
                        @RequestParam(name = "password") String password,
                        Model model) {
        if (!userRepository.existsByLogin(login)) {
            model.addAttribute("failed", false);
            return "public/login_page";
        }
        User user = userRepository.findByLogin(login).get();

        if (!user.getPassword().equals(password)) {
            model.addAttribute("failed",false);
            return "public/login_page";
        }

        return "public/main_page";
    }
}
