package org.androidpn.server.dao;

import java.util.List;

import org.androidpn.server.model.NotificationMO;
import org.androidpn.server.model.ReportVO;

/**
 * @author chengqiang.liu
 *
 */
public interface NotificationDao {
	
	/**
	 * 保存通知信息
	 * @param notificationMO
	 */
	public void saveNotification(NotificationMO notificationMO);
	
	/**
	 * 修改通知信息
	 * @param notificationMO
	 */
	public void updateNotification(NotificationMO notificationMO);
	
	/**
	 * 删除通知
	 * @param id
	 */
	public void deleteNotification(Long id);
	
	/**
	 * 查看通知
	 * @param id
	 * @return NotificationMO
	 */
	public NotificationMO queryNotificationById(Long id);
	
	/**
	 * 根据用户名和通知ID查询通知
	 * @param userName	用户名
	 * @param messageId	通知ID
	 * @return List<NotificationMO
	 */
	public List<NotificationMO> queryNotificationByUserName(String userName,String messageId);

	/**
	 * 根据状态和ID统计通知数量
	 * @param status	状态
	 * @param messageId ID
	 * @return
	 */
	public int queryCountByStatus(String status,String messageId);
	
	/**
	 * 根据条件查询通知列表
	 * @param mo 查询条件
	 * @return List<ReportVO>
	 */
	public List<ReportVO> queryReportVO(NotificationMO mo);
	
	/**
	 * 根据条件查询通知列表
	 * @param mo 查询条件
	 * @return List<NotificationMO>
	 */
	public List<NotificationMO> queryNotification(NotificationMO mo);

}
