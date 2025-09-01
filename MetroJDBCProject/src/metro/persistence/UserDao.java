package metro.persistence;



import metro.entity.User;
import metro.exceptions.UserNotFoundException;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers() throws Exception;

    void saveUser(User user) throws Exception;

    User getUserById(int userId) throws UserNotFoundException, Exception;

    int countTotalUsers() throws Exception;
}
