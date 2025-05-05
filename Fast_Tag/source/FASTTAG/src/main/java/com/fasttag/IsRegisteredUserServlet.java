package com.fasttag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@WebServlet("/IsRegisteredUser")
public class IsRegisteredUserServlet extends HttpServlet
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
		 try
		 {
			 org.json.JSONObject jsonObject = new org.json.JSONObject(sb.toString());
			 vehicleNumber = jsonObject.getString("vehicleNumber");
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 
		 JSONObject jsonObject = fastTag.isRegisteredUser(vehicleNumber);

		 response.setContentType("application/json");
		 response.getWriter().write(jsonObject.toString());
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
