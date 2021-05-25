package dao;

import util.JDBCutil;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.StringJoiner;

public class BaseDao {
    protected static Connection cn = null;
    protected static PreparedStatement pstmt = null;
    protected static ResultSet rs = null;

    public static void getConnection() {

        try {
            cn = JDBCutil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void close() throws SQLException {
        if(rs!=null){
            rs.close();
        }
        if(pstmt!=null){
            pstmt.close();
        }
        if(cn!=null){
            cn.close();
        }
        System.out.println("dao close");
    }
    public int add(String table,Object oj) throws IllegalAccessException, SQLException {
        getConnection();
        Class clz=oj.getClass();
        String sql="insert into "+table+"(";
        Field[] fields = clz.getDeclaredFields();
        int paramNum=0;
        StringJoiner sj=new StringJoiner(",",sql,") values (");
        for (Field field : fields) {
            field.setAccessible(true);
            sj.add(field.getName());
            paramNum++;
        }
        sql=sj.toString();
        sj=new StringJoiner(",",sql,")");
        for (int i=0;i<paramNum;i++) {
            sj.add("?");
        }
        sql=sj.toString();
        pstmt = cn.prepareStatement(sql);
        int i=0;
        for(Field field : fields) {
            field.setAccessible(true);
            pstmt.setObject(i+1,field.get(oj));
            i++;
        }
        System.out.println(sql);
        int result= pstmt.executeUpdate();
        close();
        return result;
    }
    public int dele(String table,String condition,Object[] param) throws SQLException {
        getConnection();
        String sql="delete from "+table+" "+condition;
        if (param != null) {
            for (int i = 0; i < param.length; i++) {
                pstmt.setObject(i + 1, param[i]);
            }
        }
        pstmt =cn.prepareStatement(sql);
        int result= pstmt.executeUpdate();
        close();
        return result;
    }
    public int upDate(String table,Object oj,String condition,Object[] param) throws SQLException, IllegalAccessException {
        getConnection();
        Class clz=oj.getClass();
        Field[] fields = clz.getDeclaredFields();
        String sql="update "+table+" set ";
        StringJoiner sj=new StringJoiner("=?,",sql,"=? ");
        for (Field field : fields) {
            field.setAccessible(true);
            if(field.get(oj)!=null) {
                sj.add(field.getName());
            }
        }
        sql=sj.toString();
        sql+=condition;
        pstmt =cn.prepareStatement(sql);
        System.out.println(sql);
        int i=1;
        for (Field field:fields) {
            if(field.get(oj)!=null) pstmt.setObject(i++,field.get(oj));
        }
        for (Object s: param){
            pstmt.setObject(i++,s);
        }
        int result= pstmt.executeUpdate();
        close();
        return result;
    }
    public ResultSet query(String goal,String table,String conditions,Object[] param) throws SQLException {
        getConnection();
        String sql="select "+goal;
        sql+=" from "+table+" "+conditions;
        pstmt = cn.prepareStatement(sql);
        if (param != null) {
            for (int i = 0; i < param.length; i++) {
                pstmt.setObject(i + 1, param[i]);
            }
        }
        System.out.println(sql);
        rs = pstmt.executeQuery();
        return rs;
    }
}
