### 说明
1. 系统管理后台基本框架admin，包括用户管理，角色管理，资源链接管理模块，可以动态分配权限和角色。
2. 使用springboot、springdata jpa、shiro等服务端技术，使用freemarker模版渲染页面。
3. 系统中对springdata的基本查询条件做了简单的封装，更加方便查询。
4. 前端技术：使用AdminLTE2系统模版，消息弹窗使用layer插件，日期选择使用laydate插件，jQuery等等。
5. 系统部署：
    1. 方式一： 
        1. 使用mysql数据库，先建立一个空数据库admin，最好编码使用utf-8字符集，不然会乱码。
        2. 把application.properties中的数据库连接信息修改成自己数据库的连接信息。
        3. 修改spring.jpa.hibernate.ddl-auto为create，目的是让系统自动建表同时初始化相关集成数据。如果不需要自动初始化数据，可以删除resource目录的import.sql文件。
    2. 方式二：
        1. 直接导入admin.sql脚本到数据库。 
        2. 把application.properties中的数据库连接信息修改成自己数据库的连接信息。 
        3. 修改spring.jpa.hibernate.ddl-auto为none，目的是防止hibernate自动修改表结构。
6. 系统启动后，访问：127.0.0.1/admin/会自动跳转到后台登录页面。
7. 初始用户名和密码为：admin/111111。
