package app.user;

import java.util.List;

/**
 * Created by Dennis on 29.09.2016.
 */
public interface UserDao {

    List<User> getAllUsers();

    User getUserByUsername(String username);

    Iterable<String> getAllUserNames();

    boolean createUser(User user);

    boolean updateOpengameId(User user, int opengameId);

    int getLatestOpengameId(String username);

    boolean updateUser(User user);

}
