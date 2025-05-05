package com.fasttag;

public class QueryConstants
{
    public static final String CREATE_FASTTAG_CONFIG_TABLE="created.fasttag.config.table";

    public static final String REGISTER_USER = "INSERT INTO FASTTAG_USER_CONFIG (VEHICLE_NO, OWNER_NAME, VEHICLE_TYPE, LAST_ENTRY_TIME) VALUES (?, ?, ?, UNIX_TIMESTAMP(NOW()) * 1000)";
    public static final String UPDATE_LAST_ENTRY_TIME = "UPDATE FASTTAG_USER_CONFIG SET LAST_ENTRY_TIME = ? WHERE FASTTAG_ID = ?";
    public static final String GET_CONFIG_BY_VEHICLENO = "SELECT * FROM FASTTAG_USER_CONFIG WHERE VEHICLE_NO = ?";
    public static final String GET_FARE_BY_VEHICLETYPE = "SELECT * FROM VEHICLE_FARE_CONFIG WHERE VEHICLE_TYPE = ?";
    public static final String GET_FASTTAG_ID = "SELECT FASTTAG_ID FROM FASTTAG_USER_CONFIG WHERE VEHICLE_NO = ?";
    public static final String UPDATE_VEHICLE_LOG = "INSERT INTO VEHICLE_LOG (FASTTAG_ID, PRICE, PAYMENT_MODE, LOG_TIME) VALUES (?, ?, ?, UNIX_TIMESTAMP(NOW()) * 1000)";

    public static final String FASTTAG_ID="FASTTAG_ID";
    public static final String VEHICLE_NO="VEHICLE_NO";
    public static final String OWNER_NAME="OWNER_NAME";
    public static final String VEHICLE_TYPE="VEHICLE_TYPE";
    public static final String LAST_ENTRY_TIME="LAST_ENTRY_TIME";
    public static final String FARE="FARE";
}
