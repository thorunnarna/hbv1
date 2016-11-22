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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by halld on 02-Nov-16.
 */

@Controller
public class CompareController {

    CompareService compareService;

    @Autowired
    public CompareController(CompareService compareService){
        this.compareService = compareService;
    }

    @RequestMapping(value="/compare", method = RequestMethod.GET)
    public String viewGetComparison(Model model){
        if (SecurityContextHolder.getContext().getAuthentication() == null ) {
            return "redirect:/";
        }
        String loggedInUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser = compareService.findUserByName(loggedInUserName);
        List<User> friends = loggedInUser.getFriends();
        List<Group> groups = loggedInUser.getGroups();

        List <String> timeSlots = new ArrayList<>();
        for (int i = 6; i<=20; i++){
            for (int k = 0; k <= 5; k++ ){
                if(i<10) {
                    timeSlots.add("0"+i+":"+k+"0");
                }
                else timeSlots.add(""+i+":"+k+"0");
            }
        }
        model.addAttribute("comparedSchedule", new ArrayList<String>());
        model.addAttribute("timeSlots", timeSlots);
        model.addAttribute("friendList", friends);
        model.addAttribute("groupList", groups);
        return "Compare";
    }

    @RequestMapping(value="/compareFriends")
    public String compareSchedulePost(Model model, @RequestParam("selectedFriend") int friendId){
        if (SecurityContextHolder.getContext().getAuthentication() == null ) {
            return "redirect:/";
        }

        String loggedInUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser = compareService.findUserByName(loggedInUserName);
        List<User> friends = loggedInUser.getFriends();
        List<Group> groups = loggedInUser.getGroups();

        List <String> timeSlots = new ArrayList<>();
        for (int i = 6; i<=20; i++){
            for (int k = 0; k <= 5; k++ ){
                if(i<10) {
                    timeSlots.add("0"+i+":"+k+"0");
                }
                else timeSlots.add(""+i+":"+k+"0");
            }
        }

        int yearNow = LocalDateTime.now().getYear();
        int weekNow = compareService.findWeekNo(LocalDateTime.now());
        List<String> schedule = compareService.compareSchedules(loggedInUser.getUserId(), friendId, weekNow, yearNow);

        System.out.println("fjöldi item: "+schedule.size());

        model.addAttribute("comparedSchedule", schedule);
        model.addAttribute("timeSlots", timeSlots);
        model.addAttribute("friendList", friends);
        model.addAttribute("groupList", groups);
        return "Compare";
    }

    @RequestMapping(value="/compareGroup", method = RequestMethod.POST)
    public String compareGroupSchedulePost(Model model, @RequestParam("selectedGroup") int grpId){
        if (SecurityContextHolder.getContext().getAuthentication() == null ) {
            return "redirect:/";
        }
        
        String loggedInUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser = compareService.findUserByName(loggedInUserName);
        List<User> friends = loggedInUser.getFriends();
        List<Group> groups = loggedInUser.getGroups();

        List <String> timeSlots = new ArrayList<>();
        for (int i = 6; i<=20; i++){
            for (int k = 0; k <= 5; k++ ){
                if(i<10) {
                    timeSlots.add("0"+i+":"+k+"0");
                }
                else timeSlots.add(""+i+":"+k+"0");
            }
        }

        int yearNow = LocalDateTime.now().getYear();
        int weekNow = compareService.findWeekNo(LocalDateTime.now());

        List<String> groupSchedule = compareService.compareScheduleGroup(grpId, weekNow, yearNow);
        model.addAttribute("comparedSchedule", groupSchedule);
        model.addAttribute("timeSlots", timeSlots);
        model.addAttribute("friendList", friends);
        model.addAttribute("groupList", groups);
        return "Compare";
    }


}
