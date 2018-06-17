# 项目使用说明

**代码中src/main/java/com.ctrip.ibu.flight.internalws.service.soa.HelloSOAServiceImpl是样例服务，可以随时参看**

## 新增服务
1. 参看[SOA文档](http://conf.ctripcorp.com/pages/viewpage.action?pageId=61550573)的Step1到Step4，生成SOA代码
2. 在src/main/java/com.ctrip.ibu.flight.internalws.service/soa或者其子package中新建Class实现上一步生成的服务接口，为其添加annotation `@Component`


## 本地运行调试
- 通过src/test/java/com.ctrip.ibu.flight.internalws.service/ServiceStarter.java进行运行或者调试，启动时自动通过embedded Tomcat进行服务发布

## 测试环境或者生产环境发布
- 直接Maven打包即可

## 备注
- Spring配置文件：src/main/resources/application-context.xml，如果需要添加额外的spring配置文件可以通过以下两种方法：
   1. 在src/main/resources/application-context.xml中import，如`<import resource="another-context.xml" />`
   2. 在sr/main/webapp/web.xml的`<context-param>``contextConfigLocation`中增加新的配置文件，通过逗号分隔,如

			<context-param>
			  <param-name>contextConfigLocation</param-name>
			  <param-value>classpath:application-context.xml,classpath:another-context.xml</param-value>
			</context-param>

- 通过sr/main/webapp/web.xml的BaijiServlet配置可以修改服务的base URL，默认为`/api/*`
- 通过src/test/resources/application.properties中的`server.port`进行本地运行时服务端口修改，测试环境或者生产环境的端口不受此配置控制