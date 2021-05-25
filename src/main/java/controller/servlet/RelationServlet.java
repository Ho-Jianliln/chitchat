package controller.servlet;

import Bean.FriendInf;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import entity.Account;
import entity.Friend;
import entity.Request;
import org.apache.commons.beanutils.BeanUtils;
import service.RelationService;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@WebServlet(name = "RelationServlet", value = "/RelationServlet")
public class RelationServlet extends BaseServlet {
    private ObjectMapper mapper = new ObjectMapper();
    public void findFriend(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String goal=request.getParameter("goal");
        PrintWriter out = response.getWriter();
        List<Account> list= RelationService.findFriend(goal);
        if(list.isEmpty()){
            out.print(0);
        }else {
            mapper.writeValue(out,list);
        }
        out.close();
    }
    public void addFriendReq(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String[]> map = request.getParameterMap();
        Request request1=new Request();
        int i=0;
        try {
            BeanUtils.populate(request1,map);
            i= RelationService.addFriendReq(request1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        out.print(i);
        out.close();
    }
    public void checkRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        List<Request> list=RelationService.checkRequest(request.getParameter("myId"));
        if(list.isEmpty()){
            out.print(0);
        }else {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(out, list);
            System.out.println(mapper.writeValueAsString(list));
        }
        out.close();
    }
    public void addFriend(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String id=request.getParameter("request_id");
        String nickname=request.getParameter("nickname");
        int result=RelationService.addFriend(id,nickname);
        out.print(result);
        out.close();
    }
    public void rejectFriend(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String id=request.getParameter("request_id");
        int result=RelationService.rejectFriend(id);
        out.print(result);
        out.close();
    }
    public void confirm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String id=request.getParameter("request_id");
        int result=RelationService.confirm(id);
        out.print(result);
        out.close();
    }
    public void friendList(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        PrintWriter out = response.getWriter();
        String my_id=request.getParameter("my_id");
        List<FriendInf> list=RelationService.getFriendList(my_id);
        if(list.isEmpty()){
            out.print(0);
        }else{
            mapper.writeValue(out,list);
            System.out.println(mapper.writeValueAsString(list));
        }
        out.close();
    }
    public void setNickname(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String[]> map = request.getParameterMap();
        Friend friend=new Friend();
        int i=0;
        try {
            BeanUtils.populate(friend,map);
            i= RelationService.setNickname(friend);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        out.print(i);
        out.close();
    }
    public void delFriend(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String myId=request.getParameter("my_id");
        String friendId=request.getParameter("friend_id");
        int result=RelationService.delFriend(myId,friendId);
    }
}
