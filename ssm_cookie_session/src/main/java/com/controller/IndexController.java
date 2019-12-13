package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("")
public class IndexController {

    /* URL回写禁用jsp:forward指令 */
    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping("/home")
    public ModelAndView home(HttpServletResponse response) {
        return new ModelAndView("/home");
    }

    @ResponseBody
    @RequestMapping("/cookie")
    public Map cookie(HttpServletRequest request) {
        /*
         * ①第一次访问，没有Session自动创建
         * ②重复访问，根据Cookie中的SessionId获取Session（浏览器允许Cookie的情况下）
         * ③Session无状态，有超时时间，由系统管理
         * */
        HttpSession session = request.getSession();
        Map result = new HashMap();
        result.put("sessionId", session.getId() != null ? session.getId() : "sessionId");
        return result;
    }

    @RequestMapping("/kaptcha2")
    public void kaptcha2(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            session.setAttribute("kaptcha2", "kaptcha2"); /* 每次调用验证码接口将验证码写入Session中 */

            OutputStream outputStream = response.getOutputStream();
            BufferedImage bufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
            ImageIO.write(bufferedImage, "JPG", outputStream);
            outputStream.close();

            /* Controller返回验证码可以修改response header */
            response.setHeader("Set-Cookie", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
