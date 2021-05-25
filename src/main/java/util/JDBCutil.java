package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCutil {
    private static DataSource ds;
    static{
        Properties properties=new Properties();
        try {
            properties.load(new FileReader("C:\\Users\\89208\\IdeaProjects\\chitchat\\JDBC.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
