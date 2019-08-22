# permission
登录/权限<br>

************************************************************************************************************************

# ssm_cookie_session
①SSM（JDK6）+RBAC<br>
②Cookie+拦截器控制权限<br>

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
④固定权限<br>
权限固定写在配置中<br>
除特定路由外，全局要求登录<br>

# shiro_springboot
①SpringBoot（JDK8）+MBP+Shiro<br>
②Shiro整合SpringBoot（shiro_ssm的SpringBoot版）<br>
MyBatis配置（DataSourceConfig）<br>
Shiro配置（ShiroConfig）<br>
③动态权限<br>
动态过滤链实现动态权限<br>
每次新增权限自动附加到管理员角色上并即时生效<br>

************************************************************************************************************************

# 排列组合
请求分为：不带token/带token<br>
路由分为：不需要登录/需要登录/需要权限<br>
①不带token/带token+不需要登录：前台/登录/登录失败/未登录/未授权（不需要登录，带token也不会调用Shiro）<br>
②不带token+登录：用户不存在/密码错误/登录成功（发放token）<br>
③不带token+需要登录：（@RequiresAuthentication）重定向至未登录<br>
④带token+需要登录：（调用Shiro登录）token无效/用户不存在/密码错误/token有效<br>
⑤不带token+需要权限：（@RequiresPermissions）重定向至未授权<br>
⑥带token+需要权限：（调用Shiro登录）token无效/用户不存在/密码错误/token有效（再调用Shiro授权）<br>

# jwt_shiro
①SpringBoot（JDK8）+MBP+Shiro+JWT<br>
②Shiro整合JWT（固定权限）<br>
③Shiro Cookie原理<br>
登录时调用doGetAuthenticationInfo()登录<br>
登录成功后用户信息被保存到全局的Subject中<br>
授权时调用doGetAuthorizationInfo()查询权限（每次授权都要查询）<br>
④Shiro JWT原理<br>
ShiroConfig配置不需要/需要登录路由<br>
JWTFilter拦截需要登录路由并调用JWTRealm登录<br>
Shiro注解调用JWTRealm登录/授权<br>

# jwt_dynamic
①SpringBoot（JDK8）+MBP+Shiro+JWT<br>
②Shiro整合JWT（动态权限）<br>