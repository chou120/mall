# banyuanmall
banyuan 商城


# 启动alibaba的nacos 注册中心  启动这个注册中心的时候需要使用单机命令启动 
# 而不是  直接使用 ./start.sh  因为这个nacos 中心配置文件里面默认的是集群配置  
# 在本地使用   bash startup.sh -m standalone
### bash与sh是有区别的，两者是不同的命令，且bash是sh的增强版，
### 而"[[]]"是bash脚本中的命令，因此在执行时，使用sh命令会报错。
### 因而是因为startup.sh文件中的[[ ]] 的问题，sh命令并不能识别，只有bash才行。

### 启动成功后
### 访问http://localhost:8848/nacos/index.html即可看到登录界面

## 然后我们需要在每一个微服务的配置文件中指定注册中的地址
```
如: cloud:
      nacos:
        discovery:
          server-addr: 127.0.0.1:8848
配置完成之后还需要开启注册服务的发现注解


```