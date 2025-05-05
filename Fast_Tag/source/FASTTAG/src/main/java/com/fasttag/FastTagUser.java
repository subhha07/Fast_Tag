package com.fasttag;

public class FastTagUser
{
    private Long fastTagId;
    private String vehicleNo;
    private String ownerName;
    private String vehicleType;
    private Long lastEntryTime;

    public FastTagUser(Long fastTagId, String vehicleNo, String ownerName, String vehicleType, Long lastEntryTime)
    {
        this.fastTagId = fastTagId;
        this.vehicleNo = vehicleNo;
        this.ownerName = ownerName;
        this.vehicleType = vehicleType;
        this.lastEntryTime = lastEntryTime;
    }

    public FastTagUser() {

    }

    public void setFastTagId(Long fastTagId)
    {
        this.fastTagId = fastTagId;
    }

    public Long getFastTagId()
    {
        return  fastTagId;
    }

    public void setVehicleNumber(String vehicleNo)
    {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleNumber()
    {
        return vehicleNo;
    }

    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }

    public String getOwnerName()
    {
        return ownerName;
    }

    public void setVehicleType(String vehicleType)
    {
        this.vehicleType = vehicleType;
    }

    public String getVehicleType()
    {
        return vehicleType;
    }

    public void setLastEntryTime(Long lastEntryTime)
    {
        this.lastEntryTime = lastEntryTime;
    }

    public Long getLastEntryTime()
    {
        return lastEntryTime;
    }

}
