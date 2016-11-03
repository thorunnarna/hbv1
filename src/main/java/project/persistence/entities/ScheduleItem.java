package project.persistence.entities;
import java.time.*;
import java.util.*;

/**
 * Created by Þórunn on 11/2/2016.
 */
public class ScheduleItem {
    private String title;
    private int id;
    private int userId;
    private LocalDate startTime;
    private List<User> taggedUsers;
    private LocalDate endTime;
    private int weekNo;
    private int year;
    private String location;
    private String color;
    private String description;
    private List<String> filters;
    private String filter;

    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}

    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    public int getUserId(){return userId;}
    public void setUserId(int userId){this.userId = userId;}

    public LocalDate getStartTime(){return startTime;}
    public void setStartTime(LocalDate startTime){this.startTime = startTime;}

    public LocalDate getEndTime(){return endTime;}
    public void setEndTime(LocalDate endTime){this.endTime = endTime;}

    public void setTaggedUsers(List<User> taggedUsers){this.taggedUsers = taggedUsers;}
    public List<User> getTaggedUsers(){return taggedUsers;}
    public void addTaggeduser(User user){taggedUsers.add(user);}
    public void removeTaggedUser(User user){taggedUsers.remove(user);}

    public int getWeekNo(){return weekNo;}
    public void setWeekNo(int weekNo){this.weekNo = weekNo;}

    public int getYear(){return year;}
    public void setYear(int year){this.year = year;}

    public String getLocation(){return location;}
    public void setLocation(String location ){this.location = location;}

    public String getColor(){return color;}
    public void setColor(String color ){this.color = color;}

    public String getDexcription(){return description;}
    public void setDescription(String description ){this.description = description;}

    public void setFilters(List<String> filters){this.filters = filters;}
    public List<String> getFilters(){return filters;}
    public void addFilter(String filter){filters.add(filter);}
    public void removeFilter(String filter){filters.remove(filter);}



    public void changeTime(LocalDate start, LocalDate end){
        startTime = start;
        endTime = end;
    }

}
