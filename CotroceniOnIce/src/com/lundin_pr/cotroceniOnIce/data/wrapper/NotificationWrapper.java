package com.lundin_pr.cotroceniOnIce.data.wrapper;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.lundin_pr.cotroceniOnIce.data.entity.Notification;

/**
 * Helper class to wrap a list of notifications
 * 
 * 
 * @author Eirikur / Jan 23, 2017
 */
@XmlRootElement(name = "notifications")
public class NotificationWrapper {

	private List<Notification> notifications;
	
	@XmlElement(name = "notification")
	public List<Notification> getNotifications(){
		return notifications;
	}
	
	public void setNotifications(List<Notification> notifications){
		this.notifications = notifications;
	}
}
