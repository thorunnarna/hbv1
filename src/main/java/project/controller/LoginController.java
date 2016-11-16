package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.persistence.entities.User;
import project.service.LoginService;
import project.service.SecurityService;
import project.validator.UserValidator;

/**
 * Created by halld on 02-Nov-16.
 */

@Controller
public class LoginController {

    LoginService loginService;

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
        return "signUp";
    }

    /*@RequestMapping(value="/signUp", method = RequestMethod.POST)
    public String signUpPost(@ModelAttribute("SignUp") User SignUp, Model model, @ModelAttribute("password") String password) {
        User user = loginService.createUser(SignUp.getUsername(), password, SignUp.getPhoto(), SignUp.getSchool());
        model.addAttribute("SignUp", user);
        return "Index";
    }*/

    @PostMapping(value="/signup")
    public String signUpPost(@ModelAttribute("SignUp") User SignUp, BindingResult bindingResult, Model model) {
        userValidator.validate(SignUp, bindingResult);

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "signUp";
        }
        User user = loginService.createUser(SignUp.getUsername(),SignUp.getPassword(), SignUp.getPhoto(), SignUp.getSchool());
        securityService.autologin(user.getUsername(), user.getPasswordConfirm());
        //model.addAttribute("SignUp", user);
        return "redirect:/";
    }

    @PostMapping(value="/login")
    public String LogInPost(@ModelAttribute("LogIn") User LogIn, Model model) {
        //loginService.logInUser(LogIn.getUsername(),LogIn.getPassword());
        //model.addAttribute("LogIn");

        return "Index";
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String loginUserPost(String user, String pass, Model model){
        return "";
    }
}
