package project.persistence.entities;
import java.util.*;

/**
 * Created by Þórunn on 11/2/2016.
 */
public class Group {

    private int grpId;
    private List<User> members;
    private String grpName;

    public int getGrpId(){return grpId;}
    public void setGrpId(int grpId){this.grpId = grpId;}

    public String getGrpName(){return grpName;}
    public void setGrpName(String grpName){this.grpName = grpName;}

    public List<User> getMembers(){return members;}
    public void addMembers(User user){members.add(user);}
    public void removeMembers(User user){members.remove(user);}

    public void changeName(String newname){
        grpName = newname;
    }







}
