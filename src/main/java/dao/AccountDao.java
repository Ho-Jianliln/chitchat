package dao;

import entity.Account;
import service.ResultTran;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountDao extends BaseDao{

    public int add(Account oj) throws IllegalAccessException, SQLException {
        return super.add("Account", oj);
    }

    public List<Account> selectAll(String condition, Object[] param) throws SQLException {
        return ResultTran.tran(super.query("*", "Account", condition, param),Account.class);
    }

    public Account loginCheck(Account user) throws SQLException {
        ResultSet rs=super.query("*","Account","where id=? and password=? and type=?",
                new Object[]{user.getId(), user.getPassword(),user.getType()});
        return (Account) ResultTran.tranOne(rs,Account.class);
    }
    public int queryNumByAccount(Account user) throws SQLException {
        ResultSet rs=super.query("count(*) rec","Account","where id=?", new Object[]{user.getId()});
        int rowCount = 0;
        while (rs.next()) {
            rowCount = rs.getInt("rec");
        }
        return rowCount;
    }
    public int updatePass(Account user) throws SQLException, IllegalAccessException {
        int i=super.upDate("Account",user,"where id=?",
                new Object[]{user.getId()});
        return i;
    }
}
