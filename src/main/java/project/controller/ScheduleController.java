package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import project.persistence.entities.*;
import project.service.ScheduleService;

/**
 * Created by halld on 02-Nov-16.
 */

@Controller
public class ScheduleController {

    ScheduleService scheduleService;

    public String viewGetScheduleForUser(int userId, Model model){
        return "";
    }

    public String viewGetScheduleByFilters(Model model, String[] filters, int userId){
        return "";
    }

    public String saveSchedulePost(Model model){
        return "";
    }

    public String insertItemPost(Model model, ScheduleItem){
        return "";
    }
}
