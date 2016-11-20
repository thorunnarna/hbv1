package project.persistence.repositories;

import org.springframework.cglib.core.Local;
import project.persistence.entities.Group;
import project.persistence.entities.ScheduleItem;
import project.persistence.entities.User;

import java.sql.Timestamp;
import java.time.*;
import java.util.List;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.postgresql.Driver;
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
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/planguin");
        dataSource.setUsername("postgres");
        dataSource.setPassword("lalli");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<User> findAllUsers(){
        String SQL = "select * from \"user\";";
        List<User> users = jdbcTemplate.query(SQL, new UserMapper());

        SQL="select * from user inner join Friendship on (Friendship.userId1=? or Friendship.userId2=?)";
        for (User u : users) {
            List<User> friends = jdbcTemplate.query(SQL, new Object[]{u.getUserId(),u.getUserId()}, new UserMapper());
            for (User f : friends) {
                u.addFriend(f);
            }
        }
        return users;
    }

    public User findUsersByName(String username){
        String SQL = "select * from \"user\" where username=?;";
        List<User> users = jdbcTemplate.query(SQL, new Object[]{username}, new UserMapper());

        SQL = "select * from User inner join Friendship on (Friendship.userId1=? or Friendship.userId2=?)";
        for (User u : users) {
            List<User> friends = jdbcTemplate.query(SQL, new Object[]{u.getUserId(), u.getUserId()}, new UserMapper());
            for (User f : friends) {
                u.addFriend(f);
            }
        }

        SQL = "select id from Group where userid=?";
        List<Integer> groupids = jdbcTemplate.queryForList(SQL, new Object[]{users.get(0).getUserId()}, Integer.class);
        for (int grpid : groupids) {
            users.get(0).addGroupId(grpid);
        }

        if(users.size()==0) return new User();
        if(users.size()>=1) return users.get(0);
        return null;
    }

    public User findUserById(int userId) {
        String SQL = "select * from \"user\" where id=?";
        List<User> users = jdbcTemplate.query(SQL, new Object[]{userId}, new UserMapper());

        if(users.size()==0) return null;

        User user = users.get(0);
        SQL = "select * from User inner join Friendship on (Friendship.userId1=? or Friendship.userId2=?";
        List<User> friends = jdbcTemplate.query(SQL, new Object[]{userId, userId}, new UserMapper());
        for (User f : friends) {
            user.addFriend(f);
        }
        return user;
    }

    public List<ScheduleItem> findItemsByUserWeek(int userId, int weekNo, int year){
        String SQL = "select * from \"scheduleItem\" where userid = ? and \"weekNo\" = ? and year=?;";
        List<ScheduleItem> items = jdbcTemplate.query(SQL, new Object[]{userId, weekNo, year}, new ItemMapper());

        SQL="select * from Filters where itemId=?";
        for (ScheduleItem i : items) {
            List<String> filters = jdbcTemplate.queryForList(SQL, new Object[]{i.getId()}, String.class);
            for (String f : filters) {
                i.addFilter(f);
            }
        }
        return items;
    }

    public List<ScheduleItem> findItemsByUserWeekFilter(int userId, int weekNo, int year, String filter) {
        String SQL = "select * from \"scheduleitem\" where userid = ? and weekNo = ? and year=? inner join " +
                "Filters on Filters.itemId=ScheduleItem.id where Filters.name=?";
        List<ScheduleItem> items = jdbcTemplate.query(SQL, new Object[]{userId, weekNo, year, filter}, new ItemMapper());

        SQL="select * from Filters where itemId=?";
        for (ScheduleItem i : items) {
            List<String> filters = jdbcTemplate.queryForList(SQL, new Object[]{i.getId()}, String.class);
            for (String f : filters) {
                i.addFilter(f);
            }
        }
        return items;
    }

    public int createUser(String password,String photo, String username, String school){
        String SQL = "insert into \"user\" (password, photo, username, school) values (?,?,?,?);";
        jdbcTemplate.update(SQL, new Object[]{password, photo, username, school});

        //SQL = "select id from \"user\" where username=?";
        //int userid = jdbcTemplate.queryForObject(SQL, new Object[]{username}, Integer.class);
        int userid = getUserByUsername(username);
        return userid;
    }

    public int getUserByUsername(String username){
        String SQL = "select id from \"user\" where username =?;";
        int userid;
        try {
            userid = jdbcTemplate.queryForObject(SQL, new Object[]{username}, Integer.class);
        }
        catch (Exception e){
            return -1;
        }
        return userid;
    }
    public void editUser(int userId, String username, String password, String photo, String school){
        String SQL = "update \"user\" set username = ?, password = ?, photo = ?, school = ? where id = ?;";
        jdbcTemplate.update(SQL, username, password, photo, school, userId);
    }


    public int createItem(String title, int userId, LocalDateTime startTime, LocalDateTime endTime,
                   int weekNo, int year, String location, String color, String description){
        String SQL="insert into \"scheduleitem\" (title, userid, \"startTime\", endTime, weekNo, year, location, color, description) " +
                "values (?,?,?,?,?,?,?,?,?);";
        jdbcTemplate.update(SQL, title, userId, Timestamp.valueOf(startTime), Timestamp.valueOf(endTime), weekNo, year, location, color, description);

        //SQL="select id from ScheduleItem where ";
        //int itemid = jdbcTemplate.queryForObject(SQL, new Object[]{username}, Integer.class);
        return 0;
    }

    public void deleteItem(int itemId){
        String SQL="delete from \"scheduleitem\" where id = ?";
        jdbcTemplate.update(SQL, itemId);
    }

    public void editItem(int itemId, String title, int userId, LocalDateTime startTime, LocalDateTime endTime, int weekNo, int year,
                         String location, String color, String description, List<User> taggedUsers, List<String> filters){
        String SQL="update \"scheduleitem\" set title=?, userid=?, startTime=?, endTime=?, weekNo=?, year=?, location=?, " +
                "color=?, description=? where id=?;";
        jdbcTemplate.update(SQL, title, userId, startTime, endTime, weekNo, year, location, color, description, itemId);

    }

    public void addFilterToItem(int userId, int itemId, String filterName) {
        String SQL ="insert into \"filters\" (userId, itemId, name) values (?,?,?);";
        jdbcTemplate.update(SQL, userId, itemId, filterName);
    }

    public void removeFilterFromItem(int userId, int itemId, String filtername) {
        String SQL = "delete from \"filters\" where name=? and itemId=? and userId=?";
        jdbcTemplate.update(SQL, filtername, itemId, userId);
    }

    public Group findGroup(int grpId) {
        String SQL="select * from \"group\" where id = ?";
        List<Group> groups = jdbcTemplate.query(SQL, new Object[]{grpId}, new GroupMapper());

        if(groups.size()==0) return null;

        Group group = groups.get(0);
        SQL="select * from User inner join Members on User.id = Members.userId and members.grpId = ?";
        List<User> members = jdbcTemplate.query(SQL, new Object[]{grpId}, new UserMapper());
        for (User u : members) {
            group.addMember(u);
        }
        return group;
    }
    public int createGroup(String grpName, List<User> members){
        String SQL="insert into \"group\" (name) output inserted.id values (?)";
        int grpId = jdbcTemplate.update(SQL, grpName);

        SQL="insert into Members (grpId, userId) values (?,?)";
        for (User u : members) {
            jdbcTemplate.update(SQL, new Object[]{grpId, u.getUserId()});
        }
        return grpId;
    }

    public void deleteGroup(int grpId){
        String SQL = "delete from \"group\" where grpId=?";
        jdbcTemplate.update(SQL, grpId);
    }

    public void editGroupName(int grpId, String grpName){
        String SQL = "update \"group\" set name=? where id = ?";
        jdbcTemplate.update(SQL, new Object[]{grpName, grpId});
    }

    public void addGroupMember(int groupId, int userId){
        String SQL="insert into \"members\" (grpId, userId) values (?,?)";
        jdbcTemplate.update(SQL, groupId, userId);
    }

    public void removeGroupMember(int groupId, int userId){
        String SQL="delete from \"members\" where groupId=? and userId=? ";
        jdbcTemplate.update(SQL, userId);
    }

    public void createFriendship(int userId1, int userId2){
        String SQL="insert into \"friendship\" values (?,?)";
        jdbcTemplate.update(SQL, userId1, userId2);
    }

    public void deleteFriendship(int friendshipId){
        String SQL="delete from \"friendship\" where id=?";
        jdbcTemplate.update(SQL,friendshipId);
    }

    public void createFilter(String filterName, int userId, int itemId){
        String SQL="insert into \"filters\" (name, userId, itemId) values (?,?,?)";
        jdbcTemplate.update(SQL, filterName, userId, itemId);
    }

    public void deleteFilter(int filterId){
        String SQL="delete from \"filters\" where id=?";
        jdbcTemplate.update(SQL, filterId);
    }
}
