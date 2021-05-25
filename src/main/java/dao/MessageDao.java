package dao;

import entity.Message;
import service.ResultTran;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MessageDao extends BaseDao{
    public int sendMsg(Message msg) throws SQLException, IllegalAccessException {
        return super.add("message",msg);
    }
    public List<Message> hasReadMsg(Message msg) throws SQLException {
        ResultSet rs=super.query("*","message",
                "where (fromId=? and toId=?) or (fromId=? and toId=? and status=1) order by date asc",
                new Object[]{msg.getFromId(),msg.getToId(),msg.getToId(),msg.getFromId()});
        return ResultTran.tran(rs,Message.class);
    }
    public List<Message> notReadMsg(Message msg) throws SQLException {
        ResultSet rs=super.query("*","message","where fromId=? and toId=? and status=0",
                new Object[]{msg.getFromId(),msg.getToId()});
        return ResultTran.tran(rs,Message.class);
    }
    public int upMsgStatue(Message msg) throws SQLException, IllegalAccessException {
        msg.setStatus(1);
        return super.upDate("message",msg,"where fromId=? and toId=? status=0",
                new Object[]{msg.getFromId(),msg.getToId()});
    }
}
