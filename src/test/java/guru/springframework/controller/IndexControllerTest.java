package guru.springframework.controller;

import guru.springframework.domain.Recipe;
import guru.springframework.service.Service;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IndexControllerTest {

    IndexController indexController;

    @Mock
    Service service;
    @Spy
    private Model model;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.indexController = new IndexController(service);
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

        verify(service, times(1)).findRecipes();
    }

    @Test
    public void getIndexPageCallsaddAttributeOnce() {
        List<Recipe> list = new ArrayList<>();
        list.add(new Recipe());
        when(service.findRecipes()).thenReturn(list);

        indexController.getIndexPage(model);

        verify(model, times(1)).addAttribute("recipes", list);
    }
}