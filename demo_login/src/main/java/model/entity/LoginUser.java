package model.entity;

public class LoginUser {
    private int id;
    private String loginId;
    private String password;

    public LoginUser() {}

    public LoginUser(int id, String loginId, String password) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getLoginId() { return loginId; }
    public void setLoginId(String loginId) { this.loginId = loginId; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
