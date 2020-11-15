package guru.springframework.controller;

import guru.springframework.domain.Recipe;
import guru.springframework.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
public class IndexController {

    private Service service;

    public IndexController(Service service) {
        this.service = service;
    }

    @RequestMapping({"", "/index", "/"})
    public String getIndexPage(Model model) {
        log.debug("Requestmapping to " + this.getClass().toString());
        List<Recipe> recipes = service.findRecipes();
        model.addAttribute("recipes", recipes);
        return "index";
    }
}
