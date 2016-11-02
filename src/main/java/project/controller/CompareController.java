package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import project.service.CompareService;

/**
 * Created by halld on 02-Nov-16.
 */

@Controller
public class CompareController {

    CompareService compareService;

    public CompareController(CompareService compareService){
        this.compareService=compareService;
    }

    public String viewGetComparison(Model model){
        return "";
    }

    public String compareSchedulePost(Model model, int user1, int user2){
        return "";
    }

}
