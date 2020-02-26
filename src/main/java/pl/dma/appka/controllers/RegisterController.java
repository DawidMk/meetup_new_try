package pl.dma.appka.controllers;

import pl.dma.appka.dto.RegisterFormDto;
import pl.dma.appka.exceptions.UserExistsException;
import pl.dma.appka.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model)
    {
        model.addAttribute(new RegisterFormDto());
        return "registerForm";
    }

    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute
            @Valid RegisterFormDto registerFormDto,
            BindingResult result,
            Model model
            ){
        try{
            if(result.hasErrors()){
                System.out.println(registerFormDto.toString());
                return "registerForm";
            }
            userService.saveUserToDb(registerFormDto);
        }catch (UserExistsException e){
            result.rejectValue("email", "666", e.getMessage());
            return "registerForm";
        }
        return "redirect:/thanks";
    }

    @GetMapping("/thanks")
    public String thanksPage(){
        return "thanks";
    }

}
