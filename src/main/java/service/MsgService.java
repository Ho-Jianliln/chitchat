package service;

import dao.MessageDao;
import entity.Message;

import java.sql.SQLException;

public class MsgService {
    public static int sendMsg(Message message) throws SQLException, IllegalAccessException {
        MessageDao md=new MessageDao();
        return md.sendMsg(message);
    }
}
