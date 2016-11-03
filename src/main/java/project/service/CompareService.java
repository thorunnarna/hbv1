package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.repositories.Repository;

/**
 * Created by halld on 02-Nov-16.
 */

@Service
public class CompareService {

    Repository repository;

    @Autowired
    public CompareService(){
        repository = new Repository();
    }

    public void compareScheduleGroup(int grpId, int weekNo, int year){

    }

    public void compareSchedules(int user1, int user2, int weekNo, int year){

    }


}
