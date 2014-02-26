/**
 * 
 */
package org.androidpn.server.service;

import java.util.List;

import org.androidpn.server.model.NotificationMO;

/**
 * @author chengqiang.liu
 *
 */
public interface NotificationService {
	
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
	 * 批量保存通知
	 * @param notificationMOs
	 */
	public void createNotifications(List<NotificationMO> notificationMOs);
	
	/**
	 * 根据用户名和通知ID查询通知
	 * @param userName	用户名
	 * @param messageId	通知ID
	 * @return NotificationMO
	 */
	public NotificationMO queryNotificationByUserName(String userName,String messageId);
	
	/**
	 * 根据条件查询通知列表
	 * @param mo 查询条件
	 * @return List<NotificationMO>
	 */
	public List<NotificationMO> queryNotification(NotificationMO mo);
}
