package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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

    public String signUpPost(Model model, User user){
        //loginService.createUser();
        //model.addAttribute("blaaaa", loginService.???);
        return "";
    }

    public String loginUserPost(String user, String pass, Model model){
        return "";
    }
}
