package kth.iv1201.recruitment.entity;

public class UserForm {
    private final String username;
    private final String password;

    public UserForm(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
