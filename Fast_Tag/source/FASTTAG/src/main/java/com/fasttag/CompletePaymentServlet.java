package com.fasttag;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.Instant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CompletePaymentServlet")
public class CompletePaymentServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		FastTag fastTag = new FastTag();
		
		response.setHeader("Access-Control-Allow-Origin", "*");
    	response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    	response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		 
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while((line = reader.readLine()) != null)
        {
        	sb.append(line);
        }
        
        String fasttagId = null;
        String vehicleNumber = null;
        int amount = 0;
        String paymentType = null;
        
        try
        {
        	org.json.JSONObject jsonObject = new org.json.JSONObject(sb.toString());
        	if (!jsonObject.isNull("fasttagId") && !jsonObject.get("fasttagId").toString().isEmpty()) {
        	    fasttagId = String.valueOf(jsonObject.getLong("fasttagId"));
        	} else {
        	    fasttagId = null;
        	}

        	vehicleNumber = jsonObject.getString("vehicleNumber");
        	amount = jsonObject.getInt("totalAmount");
        	paymentType = jsonObject.getString("paymentType");
        }
        catch(Exception e)
        {
        	e.printStackTrace();
    	}
        
        if((fasttagId=="" || fasttagId == null)  && vehicleNumber!=null)
        {
        	FastTagUser id = fastTag.findFastTagId(vehicleNumber);
        	 fasttagId =String.valueOf(id.getFastTagId());
        }
        
        long currentTime = Instant.now().toEpochMilli();

		 fastTag.updateLastEntryTime(Integer.parseInt(fasttagId), currentTime);
		 fastTag.updateVehicleLog(Integer.parseInt(fasttagId), amount, paymentType);
		 
		 
	}
	
	@Override
	 protected void doOptions(HttpServletRequest request, HttpServletResponse response)
	         throws ServletException, IOException {
	     response.setHeader("Access-Control-Allow-Origin", "*");
	     response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	     response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
	     response.setStatus(HttpServletResponse.SC_OK);
	 }

}
