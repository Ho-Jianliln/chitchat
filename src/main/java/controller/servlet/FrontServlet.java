package controller.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Account;
import org.apache.commons.lang3.RandomStringUtils;
import service.EmailService;
import service.FrontService;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;

@WebServlet(name = "FrontServlet", value = "/FrontServlet")
public class FrontServlet extends BaseServlet {
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, InvocationTargetException,
            SQLException, InstantiationException, NoSuchMethodException, IllegalAccessException, ServletException {
        HttpSession session = request.getSession();
        String account=request.getParameter("account");
        String password=request.getParameter("password");
        String vcodecheck=request.getParameter("vcodecheck");
        PrintWriter out = response.getWriter();
        int isAdmin="false".equals(request.getParameter("isAdmin"))? 0:1;
        Account user = new Account(account,password,isAdmin);
        String realVcode = (String) session.getAttribute("Vcode");
        if(vcodecheck.equals(realVcode)||vcodecheck.equals(realVcode.toLowerCase())) {//检查验证码，可小写
            if (account == null || "".equals(account) || password == null || "".equals(password)) {//检查帐密是否为空
                out.write("账号或密码不能为空");
            }else {
                Account loginUser = FrontService.login(user);
                if(loginUser!=null){
                    session.setAttribute("account",loginUser);
                    if("true".equals(request.getParameter("autoLogin"))) {//如勾选自动登录，设置cookie周期一周
                        Cookie cookie = new Cookie("JSESSIONID", session.getId());
                        cookie.setMaxAge(60*60*24*7);
                        response.addCookie(cookie);
                    }
                    if("true".equals(request.getParameter("isAdmin"))){
                        out.write("admin");
                    }else {
                        out.write("user");
                    }
                }else {
                    out.write("账号密码错误");
                }
            }
        }else{
            out.write("验证码错误");
        }
        out.close();
    }
    public void getAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Account user= (Account) session.getAttribute("account");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(out,user);
        out.close();
    }
    public void sendRegisterEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String str= RandomStringUtils.randomAlphanumeric(4);
        session.setAttribute("emailCode",str);
        try {
            EmailService.send("register",request.getParameter("email"),str);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public void sendresetPassEmail(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        HttpSession session = request.getSession();
        int result=0;
        String str= RandomStringUtils.randomAlphanumeric(4);
        session.setAttribute("emailCode",str);
        String email=FrontService.getEmail(request.getParameter("account"));
        if(email!=null&&!"".equals(email)) {
            try {
                EmailService.send("resetPass", email, str);
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        Account user=new Account(request.getParameter("account"),request.getParameter("password"),
                request.getParameter("name"),request.getParameter("email"),0);//默认此处注册的是普通用户
        String vcodecheck=request.getParameter("vcodecheck");
        String emailCode=request.getParameter("emailCode");
        String realVcode = (String) session.getAttribute("Vcode");
        String realEmailCode = (String) session.getAttribute("emailCode");
        if(!vcodecheck.equals(realVcode)&&!vcodecheck.equals(realVcode.toLowerCase())){//检验验证码
            out.write("验证码错误");
        }else if(!emailCode.equals(realEmailCode)){//检验邮箱验证码
            out.write("邮箱验证码错误");
        }else {
            int result=FrontService.register(user);
            if(result==1){
                out.write("账号已经存在！注册失败");
            }else {
                out.write("注册成功！");
            }
        }
        out.close();
    }
    public void resetPass(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, IllegalAccessException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        Account user=new Account(request.getParameter("account"),request.getParameter("password"),0);
        String vcodecheck=request.getParameter("vcodecheck");
        String emailCode=request.getParameter("emailCode");
        String realVcode = (String) session.getAttribute("Vcode");
        String realEmailCode = (String) session.getAttribute("emailCode");
        if(!vcodecheck.equals(realVcode)&&!vcodecheck.equals(realVcode.toLowerCase())){//检验验证码
            out.write("验证码错误");
        }else if(!emailCode.equals(realEmailCode)){//检验邮箱验证码
            out.write("邮箱验证码错误");
        }else {
            int result=FrontService.resetPass(user);
            if(result==1){
                out.write("密码修改成功");
            }else {
                out.write("密码修改失败");
            }
        }
        out.close();
    }
}
