package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.persistence.entities.Schedule;
import project.persistence.entities.User;
import project.service.CompareService;
import project.service.LoginService;

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

    @RequestMapping(value="", method = RequestMethod.GET)
    public String viewGetComparison(Model model){
        String loggedInUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser = compareService.findUserByName(loggedInUserName);
        List<User> friends = loggedInUser.getFriends();
        List<Integer> groups = loggedInUser.getGroups();

        model.addAttribute("friendList", friends);
        model.addAttribute("groupList", groups);
        model.addAttribute("friendComp", new String());
        model.addAttribute("groupComp", new String());
        return "Compare";
    }

    @RequestMapping(value="/comparefriends", method = RequestMethod.POST)
    public String compareSchedulePost(@ModelAttribute("comparedSchedule") Schedule comparedSchedule, Model model,
                                      @PathVariable("userId1") int user1, @PathVariable("userId2") int user2, int weekNo, int year){
        Schedule schedule = compareService.compareSchedules(user1, user2, weekNo, year);
        model.addAttribute("comparedSchedule", schedule);
        return "";
    }

    @RequestMapping(value="/compareGroup", method = RequestMethod.POST)
    public String compareGroupSchedulePost(@ModelAttribute("comparedGroupSchedule") Schedule comparedGroupSchedule,
                                           Model model, @PathVariable("groupId") int grpId, int weekNo, int year){
        Schedule groupSchedule = compareService.compareScheduleGroup(grpId, weekNo, year);
        model.addAttribute("comparedGroupSchedule", groupSchedule);
        return "";
    }


}
