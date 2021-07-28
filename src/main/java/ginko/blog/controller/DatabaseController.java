package ginko.blog.controller;

import ginko.blog.entity.Category;
import ginko.blog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/db")
public class DatabaseController {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(path = "/case_1")
    public String categoryCaseOne(@RequestParam long categoryId) {
        return entityManager.find(Category.class, categoryId).getName();
    }

    @GetMapping(path = "case_2")
    public String categoryCaseTwo(@RequestParam long categoryId) {
//        return categoryRepository.getById(categoryId).getName();
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            return optionalCategory.get().getName();
        }
        return "Not found";
    }

    @GetMapping(path = "case_3")
    public List<String> categoryCaseThree() {
        return categoryRepository.findAll().stream().map(Category::getName).collect(Collectors.toList());
    }
}
