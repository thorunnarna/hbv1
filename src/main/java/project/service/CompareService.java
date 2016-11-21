package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.*;
import project.persistence.repositories.Repository;

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
public class CompareService {

    Repository repository;

    public CompareService(){repository = new Repository();}

    public List<ScheduleItem> compareScheduleGroup(int grpId, int weekNo, int year){
        Group group = repository.findGroup(grpId);
        List<User> members = group.getMembers();
        List<ScheduleItem> items = new ArrayList<>();
        for (User u:members) {
            List<ScheduleItem> item = repository.findItemsByUserWeek(u.getUserId(),weekNo,year);
            for (ScheduleItem s:item) {
                items.add(s);
            }
        }
        return items;
    }

    public List<ScheduleItem> compareSchedules(int user1, int user2, int weekNo, int year){
        List<ScheduleItem> items1 = repository.findItemsByUserWeek(user1,weekNo, year);
        List<ScheduleItem> items2 = repository.findItemsByUserWeek(user2,weekNo, year);
        for (ScheduleItem s:items2) {
            items1.add(s);
        }

        return items1;
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

}
