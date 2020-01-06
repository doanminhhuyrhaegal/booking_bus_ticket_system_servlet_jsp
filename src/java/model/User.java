package model;

/**
 *
 * @author Lenvo
 */
public class User {
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private boolean role;

    public User() {
        username = null;
        password = null;
        name = null;
        phone = null;
        email = null;
        role = false;
    }
    public User(String username, String password, String name, String phone, String email, boolean role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.role = role;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean getRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }
    
}
