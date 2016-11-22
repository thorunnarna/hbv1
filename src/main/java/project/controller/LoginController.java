package project.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import project.persistence.entities.User;
import project.service.LoginService;
import project.service.SecurityService;
import project.validator.UserValidator;

import java.util.List;

/**
 * Created by halld on 02-Nov-16.
 */

@Controller
public class LoginController {

    LoginService loginService;

    @Autowired
    SecurityService securityService = new SecurityService();

    UserValidator userValidator = new UserValidator();

    @Autowired
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String user(Model model){
        //String test = "testname";
        //model.addAttribute("name",test);
        model.addAttribute("LogIn",new User());
        return "LogIn";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model){
        //String test = "test name";
        model.addAttribute("SignUp", new User());
        model.addAttribute("errors", null);
        model.addAttribute("hasErrors", false);
        return "signUp";
    }


    @PostMapping(value="/signup")
    public String signUpPost(@ModelAttribute("SignUp") User SignUp, BindingResult bindingResult, Model model) {
        userValidator.validate(SignUp, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("hasErrors", true);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "signUp";
        }
        User user = loginService.createUser(SignUp.getUsername(),SignUp.getPassword(), SignUp.getPhoto(), SignUp.getSchool());
        securityService.autologin(user.getUsername(), user.getPasswordConfirm());
        return "redirect:/home";
    }

    @PostMapping(value="/login")
    public String LogInPost(@ModelAttribute("LogIn") User LogIn, Model model) {
        System.out.println("controller: "+LogIn.getPassword());
        boolean isLoggedIn = securityService.autologin(LogIn.getUsername(), LogIn.getPassword());

        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        if (SecurityContextHolder.getContext().getAuthentication() == null && !isLoggedIn) {
            model.addAttribute("loginfail", true);
            return "LogIn";
        }
        return "redirect:/home";
    }

}
