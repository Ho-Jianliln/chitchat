package Bean;

public class User {
    private String account;
    private String password;
    private String name;
    private String email;
    private String vcodecheck;

    public User(String account, String password, String name, String email) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User(String account, String password, String vcodecheck) {
        this.account = account;
        this.password = password;
        this.vcodecheck = vcodecheck;
    }

    public String getVcodecheck() {
        return vcodecheck;
    }

    public void setVcodecheck(String vcodecheck) {
        this.vcodecheck = vcodecheck;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
