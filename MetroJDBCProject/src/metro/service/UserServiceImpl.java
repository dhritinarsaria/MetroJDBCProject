



package metro.service;

import metro.entity.User;
import metro.exceptions.UserNotFoundException;
import metro.persistence.UserDao;
import metro.persistence.UserDaoImpl;

import java.util.List;

/**
 * Implementation of UserService interface
 * Connects the presentation layer with the persistence layer.
 */
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    // Constructor
    public UserServiceImpl() {
        this.userDao = new UserDaoImpl();  // using in-memory persistence
    }

    @Override
    public User createUser(String name, String email) {
        User newUser = new User(name, email);  // create user without id
        try {
            userDao.saveUser(newUser);  // persist in DB (or in-memory list here)
            return newUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null; // or throw a custom exception if needed
        }
    }

    @Override
    public User getUserById(int userId) {
        try {
            return userDao.getUserById(userId);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return userDao.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int countUsers() {
        try {
            return userDao.countTotalUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}

