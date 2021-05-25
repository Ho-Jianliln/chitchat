package service;

import Bean.FriendInf;
import dao.AccountDao;
import dao.FriendDao;
import dao.RequestDao;
import entity.Account;
import entity.Friend;
import entity.Request;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RelationService {
    /**
     * @param goal 查找的账户id或名字
     * @return 找到的用户列表
     */
    public static List<Account> findFriend(String goal) throws SQLException {
        AccountDao ad = new AccountDao();
        List<Account> list = ad.selectAll("where id=? or name=?", new Object[]{goal, goal});
        ad.close();
        return list;
    }

    /**
     * 向库中添加申请好友的请求
     *
     * @param rq 封装好的请求类
     */
    public static int addFriendReq(Request rq) throws SQLException, IllegalAccessException {
        RequestDao rd = new RequestDao();
        return rd.add(rq);
    }

    /**
     * 查找与该用户有关的未确认请求
     * @param myId
     * @return 请求列表
     */
    public static List<Request> checkRequest(String myId) {
        RequestDao rd = new RequestDao();
        List<Request> list = new ArrayList<>();
        try {
            list = rd.queryByMyId(myId);
            rd.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }

    public static int addFriend(String id, String nickname) {
        int result = 0;
        try {
            RequestDao rd = new RequestDao();
            Request req = rd.queryById(id);
            req.setStatus(1);
            System.out.println(req);
            FriendDao fd = new FriendDao();
            Friend friend1 = new Friend(req.getTarget(), req.getApplicant(), nickname, 0);
            Friend friend2 = new Friend(req.getApplicant(), req.getTarget(), "", 0);
            if(fd.findNum(friend1)!=2){//确定好友关系未存在
                rd.updateStatue(req);//更新好友请求状态为已接受
                result = fd.add(friend1, friend2);//添加好友关系
            }
            rd.close();
            fd.close();
        } catch (SQLException | IllegalAccessException throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    public static int rejectFriend(String id) {
        int result = 0;
        try {
            RequestDao rd = new RequestDao();
            Request req = rd.queryById(id);
            req.setStatus(2);
            result = rd.updateStatue(req);//更新好友请求状态为拒绝
            rd.close();
        } catch (SQLException | IllegalAccessException throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    public static int confirm(String id){
        int result = 0;
        try {
            RequestDao rd = new RequestDao();
            Request req = rd.queryById(id);
            req.setStatus(3);
            result = rd.updateStatue(req);//更新好友请求状态为已确认
            rd.close();
        } catch(SQLException | IllegalAccessException throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
    public static List<FriendInf> getFriendList(String my_id) {
        List<FriendInf> list=new ArrayList<>();
        FriendDao fd=new FriendDao();
        try {
            list=fd.getFriendList(my_id);
            fd.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return list;
    }
    public static int setNickname(Friend friend){
        FriendDao fd=new FriendDao();
        int i=0;
        try {
            i= fd.setNickname(friend);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return i;
    }
    public static int delFriend(String myId,String friendId){
        FriendDao fd=new FriendDao();
        int i=0;
        try {
            i=fd.delFriend(myId,friendId);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return i;
    }
}
