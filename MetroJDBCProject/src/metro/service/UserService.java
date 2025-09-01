package metro.service;

import java.util.List;

import metro.entity.User;

public interface UserService {
    User createUser(String name, String email);  // create new user
    User getUserById(int userId);                // fetch by id
    List<User> getAllUsers();                    // fetch all users
    int countUsers();                            // total count
}
