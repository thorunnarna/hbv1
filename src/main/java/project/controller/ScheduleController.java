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
import project.persistence.repositories.Repository;
import project.service.ScheduleService;
import project.service.SearchService;
import project.validator.ItemValidator;

//import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by halld on 02-Nov-16.
 */

@Controller
public class ScheduleController {

    ScheduleService scheduleService;
    SearchService searchService;
    ItemValidator itemValidator = new ItemValidator();
    //Repository repository


    @Autowired
    public ScheduleController(ScheduleService scheduleService, SearchService searchService){
        this.scheduleService = scheduleService;
        this.searchService = searchService;
        //this.repository = repository;
    }

    @RequestMapping(value = "/scheduleByFilter")
    public String viewGetScheduleByFilters(Model model, @RequestParam("selectedFilter") String filter){

        if (SecurityContextHolder.getContext().getAuthentication() == null ) {
            return "redirect:/";
        }

        String tmpUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User tmpUser = searchService.findByName(tmpUsername);
        int userId = tmpUser.getUserId();

        int yearNow = LocalDateTime.now().getYear();
        int weekNow = scheduleService.findWeekNo(LocalDateTime.now());
        List <String> TimeSlots = scheduleService.getTimeSlots();
        List <String> Filters = scheduleService.createfilterList();
        List<User> friendList = tmpUser.getFriends();
        List<Group> groupList = tmpUser.getGroups();

        List<ScheduleItem> scheduleByFilters = scheduleService.scheduleItemsFilters(userId, weekNow, yearNow, filter);
        model.addAttribute("filters",Filters);
        model.addAttribute("timeSlots",TimeSlots);
        model.addAttribute("scheduleItem",new ScheduleItem());
        model.addAttribute("friends", friendList);
        model.addAttribute("groups", groupList);

        if(filter.equals("1")) {
            return "redirect:/home";
        }
        model.addAttribute("scheduleItems", scheduleByFilters);

        return "Home";
    }

    /*@RequestMapping(value = "/schedule/edit/{itemId}", method = RequestMethod.POST)
    public String editSchedulePost(@ModelAttribute("scheduleItemEdit") ScheduleItem scheduleItem, Model model,
                                   @PathVariable("itemId") int itemId, String title, int userId, LocalDateTime startTime,
                                   LocalDateTime endTime, int weekNo, int yearNo, String location, String color,
                                   String description, List<User> taggedUsers, List<String> filters){

        model.addAttribute("scheduleItemEdit", scheduleService.editScheduleItem(itemId, title, userId, startTime,
                endTime, weekNo, yearNo, location, color, description, taggedUsers, filters));
        return "";
    }*/

    @RequestMapping(value="/deleteItem")
    public String deleteItemPost(@RequestParam("itemId") int itemId, Model model) {
        scheduleService.removeItem(itemId);
        return "redirect:/home";
    }

    @RequestMapping(value="/createGroup")
    public String createGroup(@RequestParam("grpName") String grpName, Model model) {
        String LoggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = scheduleService.findUserByUsername(LoggedInUser);
        List<User> members = new ArrayList<>();
        members.add(user);

        int yearNow = LocalDateTime.now().getYear();
        int weekNow = scheduleService.findWeekNo(LocalDateTime.now());
        List <String> Filters = scheduleService.createfilterList();
        List <String> TimeSlots = scheduleService.getTimeSlots();
        List<User> friendList = user.getFriends();
        List<Group> groupList = user.getGroups();

        model.addAttribute("friends", friendList);
        model.addAttribute("groups", groupList);
        model.addAttribute("filters",Filters);
        model.addAttribute("timeSlots",TimeSlots);
        model.addAttribute("scheduleItem",new ScheduleItem());
        model.addAttribute("scheduleItems",scheduleService.scheduleItems(user.getUserId(),weekNow,yearNow));
        if(scheduleService.createGroup(grpName, members)) {
            model.addAttribute("groupFail", false);
            return "redirect:/home";
        }

        model.addAttribute("groupFail", true);
        return "Home";
    }

