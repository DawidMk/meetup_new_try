package pl.dma.appka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.dma.appka.user.UserService;

@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        String username = userService.getLoggedUserName();
        model.addAttribute("LoggedUserName", username);
        return "loginForm";
    }

    @GetMapping("/logout")
    public String logoutUser(Model model){
        return "index";
    }
}
