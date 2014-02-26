/**
 * 
 */
package org.androidpn.server.model;

import java.sql.Timestamp;

/**
 * @author chengqiang.liu
 *	消息统计
 */
public class ReportVO {
	
	private String messageId;
    
    private String apiKey;

    private String title;

    private String message;
    
    private String uri;
    
    private Timestamp createTime;
    
    private int notSendCount;
    
    private int sendCount;
    
    private int receiveCount;
    
    private int readCount;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public int getNotSendCount() {
		return notSendCount;
	}

	public void setNotSendCount(int notSendCount) {
		this.notSendCount = notSendCount;
	}

	public int getSendCount() {
		return sendCount;
	}

	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}

	public int getReceiveCount() {
		return receiveCount;
	}

	public void setReceiveCount(int receiveCount) {
		this.receiveCount = receiveCount;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
    
    
}