    @RequestMapping(value="/deleteGroup")
    public String deleteGroup(@RequestParam("grpId") int grpId, Model model) {
        scheduleService.deleteGroup(grpId);
        return "redirect:/home";
    }


    @RequestMapping(value = "/home", method = RequestMethod.POST)
    //@PostMapping(value = "/home")
    public String insertItemPost(@ModelAttribute("scheduleItem") ScheduleItem scheduleItem, BindingResult bindingResult,Model model) {
        itemValidator.validate(scheduleItem, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("hasErrors", true);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "Home";
        }

        String tmpUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User tmpUser = searchService.findByName(tmpUsername);
        int userid = tmpUser.getUserId();

        List<User> friendList = tmpUser.getFriends();
        List<Group> groupList = tmpUser.getGroups();

        String newDate = scheduleService.changeStringDateToRigthDate(scheduleItem.getdate());
        String newSTime = scheduleService.changeformatOfTime(scheduleItem.getStartstring());
        String newETime = scheduleService.changeformatOfTime(scheduleItem.getEndstring());

        String startTimeforItem = newDate +" "+ newSTime;
        String endTimeforItem = newDate +" "+newETime;

        //boolean retval =scheduleService.checkTime(startTimeforItem, endTimeforItem);
        //System.out.println("retval: "+retval);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startdateTime = LocalDateTime.parse(startTimeforItem,formatter);
        LocalDateTime enddateTime = LocalDateTime.parse(endTimeforItem,formatter);

        int year = scheduleService.findYear(scheduleItem.getdate());
        int weekNo = scheduleService.findWeekNo(startdateTime);



         scheduleService.createItem(scheduleItem.getTitle(), userid, startdateTime, enddateTime,
                scheduleItem.getTaggedUsers(), weekNo, year, scheduleItem.getLocation(),
                scheduleItem.getColor(),scheduleItem.getDescription(), scheduleItem.getFilter());

        List <String> TimeSlots = scheduleService.getTimeSlots();

        //hreinsa breytur fyrir næsta item
        ScheduleItem scheduleitem = new ScheduleItem();

        int yearNow = LocalDateTime.now().getYear();
        int weekNow = scheduleService.findWeekNo(LocalDateTime.now());
        //System.out.println("post " +String.valueOf(weekNow));

        List <String> Filters = scheduleService.createfilterList();

        model.addAttribute("friends", friendList);
        model.addAttribute("groups", groupList);
        model.addAttribute("filters",Filters);
        model.addAttribute("timeSlots",TimeSlots);
        model.addAttribute("scheduleItem",scheduleitem);
        model.addAttribute("scheduleItems",scheduleService.scheduleItems(userid,weekNow,yearNow));
        return "Home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        boolean isLoggedIn;
        if (SecurityContextHolder.getContext().getAuthentication() == null ) {
            return "redirect:/";
        }
        else isLoggedIn = true;
        String LoggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();

        List <String> TimeSlots = scheduleService.getTimeSlots();

        int yearNow = LocalDateTime.now().getYear();
        int weekNow = scheduleService.findWeekNo(LocalDateTime.now());
        //System.out.println("get "+String.valueOf(weekNow));
        User user = scheduleService.findUserByUsername(LoggedInUser);
        int userid= user.getUserId();
        //System.out.println(userid);

        List <String> Filters = scheduleService.createfilterList();
        List<User> friendList = user.getFriends();
        List<Group> groupList = user.getGroups();

        model.addAttribute("filters",Filters);

        model.addAttribute("friends", friendList);
        model.addAttribute("groups", groupList);
        model.addAttribute("timeSlots",TimeSlots);
        model.addAttribute("loggedInUser",LoggedInUser);
        model.addAttribute("loggedInStatus",isLoggedIn);
        model.addAttribute("scheduleItem", new ScheduleItem());
        model.addAttribute("scheduleItems",scheduleService.scheduleItems(userid,weekNow,yearNow));
        model.addAttribute("groupFail", false);
        //model.addAttribute("date",date);
        //model.addAttribute("sTime",sTime);
        //model.addAttribute("eTime",eTime);

        return "Home";
    }

}
