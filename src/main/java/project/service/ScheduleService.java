package project.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.IfProfileValue;
import project.persistence.entities.Schedule;
import project.persistence.entities.ScheduleItem;
import project.persistence.entities.User;
import project.persistence.repositories.Repository;

//import java.time.LocalDate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by halld on 02-Nov-16.
 */

@Service
public class ScheduleService {

    Repository  repository;


    public ScheduleService(){
        this.repository = new Repository();
    }

    //public void addItem(int itemId, int  ){}

    public List<ScheduleItem> scheduleItems(int userId, int weekNo, int yearNo){
        return repository.findItemsByUserWeek(userId, weekNo, yearNo);
    }

    public List<ScheduleItem> scheduleItemsFilters(int userId, int weekNo, int yearNo, String filter){
        return repository.findItemsByUserWeekFilter(userId, weekNo, yearNo, filter);
    }

    public ScheduleItem editScheduleItem(int itemId, String title, int userId, LocalDateTime startTime,
                                                LocalDateTime endTime, int weekNo, int year, String location, String color,
                                         String description, List<User> taggedUsers, List<String> filters){
        repository.editItem(itemId, title, userId, startTime, endTime, weekNo, year, location, color, description,
                taggedUsers, filters);
        ScheduleItem itemEdit = new ScheduleItem();
        itemEdit.setId(itemId);
        itemEdit.setTitle(title);
        itemEdit.setUserId(userId);
        itemEdit.setStartTime(startTime);
        itemEdit.setEndTime(endTime);
        itemEdit.setWeekNo(weekNo);
        itemEdit.setYear(year);
        itemEdit.setLocation(location);
        itemEdit.setColor(color);
        itemEdit.setDescription(description);
        itemEdit.setTaggedUsers(taggedUsers);
        itemEdit.setFilters(filters);

        return itemEdit;
    }

    public void removeItem(int itemId){

        repository.deleteItem(itemId);
    }

    public ScheduleItem createItem(String title, int userId, LocalDateTime startTime, LocalDateTime endTime,
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

    public String changeStringDateToRigthDate(String date){
        String newDate = date.substring(6,10) +"-"+date.substring(0,2) +"-"+ date.substring(3,5);
        System.out.println("change date"+newDate);
        return newDate;
    }

    public String changeformatOfTime(String time) {
        String newTime = time + ":00";
        System.out.println("change time"+newTime);
        return newTime;
    }
}
