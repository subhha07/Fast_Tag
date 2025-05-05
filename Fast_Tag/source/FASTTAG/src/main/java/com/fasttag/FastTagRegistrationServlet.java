//$Id$
package com.fasttag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@WebServlet("/RegisterUser")
public class FastTagRegistrationServlet extends HttpServlet
{
	FastTag fastTag = new FastTag();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
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
        
        String vehicleNumber = null;
        String ownerName = null;
        String vehicleType = null;
        
        try
        {
        	org.json.JSONObject jsonObject = new org.json.JSONObject(sb.toString());
    		vehicleNumber = jsonObject.getString("vehicleNumber");
    		ownerName = jsonObject.getString("ownerName");
    		vehicleType = jsonObject.getString("vehicleType");
        }
        catch(Exception e)
        {
        	e.printStackTrace();
    	}

        VehicleFare vehicleFare= fastTag.registerVehicle(vehicleNumber, ownerName, vehicleType);

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
