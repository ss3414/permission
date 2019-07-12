package com.common.shiro;

import com.common.util.JDBCUtil;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JWTFilter extends BasicHttpAuthenticationFilter {

    /*
     * ①先查路由表，不需要登录/需要登录/需要权限
     * ②再查用户权限
     *
     * fixme 路由匹配
     *  ①完全匹配+所有路由
     *  ②通配符
     * */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String URI = httpServletRequest.getRequestURI();

        JDBCUtil util = new JDBCUtil();
        List<Map<String, Object>> filterList = util.select("SELECT * FROM shiro_filter order by filter_sort");
        for (Map<String, Object> filter : filterList) {
            if (filter.get("filter_url").equals(URI)) {
                if (filter.get("filter_name").equals("anon")) { /* 不需要登录 */
                    return true;
                } else if (filter.get("filter_name").equals("logout")) { /* fixme JWT注销即用户抛弃token */
                    getSubject(request, response).logout();
                    return true;
                } else if (filter.get("filter_name").equals("authc")) {
                    try {
                        String token = httpServletRequest.getHeader("token");
                        JWTToken jwtToken = new JWTToken(token);
                        getSubject(request, response).login(jwtToken); /* 调用Realm.doGetAuthenticationInfo()登录 */
                    } catch (Exception e) {
                        return msg(response, "token无效");
                    }
                    return true;
                } else if (((String) filter.get("filter_name")).contains("perms")) {
                    Pattern pattern = Pattern.compile("perms\\[(.*)]");
                    Matcher matcher = pattern.matcher((String) filter.get("filter_name"));
                    while (matcher.find()) {
                        try {
                            getSubject(request, response).checkPermission(matcher.group(1)); /* 调用Realm.doGetAuthorizationInfo()授权 */
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (e.getMessage().contains("not have permission")) {
                                return msg(response, "没有权限");
                            } else {
                                return msg(response, "token无效");
                            }
                        }
                    }
                    return true;
                }
            }
        }

        return msg(response, "不在路由表中");
    }

    /* 通过修改response发送消息 */
    private boolean msg(ServletResponse response, String msg) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setCharacterEncoding("UTF-8");
            PrintWriter writer = httpServletResponse.getWriter();
            writer.print("{\"msg\":\"" + msg + "\"}");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
