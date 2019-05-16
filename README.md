# permission
登录/权限/单点登录<br>

************************************************************************************************************************

# shiro_ini
①Shiro（JDK6）<br>
②Shiro INI示例<br>

# shiro_rbac
①Shiro（JDK6）<br>
②Shiro RBAC示例<br>
逻辑同shiro_ini，Realm换为RBACRealm（需要在ini中配置）<br>

# shiro_web
①Java EE（JDK6）+Shiro<br>
②将Shiro整合进原生Java Web项目中<br>
数据库结构/Realm同shiro_rbac<br>

③技术细节<br>
Main/Test项目是一次性的，Web项目是持续的<br>
通过web.xml中的配置，Shiro被整合进Java Web项目中<br>
Shiro单独配置shiro.ini决定每个路由所需要的权限<br>

# shiro_ssm
①SSM（JDK6）+Shiro<br>
②Shiro整合SSM<br>
数据库结构同shiro_rbac，Realm调用MyBatis<br>

③技术细节<br>
Java Web先整合SSM，再整合Shiro<br>
权限可以由INI/XML/注解/Java代码决定<br>
MD5加密<br>
④静态权限<br>
权限固定写在配置/代码中<br>
除特定路由外，全局要求登录<br>
不同用户不同权限，内容不同<br>

# shiro_springboot
①SB2（JDK8）+MBP+Shiro<br>
②Shiro整合SpringBoot（shiro_ssm的SpringBoot版）<br>
MyBatis配置（DataSourceConfig）<br>
Shiro配置（ShiroConfig）<br>

③动态权限<br>
动态过滤链实现动态权限<br>
每次新增权限自动附加到管理员角色上<br>

******************************************************************************************

# ssm_session_cookie
①SSM（JDK6）+RBAC<br>
②Session+拦截器控制权限<br>

************************************************************************************************************************

# 参考
①http://how2j.cn/k/shiro/shiro-plan/1732.html（Shiro）<br>
②https://www.iteye.com/topic/930648（数据库RBAC模型）<br>
③https://blog.csdn.net/frankcheng5143/article/details/50836619（SpringMVC整合Shiro）<br>