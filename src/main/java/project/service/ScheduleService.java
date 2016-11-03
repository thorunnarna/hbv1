package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.ScheduleItem;
import project.persistence.repositories.Repository;

/**
 * Created by halld on 02-Nov-16.
 */

@Service
public class ScheduleService {

    Repository  repository;

    @Autowired
    public ScheduleService(){
        repository = new Repository();
    }

    public void addItem(int itemId){

    }

    public void deleteItem(int itemId){

    }

    public void removeItem(int itemId){

    }

    public void createItem(ScheduleItem scheduleItem){

    }
}
