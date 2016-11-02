package project.persistence.entities;

/**
 * Created by Þórunn on 11/2/2016.
 */
public class User {
    private String username;
    private int userid;
    private String password;
    private String photo;
    private String school;

    public String getUsername(){return username;};
    public void setUsername(String username){this.username = username;}

    public int getUserId(){return userid;};
    public void setUserId(int userId){this.userId = userId;};

    public String getPassword(){};
    public void setPassword(){};

    public String getPhoto(){};
    public void setPhoto(){};

    public String getSchool(){};
    public void setSchool(){};




    public void changePassword(String oldpass, String newpass){};

    public int addFriend(int userId){return 0;};







}
