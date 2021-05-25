package controller.servlet;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "VcodeServlet", value = "/VcodeServlet")
public class VcodeServlet extends HttpServlet {
    //设置验证码图片的宽度
    private static final int Width = 80;
    //设置验证码的高度
    private static final int Height = 30;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedImage image=new BufferedImage(Width,Height,BufferedImage.TYPE_INT_RGB);
        response.setHeader("Pragram","no-cache");//服务器通知浏览器不要缓存
        response.setHeader("Cache-Control","no-catch");
        response.setDateHeader("Exprires",0);
        Graphics g=image.getGraphics();
        //设置背景颜色
        g.setColor(Color.WHITE);
        //填充图片
        g.fillRect(0,0,Width,Height);
        //设置验证码颜色
        g.setColor(Color.RED);
        //设置验证码字体及大小
        Font font=new Font("黑体",Font.BOLD,50);
        //获取验证码
        String str= RandomStringUtils.randomAlphanumeric(4);
        //获取session
        HttpSession session=request.getSession();
        //给str做标记
        session.setAttribute("Vcode",str);
        //在图片中画出验证码
        g.drawString(str,15,25);
        //在图片中随机划线
        for (int i=0;i<7;i++){
            int x1= RandomUtils.nextInt(0,Width);
            int x2=RandomUtils.nextInt(0,Width);
            int y1=RandomUtils.nextInt(0,Height);
            int y2=RandomUtils.nextInt(0,Height);
            Color color=new Color(RandomUtils.nextInt(0,255),RandomUtils.nextInt(0,255),RandomUtils.nextInt(0,255));
            g.setColor(color);
            g.drawLine(x1,y1,x2,y2);
        }
        //输出图片
        ImageIO.write(image,"jpg",response.getOutputStream());
    }

}
