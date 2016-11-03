package project.service;

import project.persistence.repositories.Repository;
import project.persistence.entities.*;


/**
 * Created by halld on 02-Nov-16.
 */
public class SearchService {
    Repository repository;

    public SearchService() {
        repository = new Repository();
    }

    public User[] findAll(){repository.findAll();}

    public User[] findByName(String username){repository.findUsersByName(username);}

    public int createFriendship(int userId1, int userId2){}
}
