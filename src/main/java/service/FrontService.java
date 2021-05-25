package service;

import Bean.User;
import dao.AccountDao;
import entity.Account;
import util.EncryptUtil;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

public class FrontService {
    public static Account login(Account user) throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        AccountDao ad=new AccountDao();
        String password=user.getPassword();
        user.setPassword(EncryptUtil.SHA256(password));
        Account loginUser= ad.loginCheck(user);
        loginUser.setPassword("");
        ad.close();
        return loginUser;
    }
    public static int register(Account user){
        AccountDao ad=new AccountDao();
        user.setPassword(EncryptUtil.SHA256(user.getPassword()));
        int result=1;
        try {
            if(ad.queryNumByAccount(user)==1){
                ad.close();
            }else {
                if(ad.add(user)==1){
                    result=0;
                }
            }
        } catch (SQLException | IllegalAccessException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    public static int resetPass(Account user) throws SQLException, IllegalAccessException {
        AccountDao ad=new AccountDao();
        user.setPassword(EncryptUtil.SHA256(user.getPassword()));
        return ad.updatePass(user);
    }
    public static String getEmail(String account) throws SQLException {
        AccountDao ad=new AccountDao();
        List<Account> list=ad.selectAll("account=?",new Object[]{account});
        if(list.isEmpty()){
            return null;
        }else {
            Account user=list.get(0);
            return user.getEmail();
        }
    }
}
