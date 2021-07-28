package ginko.blog.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/test")
public class TestController {

    @GetMapping("/case_1")
    public Object test(@RequestParam Optional<Integer> num1,
                       @RequestParam Optional<Integer> num2) {
        return num1.orElse(0) + " " + num2.orElse(0);
    }

    @GetMapping("/case_2")
    public Object test(@RequestParam(name = "first_name") String firstName,
                       @RequestParam(name = "second_name") String secondName) {
        return firstName + " " + secondName;
    }

    @GetMapping("case_3/{login}")
    public Object test(@PathVariable String login) {
        return login;
    }

    @GetMapping("/case_4")
    public String checkAuth(Authentication authentication) {
        System.out.println(authentication);
        System.out.println(authentication.getName());
        return null;
    }
}
