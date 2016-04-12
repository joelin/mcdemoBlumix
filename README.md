#spring-boot demo
在 bluemix上演示的适应于paas的应用，包含db的crud操作，session使用redis管理。

##本地编译运行

###修改配置文件

修改 `/src/main/resources/spring-cloud-bootstrap.properties` 文件中的 `${projectbase}` 为工程的路径

修改 application-cloud.properties 内容如下,修改ip地址即可（数据库及redis）

```
spring.cloud.appId: microserviceLocal
spring.cloud.database: mysql://root:admin123@172.17.254.218:3306/test

spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true

spring.mvc.view.prefix= /WEB-INF/views/
application.message= hello jsp web

#redis single
spring.cloud.redis.host=localhost
spring.cloud.redis.port=6379
```

###运行
使用以下命令运行
> mvn spring-boot:run

##bluemix运行
根据`manifest.yml`在bluemix上创建好需要的服务，并修改为正确的服务标示，另外还需要修改应用名称和主机名
```
applications:
- path: target/springbootBluemix.war
  memory: 256M
  instances: 1
  domain: mybluemix.net
  name: mcdemo   ＃需要修改
  host: mcdemo   ＃需要修改
  disk_quota: 1024M
  services:
  - mysqlserver    #数据库服务,修改
  - Redis Cloud-qd # redis服务,修改
```

###编译打包
使用以下命令打包应用
>mvn clean package  

###接着根据cf指令推送应用，等待应用部署完成
> cf push
