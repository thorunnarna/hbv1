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
        dataSource.setUsername("arnorv");
        dataSource.setPassword("lalli");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<User> findAllUsers(){
        String SQL = "SELECT * FROM User";
        List<User> users = jdbcTemplate.query(SQL, new UserMapper());
        return users;
    }

    public User findUsersByName(String username){
        String SQL = "username = ?";
        User user = (User)jdbcTemplate.queryForObject(SQL, new Object[]{username}, new UserMapper());
        return user;
    }

    public List<ScheduleItem> findItemsByNameWeek(String name, int weekNo){
        String SQL = "";
        List<ScheduleItem> items = jdbcTemplate.query(SQL, new ItemMapper());
        return items;
    }

    public int createUser(String username, String password, String photo, String school){
        String SQL = "";
        jdbcTemplate.update(SQL, new Object[]{username, password, photo, school});

        SQL = "";
        int userid = jdbcTemplate.queryForObject(SQL, new Object[]{username}, Integer.class);
        return userid;
    }

    public void editUser(String username, String password, String photo, String school){
        String SQL = "";
        jdbcTemplate.update(SQL, new Object[]{username, password, photo, school});
    }

    public int createItem(String title, String username, LocalDate startTime, LocalDate endTime,
                   int weekNo, int year, String location, String color, String description){
        String SQL="";
        jdbcTemplate.update(SQL, new Object[]{title, username, startTime, endTime, weekNo, year, location, color, description});

        SQL="";
        int itemid = jdbcTemplate.queryForObject(SQL, new Object[]{username}, Integer.class);
        return itemid;
    }

    public void deleteItem(int itemId){
        String SQL="";
        jdbcTemplate.update(SQL, itemId);
    }

    public void editItem(String title, String username, LocalDate startTime, LocalDate endTime, int weekNo, int year,
                  String location, String color, String description){
        String SQL="";
        jdbcTemplate.update(SQL, new Object[]{title, username, startTime, endTime, weekNo, year, location, color, description});
    }

    public Group findGroup(int grpId) {
        String SQL="";
        Group group = (Group)jdbcTemplate.queryForObject(SQL, new Object[]{grpId}, new GroupMapper());
        
        SQL="";
        List<User> members = jdbcTemplate.query(SQL, new Object[]{grpId}, new UserMapper());
        for (User u : members) {
            group.addMember(u);
        }
        return group;
    }
    public int createGroup(String grpName, List<User> members){
        String SQL="setja grup inní";
        jdbcTemplate.update(SQL, grpName);

        SQL="sækja id á nýja grup";
        int grpId = jdbcTemplate.queryForObject(SQL, new Object[]{"???"}, Integer.class);

        SQL="setja member og grpid inni members tofluna";
        for (User u : members) {
            jdbcTemplate.update(SQL, new Object[]{grpId, u.getUserId()});
        }
        return grpId;
    }

    public void deleteGroup(int grpId){
        String SQL = "";
        jdbcTemplate.update(SQL, grpId);
    }

    public void editGroupName(int grpId, String grpName){
        String SQL = "";
        jdbcTemplate.update(SQL, new Object[]{grpId, grpName});
    }

    public void addGroupMember(User user){
        String SQL="";
        jdbcTemplate.update(SQL, user.getUserId());
    }

    public void removeGroupMember(int userId){
        String SQL="";
        jdbcTemplate.update(SQL, userId);
    }

    public int createFriendship(int userId1, int userId2){
        String SQL="";
        jdbcTemplate.update(SQL, userId1, userId2);

        SQL="";
        int friendshipId = jdbcTemplate.queryForObject(SQL, new Object[]{userId1, userId2}, Integer.class);
        return friendshipId;
    }

    public void deleteFriendship(int friendshipId){
        String SQL="";
        jdbcTemplate.update(SQL,friendshipId);
    }

    public int createFilter(String filterName, String username){
        String SQL="";
        jdbcTemplate.update(SQL, filterName, username);

        SQL="";
        int filterId = jdbcTemplate.queryForObject(SQL, new Object[]{filterName, username}, Integer.class);
        return filterId;
    }

    public void deleteFilter(int filterId){
        String SQL="";
        jdbcTemplate.update(SQL, filterId);
    }
}
