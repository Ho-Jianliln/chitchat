package util;


import javax.websocket.Session;
import java.io.IOException;
import java.util.*;

public class ConnPool {
    private static Map<Session, String> connUserMap = new HashMap<Session, String>();
    /**
     * 通过连接获取其对应的用户
     * @param conn
     * @return
     */
    public static String getUserByConn(Session conn) {
        return connUserMap.get(conn);
    }

    /**
     * 根据userId获取连接,这是一个list,此处取第一个
     * 因为有可能多个连接对应一个userName（但一般是只有一个，因为在close方法中，我们将失效的websocket连接去除了）
     * @param userId
     */
    public static Session getConnByUser(String userId) {
        Set<Session> keySet = connUserMap.keySet();
        synchronized (keySet) {
            for (Session conn : keySet) {
                String cuser = connUserMap.get(conn);
                if (cuser.equals(userId)) {
                    return conn;
                }
            }
        }
        return null;
    }

    /**
     * 向连接池中添加连接
     * @param userId
     * @param conn
     */
    public static void addUser(String userId, Session conn) {
        connUserMap.put(conn, userId); // 添加连接
    }

    /**
     * 获取所有连接池中的用户，因为set是不允许重复的，所以可以得到无重复的user数组
     * @return
     */
    public static Collection<String> getOnlineUser() {
        List<String> setUsers = new ArrayList<String>();
        Collection<String> setUser = connUserMap.values();
        for (String u : setUser) {
            setUsers.add(u);
        }
        return setUsers;
    }

    /**
     * 移除连接池中的连接
     */
    public static boolean removeUser(Session conn) {
        if (connUserMap.containsKey(conn)) {
            connUserMap.remove(conn); // 移除连接
            return true;
        } else {
            return false;
        }
    }

    /**
     * 向特定的用户发送数据
     *
     * @param conn
     * @param message
     */
    public static void sendMessageToUser(Session conn, String message) throws IOException {
        if (null != conn && null != connUserMap.get(conn)) {
            conn.getBasicRemote().sendText(message);
        }
    }

    /**
     * 向所有的用户发送消息
     * @param message
     */
    public static void sendMessageToAll(String message) throws IOException {
        Set<Session> keySet = connUserMap.keySet();
        synchronized (keySet) {
            for (Session conn : keySet) {
                String user = connUserMap.get(conn);
                if (user != null) {
                    conn.getBasicRemote().sendText(message);
                }
            }
        }
    }

}
