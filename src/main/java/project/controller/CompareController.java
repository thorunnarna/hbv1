package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.persistence.entities.Group;
import project.persistence.entities.Schedule;
import project.persistence.entities.ScheduleItem;
import project.persistence.entities.User;
import project.service.CompareService;
import project.service.LoginService;
import project.service.ScheduleService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by halld on 02-Nov-16.
 */

@Controller
public class CompareController {

    CompareService compareService;
    ScheduleService scheduleService;

    @Autowired
    public CompareController(CompareService compareService, ScheduleService scheduleService){
        this.compareService = compareService;
        this.scheduleService = scheduleService;
    }

    @RequestMapping(value="/compare", method = RequestMethod.GET)
    public String viewGetComparison(Model model){
        String loggedInUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser = compareService.findUserByName(loggedInUserName);
        List<User> friends = loggedInUser.getFriends();
        List<Group> groups = loggedInUser.getGroups();


        List <String> timeSlots = scheduleService.getTimeSlots();

        model.addAttribute("comparedSchedule", new ArrayList<ScheduleItem>());
        model.addAttribute("timeSlots", timeSlots);
        model.addAttribute("friendList", friends);
        model.addAttribute("groupList", groups);
        return "Compare";
    }

    @RequestMapping(value="/compareFriends")
    public String compareSchedulePost(Model model, @RequestParam("selectedFriend") int friendId){
        String loggedInUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser = compareService.findUserByName(loggedInUserName);
        List<User> friends = loggedInUser.getFriends();
        List<Group> groups = loggedInUser.getGroups();

        List <String> timeSlots = scheduleService.getTimeSlots();

        int yearNow = LocalDateTime.now().getYear();
        int weekNow = compareService.findWeekNo(LocalDateTime.now());
        List<ScheduleItem> schedule = compareService.compareSchedules(loggedInUser.getUserId(), friendId, weekNow, yearNow);

        System.out.println("fjöldi item: "+schedule.size());

        model.addAttribute("comparedSchedule", schedule);
        model.addAttribute("timeSlots", timeSlots);
        model.addAttribute("friendList", friends);
        model.addAttribute("groupList", groups);
        return "Compare";
    }

    @RequestMapping(value="/compareGroup", method = RequestMethod.POST)
    public String compareGroupSchedulePost(Model model, @RequestParam("selectedGroup") int grpId){
        String loggedInUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser = compareService.findUserByName(loggedInUserName);
        List<User> friends = loggedInUser.getFriends();
        List<Group> groups = loggedInUser.getGroups();

        List <String> timeSlots = scheduleService.getTimeSlots();
        int yearNow = LocalDateTime.now().getYear();
        int weekNow = compareService.findWeekNo(LocalDateTime.now());

        List<ScheduleItem> groupSchedule = compareService.compareScheduleGroup(grpId, weekNow, yearNow);
        model.addAttribute("comparedSchedule", groupSchedule);
        model.addAttribute("timeSlots", timeSlots);
        model.addAttribute("friendList", friends);
        model.addAttribute("groupList", groups);
        return "Compare";
    }


}
