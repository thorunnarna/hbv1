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
        user.setUserId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setPhoto(rs.getString("photo"));
        return user;
    }
}
