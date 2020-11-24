package guru.springframework.service;

import guru.springframework.domain.Recipe;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public interface RecipeService {

    public List<Recipe> findRecipes();

    Optional<Recipe> findByID(Long id);
}
