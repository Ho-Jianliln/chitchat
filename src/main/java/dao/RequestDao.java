package dao;

import entity.Request;
import service.ResultTran;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RequestDao extends BaseDao{
    public int add(Request request) throws IllegalAccessException, SQLException {
        return super.add("request", request);
    }
    public List<Request> queryByMyId(String myId) throws SQLException {
        ResultSet rs=super.query("*","request","where (applicant=? or target=?) and status!=3",
                new Object[]{myId,myId});
        return ResultTran.tran(rs,Request.class);
    }
    public Request queryById(String id) throws SQLException {
        ResultSet rs=super.query("*","request","where id=?",new Object[]{id});
        return (Request) ResultTran.tranOne(rs,Request.class);
    }
    public int updateStatue(Request req) throws SQLException, IllegalAccessException {
        return super.upDate("request",req,"where id=?",new Object[]{req.getId()});
    }
}
