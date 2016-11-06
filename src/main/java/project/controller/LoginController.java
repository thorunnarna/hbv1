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

    @RequestMapping(value="/signUp", method = RequestMethod.POST)
    public String signUpPost(@ModelAttribute("SignUp") User Signup, Model model, String username, String password, String photo, String school) {
        User user = loginService.createUser(username, password, photo, school);
        model.addAttribute("Signup", user);
        return "/";
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String loginUserPost(String user, String pass, Model model){
        return "";
    }
}
