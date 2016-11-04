package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import project.service.CompareService;

/**
 * Created by halld on 02-Nov-16.
 */

@Controller
public class CompareController {

    CompareService compareService;

    @Autowired
    public CompareController(CompareService compareService){
        this.compareService=compareService;
    }

    @RequestMapping
    public String viewGetComparison(Model model){
        return "";
    }

    @RequestMapping
    public String compareSchedulePost(Model model, int user1, int user2){
        return "";
    }

}
