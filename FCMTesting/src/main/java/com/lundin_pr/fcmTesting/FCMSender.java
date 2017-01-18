package com.lundin_pr.fcmTesting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.android.gcm.server.Sender;

public class FCMSender extends Sender {

    public final static String AUTH_KEY_FCM = "AAAA4JcI6g4:APA91bF7-abq3PMOLpjfu0Ji0Ak1p5Kr8rj6yV-CZWvYmfmuZkrz-BVYT2RXmaUjbc05hMNZOSCufrFi7Sfd4b2Q4PrXHCElj2BKzG1THkojYD0ID8zt53y9utPKG7TJOP9vfJzKYzs2";
	
    public FCMSender(String key) {
        super(key);
    }
    
    public static void main(String[] args) {
    	try {
    		URL url = new URL("https://fcm.googleapis.com/fcm/send");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
			conn.setDoOutput(true);
			
//			String input = "{\"notification\" : {\"title\" : \"Test Testing\", \"body\" : \"Sound test 2\"}, \"to\":\"/topics/allDevices\"}";
			String input = "{\"data\" : {\"title\" : \"Testing with data\", \"msg\" : \"Sound test 2\", \"url\" : \"www.imawesome.com\"}, \"to\":\"/topics/allDevices\"}";
			
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			os.close();
			
			int responseCode = conn.getResponseCode();
		    System.out.println("\nSending 'POST' request to URL : " + url);
		    System.out.println("Post parameters : " + input);
		    System.out.println("Response Code : " + responseCode);
			
		    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    String inputLine;
		    StringBuffer response = new StringBuffer();
		    
		    while((inputLine = in.readLine()) != null){
		    	response.append(inputLine);
		    }
		    in.close();
		    
		    System.out.println(response.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
