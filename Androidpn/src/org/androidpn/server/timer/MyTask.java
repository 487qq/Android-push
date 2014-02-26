package org.androidpn.server.timer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MyTask {

	public void run() {

		try {

			Connection conn = getConnection();
			if (conn != null && !conn.isClosed()) {
				Statement statement = conn.createStatement();

				// 要执行的SQL语句

				String sql = "truncate table apn_notification";
				statement.execute(sql);
				statement.close();
				conn.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 返回一个与特定数据库的连接 */
	public Connection getConnection() {
		Connection connection = null;
		try {
			// 加载属性文件，读取数据库连接配置信息
			Properties pro = new Properties();
			String base=this.getClass().getClassLoader().getResource("/").getPath();
			System.out.println(base);
			try {
				pro.load(this.getClass().getClassLoader()
						.getResourceAsStream("/jdbc.properties"));
			} catch (IOException e) {
				System.out.println("未找到配置文件！！！");
			}
			String url = pro.getProperty("jdbcUrl");
			String user = pro.getProperty("jdbcUsername");
			String password = pro.getProperty("jdbcPassword");
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

}
