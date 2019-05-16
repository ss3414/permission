package com.interceptor;

import com.mapper.PermissionMapper;
import com.model.Permission;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    PermissionMapper permissionMapper;

    /*
     * （下一个拦截器/Controller）处理请求前调用，第三个参数o为（下一个拦截器/Controller）
     * 返回true继续流程（下一个拦截器/Controller）
     * 返回false中断流程，不会调用下一个拦截器/Controller（此时需要通过response告知用户）
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        /*
         * 判断要访问的URL属于前台/后台
         * ①根据Controller的Mapping
         * */
        Map<String, Integer> map = new HashMap();
        map.put("login", 0);
        map.put("front", 1);
        map.put("back", 2);

        String[] URIArray = request.getRequestURI().split("/");
        String controller = URIArray[1];
        String method = URIArray[2];

        int flag = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getKey().equals(controller)) {
                flag = entry.getValue();
                break;
            }
        }

        /*
         * 根据flag判断是否需要登录/权限
         * ①0/default：公共，直接跳转
         * ②1：前台
         * ③2：后台，需要登录/权限
         * */
        switch (flag) {
            case 0:
                return true;
            case 1:
                return true;
            case 2:
                User user = (User) request.getSession().getAttribute("user"); // 暂不考虑多线程问题，用户每次访问后台都会查询其权限
                if (user != null) {
                    /* 细粒度权限控制，精确到具体方法 */
                    List<Permission> permissionList = permissionMapper.selectPermissionList(user);
                    for (Permission permission : permissionList) {
                        if (permission.getPermissionName().equals(request.getRequestURI())) {
                            return true;
                        }
                    }
                    return returnFalse(response); // 没有权限
                }
                return returnFalse(response); // 未登录
            default:
                return true;
        }

    }

    /*
     * （上一个拦截器/Controller）处理请求后调用
     * 第三个参数o为（下一个拦截器/Controller），modelAndView为上一个处理后的结果
     * */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    /*
     * 在DispatcherServlet完全处理完，即视图渲染完后调用
     * */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }

    /************************************************************分割线************************************************************/

    /*
     * 未登录/没有权限
     * ①中断流程，直接跳转至未登录页面
     * */
    private boolean returnFalse(HttpServletResponse response) throws IOException {
        response.sendRedirect("/login/noPermission");
        return true;
    }

}
