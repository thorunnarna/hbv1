package project.persistence.repositories.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import project.persistence.entities.User;

import org.springframework.jdbc.core.RowMapper;

/**
 * Created by Svava on 03.11.16.
 */
public class UserMapper implements RowMapper {

    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId((int)rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPhoto(rs.getString("photo"));
        user.setSchool(rs.getString("school"));
        return user;
    }
}
