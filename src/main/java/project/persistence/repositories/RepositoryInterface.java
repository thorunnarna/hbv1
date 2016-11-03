package project.persistence.repositories;

import project.persistence.entities.*;

import java.time.*;
import java.util.List;


public interface RepositoryInterface {

    List<User> findAllUsers();

    User findUsersByName(String username);

    List<ScheduleItem> findItemsByNameWeek(String name, int weekNo);

    int createUser(String username, String password, String photo, String school);

    void editUser(int userId, String username, String password, String photo, String school);

    int createItem(String title, String userId, LocalDate startTime, LocalDate endTime,
                   int weekNo, int year, String location, String color, String description);

    void deleteItem(int itemId);

    void editItem(int itemId, String title, String userId, LocalDate startTime, LocalDate endTime, int weekNo, int year,
                  String location, String color, String description);

    Group findGroup(int grpId);

    int createGroup(String grpName, List<User> members);

    void deleteGroup(int grpId);

    void editGroupName(int grpId, String grpName);

    void addGroupMember(User user);

    void removeGroupMember(int userId);

    int createFriendship(int userId1, int userId2);

    void deleteFriendship(int friendshipId);

    int createFilter(String filterName, String username);

    void deleteFilter(int filterId);

}
