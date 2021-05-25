package entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

@JsonIgnoreProperties({"password"})
public class Account {
    private String id;
    private String name;
    private String password;
    private String headshot;
    private int phoneNum;
    private String email;
    private int type;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date LastTime;
    private int status;

    public Account() {
    }
    public Account(String account, String password, int type) {
        this.id = account;
        this.password = password;
        this.type=type;
    }

    public Account(String account, String password, String name, String email, int type) {
        this.id = account;
        this.name = name;
        this.password = password;
        this.email = email;
        this.type=type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadshot() {
        return headshot;
    }

    public void setHeadshot(String headshot) {
        this.headshot = headshot;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getLastTime() {
        return LastTime;
    }

    public void setLastTime(Date lastTime) {
        LastTime = lastTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
