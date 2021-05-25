package Bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class FriendInf {
    @JsonProperty("id")
    private String friend_id;
    private String name;
    private String nickname;
    private int black;
    private String headshot;
    private int phoneNum;
    private String email;
    private int type;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date lastTime;
    private int status;

    public FriendInf(){}
    public FriendInf(String friend_id, String name, String nickname, int black, String headshot, int phoneNum,
                     String email, int type, Date lastTime, int status) {
        this.friend_id = friend_id;
        this.name = name;
        this.nickname = nickname;
        this.black = black;
        this.headshot = headshot;
        this.phoneNum = phoneNum;
        this.email = email;
        this.type = type;
        this.lastTime = lastTime;
        this.status = status;
    }

    public String getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(String friend_id) {
        this.friend_id = friend_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getBlack() {
        return black;
    }

    public void setBlack(int black) {
        this.black = black;
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
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
