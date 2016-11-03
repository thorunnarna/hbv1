package project.persistence.entities;

import java.util.List;

/**
 * Created by Þórunn on 11/2/2016.
 */
public class User {
    private List<User> friends;
    private String username;
    private int userId;
    private String photo;
    private String school;

    public List<User> getFriends(){return friends;}
    public void addFriend(User user){friends.add(user);}
    public void removeFriend(User user){friends.remove(user);}

    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}

    public int getUserId(){return userId;}
    public void setUserId(int userId){this.userId = userId;}

    public String getPhoto(){return photo;}
    public void setPhoto(String photo){this.photo = photo;}

    public String getSchool(){return school;}
    public void setSchool(String school){this.school = school;}











}
