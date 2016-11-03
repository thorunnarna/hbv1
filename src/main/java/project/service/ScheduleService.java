package project.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.ScheduleItem;
import project.persistence.entities.User;
import project.persistence.repositories.Repository;

import java.time.LocalDate;
import java.util.List;

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

    public void addItem(int itemId, int userId){

        repository.


    }

    public void deleteItem(int itemId, int userId){

    }
    //adda úr sínu scheduli?
    public void removeItem(int itemId,int userId){

    }

    public ScheduleItem createItem(String title, int userId, LocalDate startTime, LocalDate endTime,
                                   List<User> taggedUsers, int weekNo, int year, String location,String color,
                                   String description, List<String> filters){
        int id = repository.createItem(title, userId, startTime, endTime, weekNo, year, location, color, description);
        ScheduleItem item = new ScheduleItem();
        item.setId(id);
        item.setTitle(title);
        item.setUserId(userId);
        item.setStartTime(startTime);
        item.setEndTime(endTime);
        item.setTaggedUsers(taggedUsers);
        item.setWeekNo(weekNo);
        item.setYear(year);
        item.setLocation(location);
        item.setColor(color);
        item.setDescription(description);
        item.setFilters(filters);
        return item;


    }
}
