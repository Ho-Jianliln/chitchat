package entity;

public class Friend {
    private String my_id;
    private String friend_id;
    private String nickname;
    private int black;

    public Friend(){}
    public Friend(String my_id, String friend_id, String nickname, int black) {
        this.my_id = my_id;
        this.friend_id = friend_id;
        this.nickname = nickname;
        this.black = black;
    }

    public String getMy_id() {
        return my_id;
    }

    public void setMy_id(String my_id) {
        this.my_id = my_id;
    }

    public String getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(String friend_id) {
        this.friend_id = friend_id;
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
}
