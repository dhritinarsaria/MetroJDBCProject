/*package metro.entity;

public class User {
    private int userId;
    private String name;

    public User() {}

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + "]";
    }
}
*/

package metro.entity;

public class User {
    private int userId;       // primary key
    private String name;      // user name
    private String email;     // user email

    // Default constructor
    public User() {}

    // All-args constructor
    public User(int userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    // Constructor without ID (for new users before persisting)
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters & Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // To string for debugging
    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + ", email=" + email + "]";
    }
}

