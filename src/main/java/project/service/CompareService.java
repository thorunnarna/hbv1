package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.LocalDateTimeParser;
import org.springframework.stereotype.Service;
import project.persistence.entities.*;
import project.persistence.repositories.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by halld on 02-Nov-16.
 */

@Service
public class CompareService {

    Repository repository;

    public CompareService(){repository = new Repository();}

    public List<String> compareScheduleGroup(int grpId, int weekNo, int year){
        Group group = repository.findGroup(grpId);
        List<User> members = group.getMembers();
        List<ScheduleItem> items = new ArrayList<>();
        for (User u:members) {
            List<ScheduleItem> item = repository.findItemsByUserWeek(u.getUserId(),weekNo,year);
            for (ScheduleItem s:item) {
                items.add(s);
            }
        }

        List <String> timeSlots = createTimeSlots();

        List<String> nonFreeSlots = new ArrayList<>();
        for (String slot : timeSlots) {
            for (ScheduleItem item : items) {
                LocalTime slotTime = LocalTime.parse(slot);
                if(slotTime.compareTo(item.getStartTime().toLocalTime())>=0 && slotTime.compareTo(item.getEndTime().toLocalTime())<=0) {
                    if(!nonFreeSlots.contains(LocalDateTime.of(item.getStartTime().toLocalDate(), slotTime).toString())) {
                        nonFreeSlots.add(LocalDateTime.of(item.getStartTime().toLocalDate(), slotTime).toString());
                    }
                }
            }
        }

        return nonFreeSlots;
    }

    public List<String> compareSchedules(int user1, int user2, int weekNo, int year){
        List<ScheduleItem> items1 = repository.findItemsByUserWeek(user1,weekNo, year);
        List<ScheduleItem> items2 = repository.findItemsByUserWeek(user2,weekNo, year);
        for (ScheduleItem s:items2) {
            items1.add(s);
        }

        List <String> timeSlots = createTimeSlots();

        List<String> nonFreeSlots = new ArrayList<>();
        for (String slot : timeSlots) {
            for (ScheduleItem item : items1) {
                LocalTime slotTime = LocalTime.parse(slot);
                if(slotTime.compareTo(item.getStartTime().toLocalTime())>=0 && slotTime.compareTo(item.getEndTime().toLocalTime())<=0) {
                    if(!nonFreeSlots.contains(LocalDateTime.of(item.getStartTime().toLocalDate(), slotTime).toString())) {
                        nonFreeSlots.add(LocalDateTime.of(item.getStartTime().toLocalDate(), slotTime).toString());
                    }
                }
            }
        }
        return nonFreeSlots;
    }

    public User findUserByName(String username) {
        User user = repository.findUsersByName(username);
        return user;
    }

    public int findWeekNo(LocalDateTime LDT){
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        int weekNumber = LDT.get(woy);
        if (LDT.getDayOfWeek().getValue() == 7) weekNumber = weekNumber-1;
        return weekNumber;
    }

    public List<String> createTimeSlots() {
        List<String> timeSlots = new ArrayList<>();
        for (int i = 6; i<=20; i++){
            for (int k = 0; k <= 5; k++ ){
                if(i<10) {
                    timeSlots.add("0"+i+":"+k+"0");
                }
                else timeSlots.add(""+i+":"+k+"0");
            }
        }
        return timeSlots;
    }
}
