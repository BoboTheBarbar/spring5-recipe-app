package guru.springframework.controller;

import guru.springframework.domain.Recipe;
import guru.springframework.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
public class IndexController {

    private RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/index", "/"})
    public String getIndexPage(Model model) {
        log.debug("Requestmapping to " + this.getClass().toString());
        List<Recipe> recipes = recipeService.findRecipes();
        model.addAttribute("recipes", recipes);
        return "index";
    }
}