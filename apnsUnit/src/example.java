//
//  example.java
//  apnsUnit
//
//  Created by LuTH on 2012-4-11.
//  Copyright 2012 dannytiehu@hotmail.com All rights reserved.
//

public class example {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String url = "http://192.168.1.100:8080/";

		// 1.获取从指定时间开始失效的设备号
		// from: 开始时间，格式如：2012-04-12 15:04:55
		// online: 0表示获取失效用户; 1表示获取在线用户
		// 返回结果为字符串，如：{"data":["9ab043eb976c414cb371743fbe9a1900", "ab9e9846279c4269bf5a0c75849d77fb", "acdc92733a444a53a4c9131c16f18be2"]}
		String response = GetPostUtils.sendGet(
				url + "user_api.do",
				"from=2013-07-13%2016:33:34&online=1");

		System.out.println("GET:" + response);
		
		// 2.发送推送消息（建议用GET方式.限制发送的字符长度256字节,防止消息推送耗费用户流量,同时和iPhone消息推送的机制保持一致）。注意:发送中文可能会有乱码,方法3可解决
		// action=send 发送推送消息
		// broadcast: Y表示群发,N表示发送给指定用户
		// username: 指定用户的username,如:feddfa7ca6f14d649a36c74e5e7062b4
		// title: 推送消息的标题
		// message: 推送消息的内容
		// uri: 隐式传递的参数，如推送消息的id
		String response2 = GetPostUtils.sendGet(
						url + "notification_api.do",
						"action=send&broadcast=N&username=bfc990e5f54d4667a02217fb4f0f27af&title=FE协同办11公&message=GET世界，你好&uri=");

		System.out.println("GET:" + response2);



        String test="{\"curriculumId\":948,\"isSubmit\":true,\"trans\":2}";
		
		// 3.用POST方式发送
		/*--拼接POST字符串--*/
		final StringBuilder parameter = new StringBuilder();
		parameter.append("action=send&broadcast=Y&username="); // 单条发送这里要设成N,若设成Y则广播,全部收到,后面参数无效
		parameter.append("aa7885c45480440aa7fcad8134273f57");
		parameter.append("&title=FE协作平台&message=");
		parameter.append(test);
		parameter.append("&uri=");
		parameter.append(""); // 和推送给iPhone格式保持一致
		/*--End--*/
		 String resp = GetPostUtils.send("POST", url + "notification_api.do", parameter);
		 System.out.println("response:" + resp);

	}
}
