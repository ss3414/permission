# permission
权限<br>

************************************************************************************************************************

# ssm_cookie_session
①SSM+RBAC<br>
②归档<br>
服务端禁用Cookie<br>
Cookie+拦截器控制权限<br>

# shiro_springboot
①SpringBoot+MBP+Shiro<br>
②Shiro整合SpringBoot（shiro_ssm的SpringBoot版）<br>
MyBatis配置（DataSourceConfig）<br>
Shiro配置（ShiroConfig）<br>
③动态权限<br>
动态过滤链实现动态权限<br>
每次新增权限自动附加到管理员角色上并即时生效<br>
④拦截器获取参数直接登录<br>

# satoken_springboot
①SpringBoot+Sa-Token+Redisson+JWT<br>
②RedissonConfig（手动配置）<br>

******************************************************************************************

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
①SpringBoot+MBP+Shiro+JWT<br>
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
①SpringBoot+MBP+Shiro+JWT<br>
②Shiro整合JWT（动态权限）<br>
③引入Redis和Spring Cache<br>

************************************************************************************************************************

# 单点登录
①同构单点登录<br>
shiro_redis中的两组项目shiro_1/shiro_2和shiro_3/shiro_4的登录模块是统一的，即将Session/Token存储到同一个Redis中
②异构单点登录<br>
两个项目的登录机制不同，项目之间的单点登录实际上是跨项目访问时构造登录状态<br>
③引入第三方框架<br>
跨项目访问时构造登录状态，只不过引入第三方框架如CAS/OAuth<br>

# shiro_redis
①分布式部署<br>
②shiro_1/shiro_2（Shiro+Cookie）<br>
将Session交由Redis管理（ShiroConfig+RedisShiroSessionDao）<br>
User类需要实现序列化<br>
shiro_1/shiro_2域名相同即可共享登录<br>
docker-compose多项目部署<br>
③shiro_3/shiro_4（Shiro+JWT）<br>
登录时储存Token，访问后台时校验Token<br>

# cookie_jwt
①SpringBoot+JPA+Shiro+Cookie/JWT<br>
②异构单点登录<br>
shiro_5（Shiro+Cookie）<br>
shiro_6（Shiro+JWT）<br>
③单点登录方式<br>
回调（项目1带着用户信息访问项目2，项目2调用项目1接口查询用户信息）<br>
构造（项目1带着加密用户信息访问项目2，项目2校验加密用户信息并构造项目2登录用户信息）<br>
④单机缓存<br>
shiro_5不启用Redis时Session放在本机内存中（由Shiro管理）<br>
shiro_6不启用Redis时Token放在本机Spring Cache中（@EnableCaching+spring.cache.type）<br>
⑤Nginx负载均衡<br>

******************************************************************************************

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

# cas_springboot
①SpringBoot+CAS<br>
②CAS客户端<br>
③CAS配置<br>
第三方Springboot CAS客户端（也可以将cas_ssm中的web.xml移植到cas_springboot中）<br>
application.properties（对应web.xml配置）<br>
Application @EnableCasClient（Springboot自动配置）<br>
④跨域单点登录<br>
CAS单点登录的核心是：当cas_ssm登录过后，浏览器会保存CAS服务器的Cookie CASTGC，<br>
当cas_springboot访问CAS服务器时会带上这个Cookie（这个Cookie cas_ssm/cas_springboot获取不到）<br>
（除非CAS服务器将Cookie CASTGC跨域写入，否则无法跨域单点登录）<br>
