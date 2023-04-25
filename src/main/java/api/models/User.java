package api.models;

public class User {
    private int id;
    private String login;
    private String password;
    private String email;
    private String regdate;
    private int roleId;  //default -> 1 ("user")
    private int statusId; //default -> 1 ("unconfirmed")
    private int mailConfirm; //auto-default -> 0 ("unconfirmed")

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getMailConfirm() {
        return mailConfirm;
    }

    public void setMailConfirm(int mailConfirm) {
        this.mailConfirm = mailConfirm;
    }

    public User(int id, String login, String password, String email, String regdate, int roleId, int statusId, int mailConfirm) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.regdate = regdate;
        this.roleId = roleId;
        this.statusId = statusId;
        this.mailConfirm = mailConfirm;
    }
}
