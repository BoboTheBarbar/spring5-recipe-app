package guru.springframework.controller;

import guru.springframework.domain.Recipe;
import guru.springframework.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import static java.lang.Long.parseLong;

@Controller
@Slf4j
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/show/{recipeId}")
    public String showRecipe(@PathVariable String recipeId, Model model) {
        Optional<Recipe> recipe = recipeService.findByID(parseLong(recipeId));
        model.addAttribute("recipe", recipe.get());
        return "recipe/show";
    }


}
