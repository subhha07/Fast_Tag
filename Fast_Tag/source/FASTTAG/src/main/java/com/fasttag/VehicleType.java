//$Id$
package com.fasttag;

public enum VehicleType
{
    FOUR_TO_SIX_AXLE("4 to 6 Axle", 1),
    SEVEN_OR_MORE_AXLE("7 or more Axle", 2),
    BUS_TRUCK("Bus/Truck", 3),
    CAR_JEEP_VAN("Car/Jeep/Van", 4),
    HCM_EME("HCM/EME", 5),
    LCV("LCV", 6),
    UPTO_THREE_AXLE("Upto 3 Axle Vehicle", 7);

    private final String type;
    private final int id;

    VehicleType(String type, int id)
    {
        this.type = type;
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public String getType()
    {
        return type;
    }

    public static VehicleType fromType(String type)
    {
        for (VehicleType v : values())
        {
            if (v.getType().equalsIgnoreCase(type))
            {
                return v;
            }
        }
        throw new IllegalArgumentException("Invalid vehicle type: " + type);
    }
}
