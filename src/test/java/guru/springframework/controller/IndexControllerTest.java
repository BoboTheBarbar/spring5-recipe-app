package guru.springframework.controller;

import guru.springframework.domain.Recipe;
import guru.springframework.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IndexControllerTest {

    IndexController indexController;

    @Mock
    RecipeService recipeService;
    @Spy
    private Model model;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndexPageReturnsIndexPath() {
        String expected = "index";

        String actual = indexController.getIndexPage(model);

        assertEquals(expected, actual);

    }

    @Test
    public void getIndexPageCallsFindRecipesOnce() {
        indexController.getIndexPage(model);

        verify(recipeService, times(1)).findRecipes();
    }

    @Test
    public void getIndexPageCallsaddAttributeOnce() {
        List<Recipe> list = new ArrayList<>();
        list.add(new Recipe());
        when(recipeService.findRecipes()).thenReturn(list);

        indexController.getIndexPage(model);

        verify(model, times(1)).addAttribute("recipes", list);
    }

    @Test
    public void verifyRecipesWereRecieved() {
        List<Recipe> recipeSet = new ArrayList<Recipe>(2);
        recipeSet.add(new Recipe());
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipeSet.add(recipe);

        when(recipeService.findRecipes()).thenReturn(recipeSet);

        ArgumentCaptor<List<Recipe>> captor = ArgumentCaptor.forClass(List.class);
        indexController.getIndexPage(model);

        verify(model, times(1)).addAttribute(eq("recipes"), captor.capture());
        assertEquals(2, captor.getValue().size());
    }
}