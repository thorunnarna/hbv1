package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;
import project.persistence.entities.*;
import project.service.ScheduleService;
import project.service.SearchService;
import project.validator.ItemValidator;

//import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by halld on 02-Nov-16.
 */

@Controller
public class ScheduleController {

    ScheduleService scheduleService;
    SearchService searchService;
    //ItemValidator itemValidator = new ItemValidator();

    @Autowired
    public ScheduleController(ScheduleService scheduleService, SearchService searchService){
        this.scheduleService = scheduleService;
        this.searchService = searchService;
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
                                   @PathVariable("itemId") int itemId, String title, int userId, LocalDateTime startTime,
                                   LocalDateTime endTime, int weekNo, int yearNo, String location, String color,
                                   String description, List<User> taggedUsers, List<String> filters){

        model.addAttribute("scheduleItemEdit", scheduleService.editScheduleItem(itemId, title, userId, startTime,
                endTime, weekNo, yearNo, location, color, description, taggedUsers, filters));
        return "";
    }



    @RequestMapping(value = "/home", method = RequestMethod.POST)
    //@PostMapping(value = "/home")
    public String insertItemPost(@ModelAttribute("scheduleItem") ScheduleItem scheduleItem, @ModelAttribute("date") String date, @ModelAttribute("sTime")String sTime, String eTime, Model model) {
        //itemValidator.validate(scheduleItem, bindingResult);

        //if (bindingResult.hasErrors()) {
          //  System.out.println(bindingResult.getAllErrors());
            //return "Home";
        //}

        String tmpUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User tmpUser = searchService.findByName(tmpUsername);
        int userid = tmpUser.getUserId();

        System.out.println("bla"+ date);
        System.out.println("item"+scheduleItem.getTitle());
        String newDate = scheduleService.changeStringDateToRigthDate("llllllllllllllllll");
        String newSTime = scheduleService.changeformatOfTime("21:30");
        System.out.println("tjekk"+newSTime);
        System.out.println("starttime"+sTime);
        System.out.println("endtime"+eTime);

        //date =;

        //startTime
        //endTime
        //year
        //week
        //"2016-12-01 05:06:00" - formatið sem við viljum



        ScheduleItem scheduleitem = scheduleService.createItem(scheduleItem.getTitle(), userid, scheduleItem.getStartTime(), scheduleItem.getEndTime(),
                scheduleItem.getTaggedUsers(), scheduleItem.getWeekNo(), scheduleItem.getYear(), scheduleItem.getLocation(),
                scheduleItem.getColor(),scheduleItem.getDescription(), scheduleItem.getFilters());


        model.addAttribute("scheduleItem",scheduleitem);
        System.out.println(scheduleService.scheduleItems(1,2,3).get(0));
        model.addAttribute("scheduleItems",scheduleService.scheduleItems(1,2,3));
        return "Home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        System.out.println("control: "+ SecurityContextHolder.getContext().getAuthentication().getName());
        boolean isLoggedIn;
        if (SecurityContextHolder.getContext().getAuthentication().getName() == null ) isLoggedIn = false;
        else isLoggedIn = true;
        String LoggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();
        //LocalDate date = LocalDate.now();
        //LocalDateTime sTime = LocalDateTime.now();
        //LocalDateTime eTime = LocalDateTime.now();
        String date = "";
        String sTime = "";
        String eTime = "";

        List <String> TimeSlots = new ArrayList<String>();
        for (int i = 6; i<=24; i++){
            for (int k = 0; k <= 5; k++ ){
                TimeSlots.add(""+i+":"+k+"0");
            }
        }


        model.addAttribute("timeSlots",TimeSlots);
        model.addAttribute("loggedInUser",LoggedInUser);
        model.addAttribute("loggedInStatus",isLoggedIn);
        model.addAttribute("scheduleItem", new ScheduleItem());
        model.addAttribute("scheduleItems",scheduleService.scheduleItems(1,2,3));
        model.addAttribute("date",date);
        model.addAttribute("sTime",sTime);
        model.addAttribute("eTime",eTime);

        return "Home";
    }

}
