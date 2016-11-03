package project.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.persistence.entities.*;

import java.time.*;
import java.util.List;

/**
 * By extending the {@link JpaRepository} we have access to powerful methods.
 * For detailed information, see:
 * http://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
 * http://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
 *
 */
public interface Repository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u")
    List<User> findAllUsers();

    @Query(value = "SELECT u.id FROM User u where u.username = :uname")
    List<User> findUsersByName(@Param("uname") String username);

    @Query(value = "")
    List<ScheduleItem> findItemsByNameWeek(@Param("name") String name, @Param("weekNo") int weekNo);

    @Query(value="")
    int createUser(@Param("username") String username,
                    @Param("pw") String password,
                    @Param("photo") String photo,
                    @Param("school") String school);

    @Query(value="")
    int createItem(@Param("title") String title,
                   @Param("username") String username,
                   @Param("startTime") LocalDate startTime,
                   @Param("endTime") LocalDate endTime,
                   @Param("weekNo") int weekNo,
                   @Param("year") int year,
                   @Param("location") String location,
                   @Param("color") String color,
                   @Param("description") String description);

    @Query(value="")
    void deleteItem(@Param("itemId") int itemId);

    @Query(value="")
    void editItem(@Param("title") String title,
                  @Param("username") String username,
                  @Param("startTime") LocalDate startTime,
                  @Param("endTime") LocalDate endTime,
                  @Param("weekNo") int weekNo,
                  @Param("year") int year,
                  @Param("location") String location,
                  @Param("color") String color,
                  @Param("description") String description);

    @Query(value="")
    int createGroup(@Param("name") String grpName,
                    @Param("members") List<User> members);

    @Query(value="")
    void deleteGroup(@Param("id") int grpId);

    @Query(value="")
    void editGroup(@Param("id") int grpId,
                   @Param("name") String grpName,
                   @Param("members") List<User> members);

    @Query(value="")
    int createFriendship(@Param("user1") int userId1,
                         @Param("user2") int userId2);

    @Query(value="")
    void deleteFriendship(@Param("id") int friendshipId);

    @Query(value="")
    int createFilter(@Param("name") String filterName,
                     @Param("user") String username);

    @Query(value="")
    void deleteFilter(@Param("id") int filterId);

}
