Android-push
============

修改了Androidpn的一些bug，修改了离线消息的一些bug（基于网上的离线消息版本）

客户端1：
org.androidpn.client.Constants  添加3个常量 XmppManager xmppManager  后面用来获取连接发送回执。
org.androidpn.client.NotificationDetailsActivity  查看通知的界面，当此界面激活时，发送查看回执给服务端  packetId，notificationFrom 通过这2个参数基本能确定具体的通知。但不保证重复。
org.androidpn.client.NotificationIQProvider  忽略
org.androidpn.client.NotificationPacketListener   通知监听，收到通知时，发送接收回执给服务端
org.androidpn.client.NotificationReceiver  传递notificationFrom，packetId
		    .NotificationService   存储全局变量Constants.xmppManager = xmppManager;
		    .Notifier              传递notificationFrom，packetId

记得将androidpn.properties,xmppHost=192.168.7.233   修改成自己的IP

客户端2：
org.androidpn.client.XmppManager 修改了查询用户是否注册的功能，（向服务器进行请求），加入短线重连功能 



服务端：
hibernate.cfg.xml    添加一个<mapping class="org.androidpn.server.model.NotificationMO" />

spring-config.xml    添加一个service ,一个dao

org.androidpn.server.console.controller.NotificationController 添加一个给所有用户发送消息的功能，将原来的根据用户名发送改成了可指定多个用户发送

org.androidpn.server.dao.hibernate.NotificationDaoHibernate    通知数据库操作DAO实现类

org.androidpn.server.dao.NotificationDao	接口

org.androidpn.server.model.NotificationMO   通知实体对象  保存通知信息  启动时自动在数据库建一张apn_notification表

org.androidpn.server.model.ReportVO	    暂时无用

org.androidpn.server.service.impl.NotificationServiceImpl	    通知数据库操作SERVICE

org.androidpn.server.service.NotificationService	接口

org.androidpn.server.service.ServiceLocator		注册notificationService

org.androidpn.server.util.CopyMessageUtil		将生产的消息ID 复制给通知对象

org.androidpn.server.xmpp.net.StanzaHandler		用户上线时发送离线消息

org.androidpn.server.xmpp.push.NotificationManager      消息推送类 ，改动比较大。增加几个方法，增加入库操作

org.androidpn.server.xmpp.router.IQRouter		接收消息，收到回执消息修改数据库状态

WebRoot\WEB-INF\pages\notification\form.jsp		加个功能

-----------------------------------------------------
org.androidpn.server.xmpp.net.XmppIoHandler           session 超时时间

org.androidpn.server.xmpp.net.Connection              加入消息包是否发送成功的判断

org.androidpn.server.timer                            加入作业调度功能，能够定期清理发送消息的数据库表
