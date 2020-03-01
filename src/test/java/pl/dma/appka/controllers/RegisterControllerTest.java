package pl.dma.appka.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import pl.dma.appka.dto.RegisterFormDto;
import pl.dma.appka.user.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class RegisterControllerTest {

    RegisterController registerController;

    @Mock
    Model model;

    @Mock
    UserService userService;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        registerController = new RegisterController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(registerController).build();
    }

}