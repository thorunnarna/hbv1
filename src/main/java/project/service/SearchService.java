package project.service;

import org.springframework.stereotype.Service;
import project.persistence.repositories.Repository;
import project.persistence.entities.*;

import java.util.List;


/**
 * Created by halld on 02-Nov-16.
 */

@Service
public class SearchService {
    Repository repository;

    public SearchService() {

        this.repository = new Repository();
    }

    public List<User> findAll(){
        return repository.findAllUsers();
    }

    public User findByName(String username){
        return repository.findUsersByName(username);
    }

    public boolean createFriendship(int userId1, int userId2){
        repository.createFriendship(userId1,userId2);

        User user1 = repository.findUserById(userId1);
        User user2 = repository.findUserById(userId2);

        user1.addFriend(user2);
        user2.addFriend(user1);

        return true;
    }
}
