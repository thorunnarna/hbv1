package project.persistence.entities;
import java.util.*;

/**
 * Created by Þórunn on 11/2/2016.
 */
public class Schedule {
    private User user;
    private List<ScheduleItem> items;

    public List<ScheduleItem> getItems(){return items;}
    public void addItem(ScheduleItem item){items.add(item);}
    public void removeItem(ScheduleItem item){items.remove(item);}

    public void setUser(User user){this.user=user;}
    public User getUser() {return user;}

}
