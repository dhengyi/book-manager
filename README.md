# XiyouLinux Group 图书借阅平台

本系统是一个改善图书管理手段的Web项目。前端使用`Bootstrap`、`jQuery`框架增强用户与系统之间的交互，后端使用`Spring`与`Spring MVC`降低代码之间耦合度并提升开发效率。

本系统实现了以下基本功能模块，并在不断改善之中：

> - 登录模块
> - 上传书籍模块
> - 下架书籍模块
> - 我的书籍模块
> - 修改信息模块
> - 书籍详情模块
> - 书籍借阅模块
> - 书籍评论模块
> - 标签分类模块

## 环境需求
>- JDK 1.8
>- IDEA
>- Maven
>- MySQL

## 架构说明
```
annotation ---- <-自定义注解类

config ----spring <-spring配置类
       |
       ----database <-数据库配置类
       |
       ----cos <-对象存储服务配置类

dao ----dbfactory <-dao接口的生产工厂
    |
    ----dbimpl <-数据库接口的实现类
    |
    ----dbservice <-数据库所提供的调用接口
    |
    ----rowmapper <-自定义JdbcRowmapper类

model ----po <-数据库持久化层model
      |
      ----vo <-有关视图层model
      
utilclass ---- <-自定义工具类
      
web ----filter <-身份验证之过滤器
    |
    ----label <-标签页控制器
    |
    ----loginandunlogin <-未登录页面与登录后主页面控制器
    |
    ----mybook <-我的书籍页面控制器
    |
    ----showbook <-书籍详情页控制器
    |
    ----upload <-书籍上传页控制器
    
WEB-INF ----views <-前端页面
```

## 使用说明
MySQL环境下载（不含测试数据）：[![download][1]][2]

源代码直接clone到本地，不提供压缩包了。

部分页面效果：

1. 登录后主页面：
![此处输入图片的描述][3]

2. 标签页：
![此处输入图片的描述][4]

3. 书籍详情页：
![此处输入图片的描述][5]

## TODO
 1. 优化Ajax与后台交互之间的代码逻辑
 2. 优化部分JS代码，改善用户体验
 3. 修复部分bug与漏洞
 4. 加入Redis缓存，提高系统响应速度

## 版本说明
![version 1.0][6]

## Email
```
spider_hgyi@outlook.com
```


  [1]: https://img.shields.io/badge/download-MySQL-brightgreen.svg
  [2]: https://1drv.ms/u/s!Alo1-VlEZGPPdu7oO4YMYTapC3g
  [3]: http://i4.bvimg.com/633787/52421f9a695286db.png
  [4]: http://i2.bvimg.com/633787/efe6964f5e49eacd.png
  [5]: https://github.com/championheng/book-manager/blob/master/picture/1.png
  [6]: https://img.shields.io/badge/version-1.0-blue.svg
