package net.rhuanrocha.identitystore;

import java.util.Arrays;
import java.util.List;

public class User {

    private String username;
    private String password;
    private List<String> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public static User of (String username, String password, String... roles){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles(Arrays.asList(roles));
        return user;
    }
}
