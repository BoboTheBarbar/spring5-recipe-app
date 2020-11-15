package guru.springframework.service;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service

public class RecipeService implements Service {

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    private RecipeRepository recipeRepository;

    @Override
    public List<Recipe> findRecipes() {
        Iterable<Recipe> recipesFromRepository = recipeRepository.findAll();

        List<Recipe> recipes = new ArrayList<>();
        recipesFromRepository.forEach(recipes::add);
        return recipes;
    }
}
