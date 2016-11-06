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

    public LoginService() {
        this.repository = new Repository();
    }

    public User createUser(String username, String password, String photo, String school) {
        User user = new User();
        int i = repository.createUser(user.getUsername(), password, photo, school);
        user.setUserId(i);
        user.setPhoto(photo);
        user.setSchool(school);
        return user;
    }
    public void loginUser(int userId){
        ///???
    }
}
