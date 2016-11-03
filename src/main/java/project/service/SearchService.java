package project.service;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public SearchService() {
        repository = new Repository();
    }

    public List<User> findAll(){return null;}

    public List<User> findByName(String username){return null;}

    public int createFriendship(int userId1, int userId2){return 0;}
}
