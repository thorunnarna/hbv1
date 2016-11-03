package project.persistence.repositories;

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
        return null;
    }

    public List<User> findUsersByName(String username){return null;}

    public List<ScheduleItem> findItemsByNameWeek(String name, int weekNo){return null;}

    public int createUser(String username, String password, String photo, String school){return 0;}

    public int createItem(String title, String username, LocalDate startTime, LocalDate endTime,
                   int weekNo, int year, String location, String color, String description){return 0;}

    public void deleteItem(int itemId){};

    public void editItem(String title, String username, LocalDate startTime, LocalDate endTime, int weekNo, int year,
                  String location, String color, String description){};

    public int createGroup(String grpName, List<User> members){return 0;};

    public void deleteGroup(int grpId){};

    public void editGroup(int grpId, String grpName, List<User> members){};

    public int createFriendship(int userId1, int userId2){return 0;};

    public void deleteFriendship(int friendshipId){};

    public int createFilter(String filterName, String username){return 0;};

    public void deleteFilter(int filterId){};
}
