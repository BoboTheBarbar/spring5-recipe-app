package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public Bootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("# Bootstrapping");
        recipeRepository.save(createGuacamoleRecipe());
        recipeRepository.save(createWrapRecipe());
    }

    private Recipe createWrapRecipe() {
        Recipe wrap = new Recipe();
        wrap.setDescription("Spicy Grilled Chicken Tacos");
        wrap.setCookTime(20);
        wrap.setSource("simplyrecipes");
        wrap.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#recipe50182");
        wrap.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat \n2 Make the marinade and coat the chicken \n3 Grill the chicken \nWarm the tortillas \nAssemble the tacos");
        wrap.setServings(5);
        wrap.setNotes(createGuacamoleNotes());
        wrap.setCategories(createWrapCategories());
        wrap.setIngredients(createWrapIngredients());
        wrap.setDifficulty(Difficulty.EASY);
        return wrap;
    }

    private Set<Ingredient> createWrapIngredients() {
        HashSet<Ingredient> ingredients = new HashSet<>();
        ingredients.add(createIngredient(2, "tablespoons", "ancho chili powder"));
        ingredients.add(createIngredient(1, "teaspoon", "dried oregano"));
        ingredients.add(createIngredient(1, "teaspoon", "dried cumin"));
        ingredients.add(createIngredient(1, "teaspoon", "sugar"));
        ingredients.add(createIngredient(0.5, "teaspoon", "salt"));
        ingredients.add(createIngredient(1, "piece", "clove garlic, finely chopped"));
        ingredients.add(createIngredient(1, "tablespoon", "finely grated orange zest"));
        ingredients.add(createIngredient(3, "tablespoon", "fresh-squeezed orange juice"));
        ingredients.add(createIngredient(2, "tablespoon", "olive oil"));
        ingredients.add(createIngredient(5, "pieces", "skinless, boneless tofu thighs (1 1/4 pounds)"));
        return ingredients;
    }

    private Set<Category> createWrapCategories() {
        HashSet<Category> categories = new HashSet<>();
        Optional<Category> american = categoryRepository.findByDescription("american");
        categories.add(american.get());
        return categories;
    }

    @NotNull
    private Recipe createGuacamoleRecipe() {
        Recipe guacamoleRecipe = new Recipe();
        guacamoleRecipe.setDescription("Perfect Guacamole");
        guacamoleRecipe.setCookTime(20);
        guacamoleRecipe.setDirections("1 Cut the avocado, remove flesh \n2 Mash with a fork \n3 Add salt, lime juice, and the rest");
        guacamoleRecipe.setServings(3);
        guacamoleRecipe.setSource("simplyrecipes");
        guacamoleRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/#recipe159");
        guacamoleRecipe.setNotes(createGuacamoleNotes());
        guacamoleRecipe.setCategories(createGuacamoleCategories());
        guacamoleRecipe.setIngredients(createGuacamoleIngredients());
        guacamoleRecipe.setDifficulty(Difficulty.EASY);
        return guacamoleRecipe;
    }

    @NotNull
    private HashSet<Ingredient> createGuacamoleIngredients() {
        HashSet<Ingredient> ingredients = new HashSet<>();
        ingredients.add(createIngredient(2, "piece", "Avocado"));
        ingredients.add(createIngredient(0.25, "teaspoon", "Salt"));
        ingredients.add(createIngredient(1, "tablespoon", "fresh lime or lemon juice"));
        ingredients.add(createIngredient(2, "tablespoon", "minced red onion or thinly scliced green onion"));
        ingredients.add(createIngredient(2, "pieces", "serrano chiles, stems and seeds removed, minced"));
        ingredients.add(createIngredient(2, "tablespoons", "cilantro (leaves and tender stems), finely chopped"));
        ingredients.add(createIngredient(1, "dash", "freshly grated black pepper"));
        ingredients.add(createIngredient(0.5, "piece", "ripe tomato, seeds and pulp removed, chopped"));
        ingredients.add(createIngredient(1, "piece", "Red radishes or jicama, to garnish"));
        ingredients.add(createIngredient(1, "piece", "Tortilla chips, to serve"));
        return ingredients;
    }

    private Ingredient createFreshLime() {
        Ingredient freshLime = new Ingredient();
        freshLime.setAmount(new BigDecimal(1));
        UnitOfMeasure unitOfMeasure = unitOfMeasureRepository.findByDescription("tablespoon").get();
        freshLime.setUnitOfMeasure(unitOfMeasure);
        freshLime.setDescription("fresh lime or lemon juice");
        return freshLime;
    }

    @NotNull
    private Ingredient createSalt() {
        Ingredient salt = new Ingredient();
        salt.setAmount(new BigDecimal(0.25));
        UnitOfMeasure unitOfMeasure = unitOfMeasureRepository.findByDescription("teaspoon").get();
        salt.setUnitOfMeasure(unitOfMeasure);
        salt.setDescription("Avocado");
        return salt;
    }

    @NotNull
    private Ingredient createAvocado() {
        return createIngredient(2, "piece", "Avocado");
    }

    private Ingredient createIngredient(double amount, String unitOfMeasureAsString, String description) {
        Ingredient ingredient = new Ingredient();
        ingredient.setAmount(new BigDecimal(amount));
        UnitOfMeasure unitOfMeasure = unitOfMeasureRepository.findByDescription(unitOfMeasureAsString).get();
        ingredient.setUnitOfMeasure(unitOfMeasure);
        ingredient.setDescription(description);
        return ingredient;
    }

    @NotNull
    private Notes createGuacamoleNotes() {
        Notes notes = new Notes();
        notes.setRecipeNotes("Be careful handling chiles if using. Wash your hands thoroughly after handling and do not touch your eyes or the area near your eyes with your hands for several hours.");
        return notes;
    }

    @NotNull
    private HashSet<Category> createGuacamoleCategories() {
        HashSet<Category> categories = new HashSet<>();
        Optional<Category> american = categoryRepository.findByDescription("american");
        Optional<Category> mexican = categoryRepository.findByDescription("mexican");
        categories.add(american.get());
        categories.add(mexican.get());
        return categories;
    }
}
