package guru.springframework.controller.Integrationstests;

import guru.springframework.controller.IndexController;
import guru.springframework.domain.Recipe;
import guru.springframework.service.RecipeServiceImpl;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerIntegrationstest {

    @Mock
    RecipeServiceImpl recipeService;

    IndexController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        controller = new IndexController(recipeService);
    }

    @SneakyThrows
    @Test
    public void testGetRecipe() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        List<Recipe> list = new ArrayList<Recipe>();
        list.add(recipe);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        Mockito.when(recipeService.findRecipes()).thenReturn(list);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}
