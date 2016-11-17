package project.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import project.persistence.entities.User;
import project.service.LoginService;
import project.service.SecurityService;
import project.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by halld on 02-Nov-16.
 */

@Controller
public class LoginController {

    LoginService loginService;

    @Autowired
    SecurityService securityService;

    UserValidator userValidator = new UserValidator();

    @Autowired
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String user(Model model){
        //String test = "testname";
        //model.addAttribute("name",test);
        System.out.println("eftir login: "+SecurityContextHolder.getContext().getAuthentication());
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
    public String signUpPost(@ModelAttribute("SignUp") User SignUp, BindingResult bindingResult, Model model, HttpServletRequest request) {
        userValidator.validate(SignUp, bindingResult);

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "signUp";
        }
        User user = loginService.createUser(SignUp.getUsername(),SignUp.getPassword(), SignUp.getPhoto(), SignUp.getSchool());
        securityService.autologin(user.getUsername(), user.getPasswordConfirm());

        return "redirect:/home";
    }

    @PostMapping(value="/login")
    public String LogInPost(@ModelAttribute("LogIn") User LogIn, Model model) {
        //loginService.logInUser(LogIn.getUsername(),LogIn.getPassword());
        //model.addAttribute("LogIn");
        securityService.autologin(LogIn.getUsername(), LogIn.getPassword());
        return "redirect:/home";
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String loginUserPost(String user, String pass, Model model){
        return "";
    }
}
