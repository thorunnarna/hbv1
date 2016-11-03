package project.persistence.repositories;

import project.persistence.entities.Group;
import project.persistence.entities.ScheduleItem;
import project.persistence.entities.User;

import java.time.*;
import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import project.persistence.repositories.Mappers.GroupMapper;
import project.persistence.repositories.Mappers.ItemMapper;
import project.persistence.repositories.Mappers.UserMapper;

/**
 * Created by Svava on 03.11.16.
 */
public class Repository implements RepositoryInterface {

    private JdbcTemplate jdbcTemplate;

    public Repository() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:5432/planguin");
        dataSource.setUsername("postgres");
        dataSource.setPassword("lalli");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<User> findAllUsers(){
        String SQL = "select * from User;";
        List<User> users = jdbcTemplate.query(SQL, new UserMapper());
        return users;
    }

    public User findUsersByName(String username){
        String SQL = "select * from User where username=?;";
        User user = (User)jdbcTemplate.queryForObject(SQL, new Object[]{username}, new UserMapper());
        return user;
    }

    public User findUserById(int userId) {
        String SQL = "select * from User where id=?";
        User user = (User)jdbcTemplate.queryForObject(SQL, new Object[]{userId}, new UserMapper());
        return user;
    }

    public List<ScheduleItem> findItemsByUserWeek(int userId, int weekNo){
        String SQL = "select * from ScheduleItem where userid = ? and weekNo = ?;";
        List<ScheduleItem> items = jdbcTemplate.query(SQL, new Object[]{userId, weekNo}, new ItemMapper());
        return items;
    }

    public List<ScheduleItem> findItemsByUserWeekFilter(int userId, int weekNo, String filter) {
        String SQL = "select * from ScheduleItem where userid = ? and weekNo = ? inner join " +
                "Filters on Filters.itemId=ScheduleItem.id where Filters.name=?";
        List<ScheduleItem> items = jdbcTemplate.query(SQL, new Object[]{userId, weekNo, filter}, new ItemMapper());
        return items;
    }

    public int createUser(String username, String password, String photo, String school){
        String SQL = "insert into User (password, photo, username, school) values (?,?,?,?);";
        jdbcTemplate.update(SQL, username, password, photo, school);

        SQL = "select id from User where username = ?;";
        int userid = jdbcTemplate.queryForObject(SQL, new Object[]{username}, Integer.class);
        return userid;
    }

    public void editUser(int userId, String username, String password, String photo, String school){
        String SQL = "update User set username = ?, password = ?, photo = ?, school = ? where id = ?;";
        jdbcTemplate.update(SQL, username, password, photo, school, userId);
    }


    public int createItem(String title, int userId, LocalDate startTime, LocalDate endTime,
                   int weekNo, int year, String location, String color, String description){
        String SQL="insert into ScheduleItem (title, userid, startTime, endTime, weekNo, year, location, color, description) " +
                "output Inserted.id values (?,?,?,?,?,?,?,?,?);";
        int itemid = jdbcTemplate.update(SQL, title, userId, startTime, endTime, weekNo, year, location, color, description);

        //SQL="select id from ScheduleItem where ";
        //int itemid = jdbcTemplate.queryForObject(SQL, new Object[]{username}, Integer.class);
        return itemid;
    }

    public void deleteItem(int itemId){
        String SQL="delete from ScheduleItem where id = ?";
        jdbcTemplate.update(SQL, itemId);
    }

    public void editItem(int itemId, String title, String userId, LocalDate startTime, LocalDate endTime, int weekNo, int year,
                  String location, String color, String description){
        String SQL="update ScheduleItem set title=?, userid=?, startTime=?, endTime=?, weekNo=?, year=?, location=?, " +
                "color=?, description=? where id=?";
        jdbcTemplate.update(SQL, title, userId, startTime, endTime, weekNo, year, location, color, description, itemId);
    }

    public Group findGroup(int grpId) {
        String SQL="select * from Group where id = ?";
        Group group = (Group)jdbcTemplate.queryForObject(SQL, new Object[]{grpId}, new GroupMapper());
        
        SQL="select * from User inner join Members on User.id = Members.userId and members.grpId = ?";
        List<User> members = jdbcTemplate.query(SQL, new Object[]{grpId}, new UserMapper());
        for (User u : members) {
            group.addMember(u);
        }
        return group;
    }
    public int createGroup(String grpName, List<User> members){
        String SQL="insert into Group (name) output Inserted.id values (?)";
        int grpId = jdbcTemplate.update(SQL, grpName);

        //SQL="sækja id á nýja grup";
        //int grpId = jdbcTemplate.queryForObject(SQL, new Object[]{"???"}, Integer.class);

        SQL="insert into Members (grpId, userId) values (?,?)";
        for (User u : members) {
            jdbcTemplate.update(SQL, new Object[]{grpId, u.getUserId()});
        }
        return grpId;
    }

    public void deleteGroup(int grpId){
        String SQL = "delete from Group where grpId=?";
        jdbcTemplate.update(SQL, grpId);
    }

    public void editGroupName(int grpId, String grpName){
        String SQL = "update Group set name=? where id = ?";
        jdbcTemplate.update(SQL, new Object[]{grpName, grpId});
    }

    public void addGroupMember(int groupId, int userId){
        String SQL="insert into Members (grpId, userId) values (?,?)";
        jdbcTemplate.update(SQL, groupId, userId);
    }

    public void removeGroupMember(int groupId, int userId){
        String SQL="delete from Members where groupId=? and userId=? ";
        jdbcTemplate.update(SQL, userId);
    }

    public void createFriendship(int userId1, int userId2){
        String SQL="insert into Friendship output values (?,?)";
        jdbcTemplate.update(SQL, userId1, userId2);
    }

    public void deleteFriendship(int friendshipId){
        String SQL="delete from Friendship where id=?";
        jdbcTemplate.update(SQL,friendshipId);
    }

    public void createFilter(String filterName, int userId, int itemId){
        String SQL="insert into Filters (name, userId, itemId) output Inserted.id values (?,?,?)";
        jdbcTemplate.update(SQL, filterName, userId, itemId);
    }

    public void deleteFilter(int filterId){
        String SQL="delete from Filters where id=?";
        jdbcTemplate.update(SQL, filterId);
    }
}
