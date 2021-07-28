package ginko.blog.controller;

import ginko.blog.entity.Role;
import ginko.blog.entity.User;
import ginko.blog.repository.RoleRepository;
import ginko.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/registration")
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String showRegistrationPage() {
        return "public/registration_page";
    }

    @PostMapping
    public String proceedRegistration(@RequestParam(name = "firstName") String firstName,
                                      @RequestParam(name = "lastName") String lastName,
                                      @RequestParam(name = "login") String login,
                                      @RequestParam(name = "password") String password,
                                      Model model) {
        if (userRepository.existsByLogin(login)) {
            model.addAttribute("fail","fail");
            return "public/registration_page";
        }
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(login);
        user.setPassword(passwordEncoder.encode(password));
        user.setRegisterDate(LocalDateTime.now());
        user.setRole(roleRepository.getById(1L));
        userRepository.save(user);
        return "public/main_page";
    }


}
