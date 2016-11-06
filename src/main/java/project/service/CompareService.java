package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.*;
import project.persistence.repositories.Repository;

import java.util.List;

/**
 * Created by halld on 02-Nov-16.
 */

@Service
public class CompareService {

    Repository repository;

    public CompareService(){repository = new Repository();}

    public Schedule compareScheduleGroup(int grpId, int weekNo, int year){
        Group group = repository.findGroup(grpId);
        List<User> members = group.getMembers();
        Schedule schedule= new Schedule();
        for (User u:members) {
            List<ScheduleItem> item = repository.findItemsByUserWeek(u.getUserId(),weekNo,year);
            for (ScheduleItem s:item) {
                schedule.addItem(s);
            }
        }
        return schedule;
    }

    public Schedule compareSchedules(int user1, int user2, int weekNo, int year){
        Schedule schedule = new Schedule();
        List<ScheduleItem> items1 = repository.findItemsByUserWeek(user1,weekNo, year);
        for (ScheduleItem s:items1) {
            Schedule.addItem(s);
        }
        List<ScheduleItem> items2 = repository.findItemsByUserWeek(user2,weekNo, year);
        for (ScheduleItem s:items2) {
            Schedule.addItem(s);
        }

        return schedule;
    }


}
