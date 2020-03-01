package pl.dma.appka.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import pl.dma.appka.user.User;
import pl.dma.appka.user.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class LoginControllerTest {

    LoginController loginController;

    @Mock
    UserService userService;
    @Mock
    Model model;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        loginController = new LoginController(userService);

    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginForm"));
    }

    @Test
    public void accessLoginPage() {
        //given
        User user = User.builder().id(1).name("name").email("email@email.com").password("password").build();
        //when
        when(userService.getLoggedUserName()).thenReturn(user.getName());
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        String loginPage = loginController.loginPage(model);
        assertEquals("loginForm", loginPage);
        verify(model, times(1)).addAttribute(eq("LoggedUserName"), argumentCaptor.capture());

        String value = argumentCaptor.getValue();

        assertEquals(user.getName(), value);
        //then
    }

    @Test
    public void logoutUser() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/logout"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}