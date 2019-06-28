# permission
登录/权限/单点登录<br>

************************************************************************************************************************

# ssm_cookie_session
①SSM（JDK6）+RBAC<br>
②Session+拦截器控制权限<br>

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
①SpringBoot（JDK8）+MBP+Shiro<br>
②Shiro整合SpringBoot（shiro_ssm的SpringBoot版）<br>
MyBatis配置（DataSourceConfig）<br>
Shiro配置（ShiroConfig）<br>
③动态权限<br>
动态过滤链实现动态权限<br>
每次新增权限自动附加到管理员角色上<br>

************************************************************************************************************************

# jwt_shiro
①SpringBoot（JDK8）+MBP+Shiro+JWT<br>
②Shiro整合JWT<br>

************************************************************************************************************************

# CAS准备工作
①CAS服务端<br>
将修改过的CAS服务端cas.war（cas-server-4.0.0/不需要HTTPS）中的内容解压放到apache-tomcat-7.0.90(2)\cas\ROOT目录下<br>
配置Tomcat本地单端口多域名（apache-tomcat-7.0.90(2)\conf\server.xml）<br>
修改hosts映射域名（C:\Windows\System32\drivers\etc\hosts）<br>
CAS单点认证地址：http://cas.test.com/login（casuser+Mellon）<br>
②hosts映射域名<br>
CAS服务端：127.0.0.1 cas.test.com<br>
③开发环境<br>
开发环境下cas_ssm/cas_springboot分别使用127.0.0.1:8080/127.0.0.1:8000启动（需要修改对应CAS配置）<br>

# cas_ssm
①SSM（JDK8）+CAS<br>
②CAS客户端<br>
③web.xml配置<br>
允许login1.jsp访问单点地址（CAS Validation Filter）<br>
如果未登录访问login1.jsp会自动单点认证（CAS Authentication Filter）<br>
不允许login2访问单点地址，但允许从request中获取用户信息（CAS HttpServletRequest Wrapper Filter）<br>
允许从任意页面注销（CAS Single Sign Out Filter）<br>
④Java EE/SpringMVC<br>
JSP直接放到webapp目录下，跳转时以.jsp结尾为传统Java EE写法<br>

# cas_springboot
①SpringBoot（JDK8）+CAS<br>
②CAS客户端<br>
③CAS配置<br>
第三方Springboot CAS客户端（也可以将cas_ssm中的web.xml移植到cas_springboot中）<br>
application.properties（对应web.xml配置）<br>
Application @EnableCasClient（Springboot自动配置）<br>
④跨域单点登录<br>
CAS单点登录的核心是：当cas_ssm登录过后，浏览器会保存CAS服务器的Cookie CASTGC，<br>
当cas_springboot访问CAS服务器时会带上这个Cookie（这个Cookie cas_ssm/cas_springboot获取不到）<br>
（除非CAS服务器将Cookie CASTGC跨域写入，否则无法跨域单点登录）<br>