package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.persistence.entities.*;
import project.service.ScheduleService;

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

    @RequestMapping(value="", method = RequestMethod.GET)
    public String viewGetScheduleForUser(int userId, Model model){


        return "";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String viewGetScheduleByFilters(Model model, String[] filters, int userId){
        return "";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String saveSchedulePost(Model model){
        return "";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String insertItemPost(Model model, ScheduleItem scheduleItem){
        return "";
    }
}
