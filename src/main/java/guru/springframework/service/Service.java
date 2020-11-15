package guru.springframework.service;

import guru.springframework.domain.Recipe;

import java.util.List;

@org.springframework.stereotype.Service
public interface Service {

    public List<Recipe> findRecipes();
}
