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
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
                                   String description, String filter){
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
        item.setFilter(filter);
        repository.createFilter(filter,userId,item.getId());
        return item;

    }

    public User findUserByUsername(String username){
        User finduser = repository.findUsersByName(username);
        return finduser;
    }

    public String changeStringDateToRigthDate(String date){
        String newDate = date.substring(6,10) +"-"+date.substring(0,2) +"-"+ date.substring(3,5);
        return newDate;
    }

    public int findYear(String date){
        String stYear = date.substring(6,10);
        int year = Integer.parseInt(stYear);
        return year;
    }

    public int findWeekNo(LocalDateTime LDT){
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        int weekNumber = LDT.get(woy);
        if (LDT.getDayOfWeek().getValue() == 7) weekNumber = weekNumber-1;
        return weekNumber;
    }

    public String changeformatOfTime(String time) {
        String newTime = time + ":00";
        return newTime;
    }

    public boolean createGroup(String name, List<User> members) {
        if (repository.findGroupByName(name)!=-1) return false;
        repository.createGroup(name, members);
        return true;
    }

    public void deleteGroup(int grpId) {
        repository.deleteGroup(grpId);
    }

    public List <String> getTimeSlots(){
        List <String> TimeSlots = new ArrayList<String>();
        for (int i = 6; i<=20; i++){
            for (int k = 0; k <= 5; k++ ){
                if(i<10) {
                    TimeSlots.add("0"+i+":"+k+"0");
                }
                else TimeSlots.add(""+i+":"+k+"0");
            }
        }
        return TimeSlots;
    }

    public List<String> createfilterList(){
        List <String> Filters = new ArrayList<String>();
        Filters.add("Work");
        Filters.add("School");
        Filters.add("Appointment");
        Filters.add("Others");

        return Filters;
    }

    public boolean checkTime(String starts, String ends){
        System.out.println(starts);
        System.out.println(ends);

        int hss =Integer.parseInt(checkifZero(starts.substring(0,2)));
        int hes =Integer.parseInt(checkifZero(ends.substring(0,2)));
        int mss =Integer.parseInt(starts.substring(3,5));
        int mes =Integer.parseInt(ends.substring(3,5));

        System.out.println("hss"+hss+"hes"+hes+"mss"+mss+"mes"+mes);

        boolean retval = true;

        if (hes < hss)retval = false;
        if (hes == hss) {
            if (mes < mss) retval = false;
        }
        return retval;
    }

    public String checkifZero(String check) {

        if (check.substring(0) == "0");

        return check.substring(1);

    }

}
