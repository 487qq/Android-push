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


