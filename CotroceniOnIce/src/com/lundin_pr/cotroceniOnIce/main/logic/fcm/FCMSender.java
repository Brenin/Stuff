package com.lundin_pr.cotroceniOnIce.main.logic.fcm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.lundin_pr.cotroceniOnIce.data.constants.Constants;
import com.lundin_pr.cotroceniOnIce.data.entity.Notification;
import com.lundin_pr.cotroceniOnIce.gui.view.error.ShowException;

public class FCMSender {

	public static void sendNotification(Notification notification){
		try {
			URL url = new URL(Constants.URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "key=" + Constants.AUTH_KEY);
			conn.setDoOutput(true);

			String title = "\"title\" : \"" + notification.getTitle() + "\"";
			String header = "\"header\" : \"" + notification.getHeader() + "\"";
			String msg = "\"msg\" : \"" + notification.getMsg() + "\"";
			String link = "\"url\" : \"" + notification.getLink() + "\"";
			
			String input = "{\"data\" : {" + title + ", " + header + ", " + msg + ", " + link + "}, \"to\":\"/topics/allDevices\"}";
			
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			os.close();
			
			int responseCode = conn.getResponseCode();
			
		    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    String inputLine;
		    StringBuffer response = new StringBuffer();
		    
		    while((inputLine = in.readLine()) != null){
		    	response.append(inputLine);
		    }
		    in.close();
		    
		    notification.setNote("Sending 'POST' request to URL : " + url 
		    		+ "\nPost parameters : " + input 
		    		+ "\nResponse Code : " + responseCode
		    		+ "\nResponse : " + response.toString());
		    
		} catch (IOException e) {
			ShowException.createExceptionDialog(e);
		}
	}
}
