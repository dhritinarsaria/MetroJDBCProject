



package metro.persistence;

import metro.entity.User;
import metro.exceptions.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of UserDao interface
 * This class acts as the persistence layer (in-memory for now).
 * You can later replace this with JDBC/Database implementation.
 */
public class UserDaoImpl implements UserDao {

    // In-memory storage for users (instead of DB)
    private final List<User> users = new ArrayList<>();
    private int idCounter = 1; // to auto-generate userId

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users); // return a copy to avoid modification outside
    }

    @Override
    public void saveUser(User user) {
        if (user.getUserId() == 0) {
            // new user, assign auto-generated ID
            user.setUserId(idCounter++);
        }
        users.add(user);
    }

    @Override
    public User getUserById(int userId) throws UserNotFoundException {
        return users.stream()
                .filter(u -> u.getUserId() == userId)
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));
    }

    @Override
    public int countTotalUsers() {
        return users.size();
    }
}

