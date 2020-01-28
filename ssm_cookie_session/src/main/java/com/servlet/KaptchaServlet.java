package com.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

public class KaptchaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("kaptcha1", "kaptcha1"); /* 每次调用验证码接口将验证码写入Session中 */

        OutputStream outputStream = response.getOutputStream();
        BufferedImage bufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        ImageIO.write(bufferedImage, "JPG", outputStream);
        outputStream.close();

        /* Servlet返回验证码无法修改response header */
        response.setHeader("Set-Cookie", "");
    }

}
