# webx demo

Spring4 ContextLoaderListener已经没有那个重写的方法。所以无法将webx适配Spring4

## Webx的三大架构

SprngExt：Webx的基础架构，基于Spring，为Webx提供一个通用的拓展机制。

Webx Framework：基于ServletAPI，Webx风格的Web层次架构，提供Web的基础服务。和Spring在同一层次，事实上，Webx就是增强、或者说魔改的Spring，阿里命名为webx。

Webx Turbine：实现具体的网页功能，例如页面跳转，数据发送、页面展示和渲染等。和Spring MVC在同一层次。和最新的MVC的思想不同，他没有主流的靠拢RESTful思想，而是Struct2约定由于配置思想的延续。

### 可定制

我们平时做一些课设或者外包项目、开源项目时，一般都是用SpringExt作为bean的载入框架、Spring
作为封装Servlet的Web框架，Spring MVC作为前端页面的展示和渲染框架。大概看起来就是：

xml载入bean -> Spring  -> SpringMVC

这里面的任何一个层面都可以用其他框架代替，比如xml载入bean可以换成webx，SpringMVC可以换成Struct2等等。

所以在一个webx中看起来是这个样子：

Spring Ext(extends spring schema) -> Webx Framework(extends Spring) -> Webx Turbine

如果觉得webxturbine不好用 可以用spring mvc 代替，就这样。

## 运行这个项目

### 需要的环境

- JDK 1.7
- IDE：intelliJ IDEA 或者 eclipse
- 版本控制工具：git
- 版本管理：maven

### 如何获取项目

git命令行输入：
`https://github.com/gblau/webx3.x-tutorial-demo.git`
克隆整个项目

### 运行

#### eclipse

以管理员身份运行eclipse-->eclipse-->import-->Existing Maven Projects，把整个克隆下来的webxdemo导入，打开window-->show view-->Problems界面，按提示解决可能出现的错误。
之所以要以管理员身份运行，是因为webx在加载文件上传模块时，会创建一个/tmp目录（在webx.xml中配置），若不以管理员身份运行eclipse，待会在跑的时候可能因权限不够，无法创建/tmp文件夹而报错。

#### idea

在选择项目界面import，使用maven导入，选择运行版本为jdk1.7即可。

#### 导入sql文件

将resources下的webx_demo.sql文件导入数据库。
