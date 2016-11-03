package project.persistence.repositories;

import project.persistence.entities.*;

import java.time.*;
import java.util.List;


public interface RepositoryInterface {

    List<User> findAllUsers();

    User findUsersByName(String username);

    List<ScheduleItem> findItemsByNameWeek(String name, int weekNo);

    int createUser(String username, String password, String photo, String school);

    int createItem(String title, String username, LocalDate startTime, LocalDate endTime,
                   int weekNo, int year, String location, String color, String description);

    void deleteItem(int itemId);

    void editItem(String title, String username, LocalDate startTime, LocalDate endTime, int weekNo, int year,
                  String location, String color, String description);

    Group findGroup(int grpId);

    int createGroup(String grpName, List<User> members);

    void deleteGroup(int grpId);

    void editGroup(int grpId, String grpName, List<User> members);

    int createFriendship(int userId1, int userId2);

    void deleteFriendship(int friendshipId);

    int createFilter(String filterName, String username);

    void deleteFilter(int filterId);

}
