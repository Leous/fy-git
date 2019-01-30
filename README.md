# fy-git 博客 技术分享

## 端口（可以自行修改）：8081

## 此文件主要记录整合的插件

1. 整合swagger2插件. 
* 启动项目，访问页面即可看到接口文档 http://localhost:8081/swagger-ui.html. 
具体使用方式查看登录接口上的@Api、@ApiOperation注解即可
```
    @Api(value = BaseApi.API_PARTNER, tags = "Partner Api", description = "用户相关")
    public class PartnerApi extends BasicApi {
    
        @ApiOperation(value="验证用户名", httpMethod="POST")
        public ResponseEntry verifyAccount(HttpServletRequest request){}
    }
```
2. 整合lombok插件.
* 使用lombok提供的注解如：@Data 代替setter、getter、toString等方法
```
    @Data
    public class VerifyAccountRequest extends BaseRequest {
        private String account;
        
        //后续就不需要写setter、getter、toString了
    }
```

3. 整合logback日志插件.(日志配置在根目录log文件夹中)
* 可以使用lombok包下的@Slf4j注解，然后在正文中使用log.info()、log.error()等方法写日志；
```
    @Slf4j
    public class PartnerApi {
    
        public ResponseEntry verifyAccount(HttpServletRequest request){
            log.info("日志内容");
        }
    }
```
* 也可以不借助lombok使用以下方式来声明logger对象记录日志；
```
    public class PartnerApi {
    
        private static final Logger logger = LoggerFactory.getLogger(PartnerController.class);
            
        public ResponseEntry verifyAccount(HttpServletRequest request, @Validated @RequestBody VerifyAccountRequest verifyAccountRequest){
            //日志公共的前缀
            String logmsg = "[用户常规事件] 当前登录者=" + id + ", 事件描述={}, 请求参数=" + verifyAccountRequest.toString() + ", 请求头=" + FyUtils.getHeadersInfo(request);

            logger.info("日志内容");
        }
    }
```


4. 整合druid插件(alibaba 连接池)
* 比较流行的数据库连接池，代替传统的C3P0、JDBC
```
    需引入以下jar包. 版本都为1.1.10
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>1.1.10</version>
    </dependency>
    <#-- 注意：没有以下jar包会时无法读取yml文件中的druid连接池的补充设置 -->
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid-spring-boot-starter</artifactId>
        <version>1.1.10</version>
    </dependency>
```
```
    >> springboot 项目 yml配置数据库
    >> 最新版本druid连接池的补充设置现在必须配置在spring.druid下
    spring:
      datasource:
        #是否启用数据库模式
        enable: true
        # 使用druid数据源
        driver-class-name: com.mysql.cj.jdbc.Driver
        type: com.alibaba.druid.pool.DruidDataSource
        url: jdbc:mysql://localhost:3306/fy?autoReconnect=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
        username: root
        password: password
        druid:
          # 下面为连接池的补充设置，应用到上面所有数据源中
          # 初始化大小，最小，最大
          initialSize: 1
          minIdle: 3
          maxActive: 20
          # 配置获取连接等待超时的时间
          maxWait: 60000
          # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
          timeBetweenEvictionRunsMillis: 60000
          # 配置一个连接在池中最小生存的时间，单位是毫秒
          minEvictableIdleTimeMillis: 30000
          validationQuery: select 'x'
          testWhileIdle: true
          testOnBorrow: false
          testOnReturn: false
          # 打开PSCache，并且指定每个连接上PSCache的大小
          poolPreparedStatements: true
          maxPoolPreparedStatementPerConnectionSize: 20
          # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
          filters: config,stat,wall,slf4j
          #打开慢SQL记录
          logSlowSql: true
          # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
          connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000;config.decrypt=true;config.decrypt.key=public_key
          # 合并多个DruidDataSource的监控数据
          #useGlobalDataSourceStat: true
          # 配置DruidStatFilter
          web-stat-filter:
            enabled: true
            url-pattern: "/*"
            exclusions: "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"
          # 配置DruidStatViewServlet
          stat-view-servlet:
            url-pattern: "/druid/*"
            # IP白名单(没有配置或者为空，则允许所有访问)
            allow: 127.0.0.1 #,192.168.163.1
            # IP黑名单 (存在共同时，deny优先于allow)
            deny: #192.168.1.73
            #  禁用HTML页面上的“Reset All”功能
            reset-enable: false
            # 登录名
            login-username: admin
            # 登录密码
            login-password: 123456
```
* 上面yml末尾还配置了druid监控数据库操作，展示在页面中，访问 http://localhost:8081/druid/login.html，输入设置好的账号密码就可以访问了