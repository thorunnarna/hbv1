package project.persistence.repositories.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import project.persistence.entities.ScheduleItem;

import org.springframework.jdbc.core.RowMapper;
/**
 * Created by Svava on 03.11.16.
 */
public class ItemMapper implements RowMapper {

    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        ScheduleItem item = new ScheduleItem();
        item.setId((int)rs.getLong("id"));
        item.setUserId(rs.getInt("userid"));
        item.setColor(rs.getString("color"));
        item.setDescription(rs.getString("description"));
        item.setEndTime(rs.getTimestamp("endTime").toLocalDateTime());
        item.setStartTime(rs.getTimestamp("startTime").toLocalDateTime());
        item.setLocation(rs.getString("location"));
        item.setTitle(rs.getString("title"));
        item.setWeekNo(rs.getInt("weekNo"));
        item.setYear(rs.getInt("year"));
        item.calculateTime();
        item.findWeekDay();
        item.setStartstring(item.getStartTime().toString().substring(11));
        item.setEndstring(item.getEndTime().toString().substring(11));
        return item;
    }
}
