package project.persistence.entities;

/**
 * Created by Þórunn on 11/2/2016.
 */
public class User {
    private String username;
    private int userId;
    private String password;
    private String photo;
    private String school;

    public String getUsername(){return username;};
    public void setUsername(String username){this.username = username;}

    public int getUserId(){return userId;};
    public void setUserId(int userId){this.userId = userId;};

    public String getPassword(){return password;};
    public void setPassword(String password){this.password = password;};

    public String getPhoto(){return photo;};
    public void setPhoto(String photo){this.photo = photo;};

    public String getSchool(){return school;};
    public void setSchool(String school){this.school = school;};


    public void changePassword(String oldpass, String newpass){};

    public int addFriend(int userId){return 0;};







}
