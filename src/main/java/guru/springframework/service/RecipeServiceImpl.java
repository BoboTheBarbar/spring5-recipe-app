package guru.springframework.service;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service

public class RecipeServiceImpl implements RecipeService {

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
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

    @Override
    public Optional<Recipe> findByID(Long id) {
        return this.recipeRepository.findById(id);
    }
}
