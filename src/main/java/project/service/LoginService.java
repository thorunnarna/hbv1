package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import project.persistence.entities.*;
import project.persistence.repositories.Repository;

/**
 * Created by halld on 02-Nov-16.
 */

@Service
public class LoginService {

    Repository repository;

    @Autowired
    public LoginService() {
        repository = new Repository();
    }

    public void createUser(User user) {}
    public void loginUser(int userId){}
}
