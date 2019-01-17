package com.example.demo;

import com.example.demo.controller.CoinController;
import com.example.demo.service.CoinMarketService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/*
public class CoinControllerTest {
    @Mock
    CoinMarketService coinMarketService;

    @Mock
    Model model;

    CoinController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new CoinController(coinMarketService);
    }

    @Test
    public void getIndexPage() throws Exception {

        String viewName = controller.getIndexPage(model);

        assertEquals("index", viewName);
        verify(coinMarketService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), anySet());
    }
}
*/