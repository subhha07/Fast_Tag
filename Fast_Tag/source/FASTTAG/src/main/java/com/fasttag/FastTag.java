package com.fasttag;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;

import org.json.JSONObject;

import java.sql.ResultSet;

public class FastTag
{	
    String url = "jdbc:mysql://unitbox.ceohlvwm6aln.us-west-2.rds.amazonaws.com:3306/fasttag";
    String userName = "admin";
    String password = "unitbox23";
    
    private static final long THIRTY_MINUTES_IN_MILLIS = Duration.ofMinutes(30).toMillis();
    private static final long REGISTRATION_FEE = 50;
    
    private Connection connection;
    
    public FastTag() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize DB connection");
        }
    }

    public VehicleFare registerVehicle(String vehicleNo, String ownerName, String vehicleType)
    {
        String addUser = QueryConstants.REGISTER_USER;
        try (PreparedStatement statement = connection.prepareStatement(addUser))
        {
        	
            statement.setString(1, vehicleNo);
            statement.setString(2, ownerName);
            statement.setString(3, vehicleType);
            statement.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public JSONObject isRegisteredUser(String vehicleNo)
    {
    	JSONObject returnObject = new JSONObject();
    	
    	String vechileSelectQuery = QueryConstants.GET_CONFIG_BY_VEHICLENO;
    	try (PreparedStatement statement = connection.prepareStatement(vechileSelectQuery))
    	{
           statement.setString(1, vehicleNo);
           ResultSet result = statement.executeQuery();

           if(result.next())
           {
        	   FastTagUser user = new FastTagUser();
               user.setFastTagId(result.getLong(QueryConstants.FASTTAG_ID));
               user.setOwnerName(result.getString(QueryConstants.OWNER_NAME));
               user.setVehicleType(result.getString(QueryConstants.VEHICLE_TYPE));
               
               returnObject.put("isRegisteredUser", true);
               returnObject.put("fasttagId", user.getFastTagId());
               returnObject.put("ownerName", user.getOwnerName());
               returnObject.put("vehicleType", user.getVehicleType());
               
               double totalAmount = calculateTollForRegisteredUser(vehicleNo);
               returnObject.put("totalAmount", totalAmount);
               
           }
           else
           {
        	   returnObject.put("isRegisteredUser", false);
           }
           
    	}
    	catch (Exception e)
        {
            e.printStackTrace();
        }
    	
    	return returnObject;
    
	}
    
    public FastTagUser findFastTagId(String vehicleNo)
    {
        String vechileSelectQuery = QueryConstants.GET_FASTTAG_ID;

        try (PreparedStatement statement = connection.prepareStatement(vechileSelectQuery))
        {
                statement.setString(1, vehicleNo);
                ResultSet result = statement.executeQuery();

                if (result.next())
                {
                    FastTagUser user = new FastTagUser();
                    user.setFastTagId(result.getLong(QueryConstants.FASTTAG_ID));
                    return user;
                }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public FastTagUser findByVehicleNo(String vehicleNo)
    {
        String vechileSelectQuery = QueryConstants.GET_CONFIG_BY_VEHICLENO;

        try (PreparedStatement statement = connection.prepareStatement(vechileSelectQuery))
        {
                statement.setString(1, vehicleNo);
                ResultSet result = statement.executeQuery();

                if (result.next())
                {
                    FastTagUser user = new FastTagUser();
                    user.setFastTagId(result.getLong(QueryConstants.FASTTAG_ID));
                    user.setVehicleNumber(result.getString(QueryConstants.VEHICLE_NO));
                    user.setOwnerName(result.getString(QueryConstants.OWNER_NAME));
                    user.setVehicleType(result.getString(QueryConstants.VEHICLE_TYPE));
                    user.setLastEntryTime(result.getLong(QueryConstants.LAST_ENTRY_TIME));
                    return user;
                }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public Double findFareByVehicleType(String vehicleType)
    {
    	Double fare = VehicleFareCache.getFare(vehicleType, connection);
    	return fare != null ? fare : 0.0;
  
    }

    public void updateLastEntryTime(int fasttagId, long lastEntryTime)
    {
        String updateLastEntryTime = QueryConstants.UPDATE_LAST_ENTRY_TIME;
        try (PreparedStatement statement = connection.prepareStatement(updateLastEntryTime))
        {
            statement.setLong(1, lastEntryTime);
            statement.setInt(2, fasttagId);

            statement.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private double calculateTollForRegisteredUser(String vehicleNo)
    {
    	double totalAmount = 0;
		 FastTagUser user = findByVehicleNo(vehicleNo);
		 if (user == null)
		 {
           return totalAmount;
           
		 }
		 
		 double vehicleFare = findFareByVehicleType(user.getVehicleType());
		 if (vehicleFare == 0)
		 {
			 throw new RuntimeException("Fare not found for vehicle type: " + user.getVehicleType());
		 }
		 
		 long currentTime = Instant.now().toEpochMilli();

		 if(user.getLastEntryTime() != null && (currentTime - user.getLastEntryTime()) < THIRTY_MINUTES_IN_MILLIS)
		 {
			 totalAmount += vehicleFare/2;
		 }
		 else
		 {
			 totalAmount += vehicleFare;
		 }
		 
		 return totalAmount;
   }
    
   public void updateVehicleLog(int fasttagId, int amount, String paymentType)
    {
    	String getFare = QueryConstants.UPDATE_VEHICLE_LOG;

        try (PreparedStatement statement = connection.prepareStatement(getFare))
        {
            statement.setInt(1, fasttagId);
            statement.setInt(2, amount);
            statement.setInt(3, PaymentType.fromType(paymentType));
            statement.executeUpdate();
   
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
