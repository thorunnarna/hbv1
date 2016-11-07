package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.persistence.entities.User;
import project.service.LoginService;

/**
 * Created by halld on 02-Nov-16.
 */

@Controller
public class LoginController {

    LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String user(Model model){
        String test = "testname";
        model.addAttribute("name",test);
        return "LogIn";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(){
        //String test = "test name";
       // model.addAttribute("name", test);
        return "SignUp";
    }

    @RequestMapping(value="/signUp", method = RequestMethod.POST)
    public String signUpPost(@ModelAttribute("SignUp") User SignUp, Model model, String username, String password, String photo, String school) {
        User user = loginService.createUser(username, password, photo, school);
        model.addAttribute("SignUp", user);
        return "/home";
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String loginUserPost(String user, String pass, Model model){
        return "";
    }
}
