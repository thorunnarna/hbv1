package project.persistence.repositories.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import project.persistence.entities.Group;

/**
 * Created by Svava on 03.11.16.
 */
public class GroupMapper implements RowMapper {

    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Group group = new Group();
        group.setGrpId(rs.getInt("id"));
        group.setGrpName(rs.getString("name"));
        return group;
    }
}