package controller.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MessageServlet",value = "/MessageServlet")
public class MessageServlet extends BaseServlet{
    public void sendMsg(HttpServletRequest request, HttpServletResponse response){

    }
}
