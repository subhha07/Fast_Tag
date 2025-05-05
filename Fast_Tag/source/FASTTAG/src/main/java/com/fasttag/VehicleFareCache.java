//$Id$
package com.fasttag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * To Cache the Fare config from VEHICLE_FARE_CONFIG to avoid repeated querying
 */
public class VehicleFareCache
{
    private static final Map<String, Double> fareConfigCache = new ConcurrentHashMap<>();

    private static void loadFareCache(Connection connection)
    {
        String query = QueryConstants.GET_FARE_BY_VEHICLETYPE;
        
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery())
        {
        	fareConfigCache.clear();
            
            while(result.next())
            {
            	fareConfigCache.put(result.getString("VEHICLE_TYPE"), result.getDouble("FARE"));
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static Double getFare(String vehicleType, Connection connection)
    {
    	if(fareConfigCache.isEmpty())
    	{
    		loadFareCache(connection);
        }
        return fareConfigCache.get(vehicleType);
    }

    public static void refreshCache(Connection connection)
    {
        loadFareCache(connection);
    }

    public static boolean containsVehicleType(String vehicleType)
    {
        return fareConfigCache.containsKey(vehicleType);
    }
}
