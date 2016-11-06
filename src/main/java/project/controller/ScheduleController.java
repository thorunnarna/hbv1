package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.persistence.entities.*;
import project.service.ScheduleService;

import javax.annotation.security.PermitAll;
import java.util.List;

/**
 * Created by halld on 02-Nov-16.
 */

@Controller
public class ScheduleController {

    ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }

    @RequestMapping(value="/schedule/{userId}", method = RequestMethod.GET)
    public String viewGetScheduleForUser(@PathVariable("userId") int userId, int weekNo, int yearNo, Model model){

        List<ScheduleItem> schedule = scheduleService.scheduleItems(userId, weekNo, yearNo);
        model.addAttribute(schedule);

        return "/";
    }

    @RequestMapping(value = "/schedule/{filter}", method = RequestMethod.GET)
    public String viewGetScheduleByFilters(Model model, @PathVariable("filter") String filters, int userId, int weekNo, int yearNo){
        List<ScheduleItem> scheduleByFilters = scheduleService.scheduleItemsFilters(userId, weekNo, yearNo, filters);
        model.addAttribute(scheduleByFilters);

        return "";
    }

    @RequestMapping(value = "/schedule/edit/{itemId}", method = RequestMethod.POST)
    public String editSchedulePost(@ModelAttribute("scheduleItemEdit") ScheduleItem scheduleItem, Model model,
                                   @PathVariable("itemId") int itemId, String title, int userId, String startTime,
                                   String endTime, int weekNo, int yearNo, String location, String color,
                                   String description, List<User> taggedUsers, List<String> filters){

        model.addAttribute("scheduleItemEdit", scheduleService.editScheduleItem(itemId, title, userId, startTime,
                endTime, weekNo, yearNo, location, color, description, taggedUsers, filters));

        return "";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String insertItemPost(@ModelAttribute("scheduleItem") ScheduleItem scheduleItem, Model model,
                                 String title, int userId, String startTime, String endTime, List<User> taggedUsers,
                                 int weekNo, int year, String location,String color, String description, List<String> filters){

        model.addAttribute("scheduleItem", scheduleService.createItem(title, userId, startTime, endTime,
                taggedUsers, weekNo, year, location, color, description, filters));

        return "";
    }
}
