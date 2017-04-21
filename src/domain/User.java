package domain;

/**
 * Created by Louise Windows on 18-04-2017.
 */
public class User {
    int id;
    String username = "";
    String password = "";
    String role = "";


    public User(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;

    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getRole() {
        return role;
    }

}
