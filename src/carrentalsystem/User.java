package carrentalsystem;

public class User {
    protected String username;
    protected String password; 
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }
}
