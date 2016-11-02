package project.service;

import project.persistence.entities.ScheduleItem;
import project.persistence.repositories.Repository;

/**
 * Created by halld on 02-Nov-16.
 */
public class ScheduleService {

    Repository  repository;

    public ScheduleService(Repository repository){
        this.repository=repository;
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
