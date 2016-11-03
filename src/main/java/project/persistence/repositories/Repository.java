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
        Group group = jdbcTemplate.queryForObject();
    }
    public int createGroup(String grpName, List<User> members){return 0;}

    public void deleteGroup(int grpId){}

    public void editGroup(int grpId, String grpName, List<User> members){}

    public int createFriendship(int userId1, int userId2){return 0;}

    public void deleteFriendship(int friendshipId){}

    public int createFilter(String filterName, String username){return 0;

    public void deleteFilter(int filterId){}
}
