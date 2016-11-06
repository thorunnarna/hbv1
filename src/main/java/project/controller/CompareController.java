package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.persistence.entities.Schedule;
import project.service.CompareService;

/**
 * Created by halld on 02-Nov-16.
 */

@Controller
public class CompareController {

    CompareService compareService;

    public CompareController(CompareService compareService){
        this.compareService = compareService;
    }

    /*@RequestMapping(value="", method = RequestMethod.GET)
    public String viewGetComparison(Model model){
        return "";
    }*/

    @RequestMapping(value="", method = RequestMethod.POST)
    public String compareSchedulePost(Model model, int user1, int user2, int weekNo, int year){
        Schedule schedule = compareService.compareSchedules(user1, user2, weekNo, year);
        model.addAttribute(schedule);
        return "";
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public String compareGroupSchedulePost(Model model, int grpId, int weekNo, int year){
        Schedule groupSchedule = compareService.compareScheduleGroup(grpId, weekNo, year);
        model.addAttribute(groupSchedule);
        return "";
    }


}
