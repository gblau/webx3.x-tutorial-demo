# webx demo

Spring4 ContextLoaderListener已经没有那个重写的方法。所以无法将webx适配Spring4



Webx turbine 中vm页面部分demo尚未整合，demo功能还没有完善。



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



## Webx启动

使用webx，比较核心的就是使用webx framework来增强Spring，所以要配置一个webx项目，现在web.xml中配置相关过滤器和监听器。

#### 初始化spring容器，在web.xml中添加监听器

``` Java
<listener>
        <listener-class>com.alibaba.citrus.webx.context.WebxContextLoaderListener</listener-class>
</listener>
```

`WebxContextLoaderListener`用于初始化`Spring`，装载`/WEB-INF/webx.xml`, `/WEB-INF/webx-*.xml`，为每个子应用创建`Spring`容器.
`WebxContextLoaderListener`是从`spring`的`ContextLoaderListener`派生出来的。所使用的方法是重写createContextLoader方法，让webx使用webx自己的ContextLoader类来构建项目。这种方法Spring4已经抛弃，所以Webx项目无法升级成Spring4.



#### 初始化日志框架

```java
  <listener>
    <listener-class>com.alibaba.citrus.logconfig.LogConfiguratorListener</listener-class>
  </listener>
```

Webx Framework使用SLF4J作为它的日志框架。因此Webx Framework理论上支持所有日志系统。

  目前仅支持log4j 和logback，在WEB-INF下放置相应的配置文件即可。、



### 配置Webx 相关服务

#### `webx.xml` 和 `webx-*.xml`

`webx.xml`是webx的主配置文件，webx启动后，会生成对应的context作为整个应用的根容器。

`webx-*.xml`的配置将会作为子容器使用，创建时自动关联父容器的引用。

就这样Webx Framework将一个WEB应用分解成多个小应用模块：app1、app2，当然名字可以任意取。

- 每个小应用模块独享一个Spring Sub Context子容器。两个子容器之间的beans无法互相注入。
- 所有小应用模块共享一个Spring Root Context根容器。根容器中的bean可被注入到子容器的bean中；反之不可以。

**webx至少需要一个子模块**，所以即使项目很小，最少还是需要配置两个模块来运行webx项目。

#### 文件上传

使用`services:upload`标签定义上传文件配置，参数详见webx.xml项目配置。

可在request context中配置过滤参数，一个可能的配置如下：

```
<services:request-contexts>
        <request-contexts:parser URIEncoding="UTF-8" useBodyEncodingForURI="false" >
            <request-contexts:filters xmlns="http://www.alibaba.com/schema/services/request-contexts/parser/filters">
                <!-- 文件上传白名单 -->
                <uploaded-file-whitelist extensions="xls" />
            </request-contexts:filters>
        </request-contexts:parser>
</services:request-contexts>
```

上面的配置将会禁止文件名后缀不在列表中的文件被上传到服务器上。如果做得更好一点，你甚至可以对上传文件进行病毒扫描。



#### Webx的Resource Loading服务

资源表现形式的多样性，给应用程序的接口设计带来一点麻烦，为了统一资源的获取，Spring框架中提供了这方面的服务，即Resource Loader，但是Resource Loader还存在一些不合理的地方。

Spr ing ResourceLoader是由ApplicationContext来实现的。而你一次只能选择一种ApplicationContext的实现，同时只能使用绝对路径让他难以在多环境下部署，而且也很难拓展。



webx中提供了Resource Loading Service对资源进行统一管理，在Resource Loading Service中可以包含多个不同的Resource Loader进行资源的加载，使得加载资源具有多样性，同时也很好的完成了资源加载的大部分功能。



事实上，webx的ResourcesLoaderService也是从Spring的ResourceLoader上派生而来的。如果使用Spring原来的ResourceLoader也没有什么问题，Webx不会报错。



如果想要将Spring ResourceLoader机制替换成Webx的Resource Loading服务，可以像这样配置：

```
<resource-loading
        xmlns="http://www.alibaba.com/schema/services"
        xmlns:res-loaders="http://www.alibaba.com/schema/services/resource-loading/loaders">

    <resource-alias pattern="/" name="/webroot" />

    <resource pattern="/webroot" internal="true">
        <res-loaders:webapp-loader />
    </resource>
    <resource pattern="/classpath" internal="true">
        <res-loaders:classpath-loader />
    </resource>
```

具体的解释可以参见项目中的配置。



#### request context

web的filter可以修改Request和Response中的数据，也可以改变请求的流程。但是对这两个方面，业务逻辑处理都略显乏力。

Webx中将filter拆成pipeline和Request Context，前者专门用来处理执行流程，后者专门用来访问和修改数据。

Webx Framework提供了一个request contexts服务。Request contexts服务利
用HttpServletRequestWrapper和HttpServletResponseWrapper对request和response对象进行包装，以实现新的功能。你可以把Request Context看作是HttpServletRequest和HttpServletResponse这两个对象的总和。除此之外，多个Request Context可以被串接起来，被称为Request Context Chain，类似于filter chain。

> 在一个请求开始的时候，每个Request Context的“预处理”过程被依次调用。最内层的（即最先的）Request Context最先被调用，最外层的（即最后的）Request Context最后被调用；

在一个请求结束的时候，每个Request Context的“提交”过程被依次调用。和“预处理”的顺序相反，最外层的（即最后的）Request Context最先被调用，最内层的（即最先的）Request Context最后被调用。

下面是官方文档给出的例子：

```
<services:request-contexts xmlns="http://www.alibaba.com/schema/services/request-contexts">
  <basic />
  <buffered />
  <lazy-commit />
  <parser />
  <set-locale defaultLocale="zh_CN" defaultCharset="UTF-8" />
  ...
  </services:request-contexts>
```

更多解释可以在项目注释中查看。



#### pipeline

Pipeline的设计和filter非常相似，也是击鼓传花式的流程控制。但是有几点不同：

- Pipeline只能控制流程，不能改变request和response。
- Pipeline是轻量级组件，它甚至不依赖于WEB环境。Pipeline既可以在程序中直接装配，也可以由spring和schema来配置。
- Pipeline支持更复杂的流程结构，例如：子流程、条件分支、循环等。

pipeline一般单独放置一个文件配置，可以在项目中查看例子。



#### 工具类 pullService

可以提供Webx Turbine页面的一系列类似于Util的功能。将beans暴露给模板。这里定义的tools可被所有components(页面)之间共享。例如在页面可以通过$!{myPullTool.jsAndCssDomain}，直接获取类中的属性值。

类似：

```
<services:pull xmlns="http://www.alibaba.com/schema/services/pull/factories">
  <form-tool />
  ...
</services:pull>
```

**每一个配置的标签都需要一个相应的xml配置，否则会抛出找不到相关服务的异常。**

比如`<form-tool />`需要`<services:form>`标签配置支持。



#### MappingRuleService

 名称查找规则。Maping Rule Service是将target映射到另一种事物或者进行相反映射的规则。简单地说，可以把jsp映射成htm后缀。

webx中所有的要查找的bean都要有这个来管理。所以没有配置这个的话会抛出无法加载bean的异常。



## Webx Turbine form表单和 vm页面

待完善。