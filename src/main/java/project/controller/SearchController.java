package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 * Created by halld on 02-Nov-16.
 */

@Controller
public class SearchController {

    SearchService searchService;

    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }

    public String viewGetListOfUsers(Model model){
        return "";
    }

    public String searchByNamePost(String username, Model model){
        return "";
    }

    public String addFriendPost(Model model, int userId, int friendId){
        return "";
    }
}
