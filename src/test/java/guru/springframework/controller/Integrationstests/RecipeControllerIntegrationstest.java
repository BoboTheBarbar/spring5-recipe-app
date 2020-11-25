package guru.springframework.controller.Integrationstests;

import guru.springframework.controller.RecipeController;
import guru.springframework.domain.Recipe;
import guru.springframework.service.RecipeService;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerIntegrationstest {
    @Mock
    RecipeService recipeService;

    RecipeController recipeController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.recipeController = new RecipeController(recipeService);
    }

    @SneakyThrows
    @Test
    public void testGetRecipe() {

        Recipe recipe = new Recipe();
        recipe.setId(1L);

        when(recipeService.findByID(anyLong())).thenReturn(Optional.of(recipe));

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        mockMvc.perform(get("/recipe/show/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/show"));
    }
}
