package project.persistence.repositories;

import project.persistence.entities.*;

import java.time.*;
import java.util.List;


public interface RepositoryInterface {

    List<User> findAllUsers();

    User findUsersByName(String username);

    User findUserById(int userId);

    List<ScheduleItem> findItemsByUserWeek(int userId, int weekNo, int year);

    List<ScheduleItem> findItemsByUserWeekFilter(int userId, int weekNo, int year, String filter);

    int createUser(String username, String password, String photo, String school);

    void editUser(int userId, String username, String password, String photo, String school);

    int createItem(String title, int userId, LocalDate startTime, LocalDate endTime,
                   int weekNo, int year, String location, String color, String description);

    void deleteItem(int itemId);

    void editItem(int itemId, String title, String userId, LocalDate startTime, LocalDate endTime, int weekNo, int year,
                  String location, String color, String description);

    Group findGroup(int grpId);

    int createGroup(String grpName, List<User> members);

    void deleteGroup(int grpId);

    void editGroupName(int grpId, String grpName);

    void addGroupMember(int groupId, int userId);

    void removeGroupMember(int groupId, int userId);

    void createFriendship(int userId1, int userId2);

    void deleteFriendship(int friendshipId);

    void createFilter(String filterName, int userId, int itemId);

    void deleteFilter(int filterId);

}
