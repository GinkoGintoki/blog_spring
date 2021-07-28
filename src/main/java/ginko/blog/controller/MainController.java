package ginko.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(path = "/")
public class MainController {

    @GetMapping
    public String showMainPage(Model model) {
        List<String> animals = new ArrayList<>();
        Collections.addAll(animals, "cat", "lion", "zebra", "fox");
        model.addAttribute("animals", animals);
        return "public/main_page";
    }
}
