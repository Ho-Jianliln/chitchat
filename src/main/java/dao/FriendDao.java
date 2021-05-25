package dao;

import Bean.FriendInf;
import entity.Friend;
import service.ResultTran;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FriendDao extends BaseDao{
    public int add(Friend friend1,Friend friend2) throws SQLException, IllegalAccessException {
        int i=0;
        i+=super.add("friend",friend1);
        i+=super.add("friend",friend2);
        return i;
    }
    public int findNum(Friend friend) throws SQLException {
        ResultSet rs =super.query("count(*) num","friend","(my_id=? and friend_id=?) or (my_id=? and friend_id=?)",
                new Object[]{friend.getFriend_id(),friend.getMy_id(),friend.getMy_id(),friend.getFriend_id()});
        while (rs.next()){
            return rs.getInt("num");
        }
        return 0;
    }
    public List<FriendInf> getFriendList(String my_id) throws SQLException {
        ResultSet rs=super.query("*","friend","inner join account on friend_id=id and my_id=?",new Object[]{my_id});
        return ResultTran.tran(rs,FriendInf.class);
    }
    public int setNickname(Friend friend) throws SQLException, IllegalAccessException {
        return super.upDate("friend",friend,"where my_id=? and friend_id=?",
                new Object[]{friend.getMy_id(),friend.getFriend_id()});
    }
    public int delFriend(String myId,String friendId) throws SQLException {
        return super.dele("friend","where my_id=? and friend_id=?",new Object[]{myId,friendId});
    }
}
