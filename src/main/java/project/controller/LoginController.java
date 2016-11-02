package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import project.persistence.entities.User;
import project.service.LoginService;

/**
 * Created by halld on 02-Nov-16.
 */

@Controller
public class LoginController {

    LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    public String signUpPost(Model model, User user){
        return "";
    }

    public String loginUserPost(String user, String pass, Model model){
        return "";
    }
}
