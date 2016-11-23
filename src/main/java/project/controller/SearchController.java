package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.persistence.entities.Group;
import project.persistence.entities.User;
import project.service.SearchService;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by halld on 02-Nov-16.
 */

@Controller
public class SearchController {
    public class UserHolder {
        User user;
        boolean friendship;

        public User getUser() {
            return user;
        }

        public boolean isFriendship() {
            return friendship;
        }
    }

    SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }

    @RequestMapping(value="/search")
    public String viewGetListOfUsers(Model model){
        if (SecurityContextHolder.getContext().getAuthentication() == null ) {
            return "redirect:/";
        }
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser = searchService.findByName(loggedInUsername);
        List<User> users = searchService.findAll();
        List<UserHolder> userHolders = new ArrayList<>();
        List<Group> groups = loggedInUser.getGroups();
        model.addAttribute("groupList", groups);
        for(User user:users ) {
            UserHolder uh = new UserHolder();
            uh.user = user;
            System.out.println(user);
            uh.friendship = searchService.checkIfFriend(user, loggedInUser);
            userHolders.add(uh);
        }
        model.addAttribute("users", userHolders);

        return "Search";
    }

    @RequestMapping(value="/search/q")
    public String getSearchByName(@RequestParam("username") String username, Model model){
        if (SecurityContextHolder.getContext().getAuthentication() == null ) {
            return "redirect:/";
        }
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser = searchService.findByName(loggedInUsername);
        System.out.println(username);
        User user = searchService.findByName(username);
        if(user.getUsername() == null) {
            return "Search";
        }
        boolean friendship = searchService.checkIfFriend(user, loggedInUser);
        System.out.println("í controller: "+user.getUsername());
        List<Group> groups = loggedInUser.getGroups();
        System.out.println(groups.get(0).getGrpName());
        model.addAttribute("groupList", groups);
        List<UserHolder> users = new ArrayList<>();
        UserHolder userHolderItem  = new UserHolder();
        userHolderItem.user = user;
        userHolderItem.friendship = friendship;
        users.add(userHolderItem);
        model.addAttribute("users", users);

        return "Search";
    }

    @RequestMapping(value="/search/addFriend", method = RequestMethod.POST)
    public String addFriendPost(@RequestParam("userId") int userId) {
        if (SecurityContextHolder.getContext().getAuthentication() == null ) {
            return "redirect:/";
        }
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser = searchService.findByName(loggedInUsername);
        User user2 = searchService.findByUserId(userId);
        if(!searchService.checkIfFriend(loggedInUser, user2)) {
            searchService.createFriendship(loggedInUser.getUserId(), userId);
        }
        return "redirect:/search";
    }

    @RequestMapping(value="/search/addToGroup", method = RequestMethod.POST)
    public String addToGroup(@RequestParam("addToGroup") int grpId, @RequestParam("addMember") int userId) {
        if (SecurityContextHolder.getContext().getAuthentication() == null ) {
            return "redirect:/";
        }
        Group group = searchService.findGroup(grpId);
        User user = searchService.findByUserId(userId);
        searchService.addGroupMemeber(grpId, userId);
        group.addMember(user);
        System.out.println("id:" + group.getGrpId());
        return "redirect:/search";
    }
}
