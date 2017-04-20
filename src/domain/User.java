package domain;

/**
 * Created by Louise Windows on 18-04-2017.
 */
public class User {
    String username = "";
    String id = "";
    String role = "";
    String password = "";

    public User(String username, String password, String id, String role) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.password = password;

    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getPassword(){
        return password;
    }
}
