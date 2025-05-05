package com.fasttag;

public enum PaymentType
{
    CASH(0),
    FASTTAG_WALLET(1),
    EXEMPTED_VEHICLE(2);

    private final int code;

    PaymentType(int code)
    {
        this.code = code;
    }

    public int getCode()
    {
        return code;
    }
    
    public static int fromType(String name)
    {
        for(PaymentType mode : PaymentType.values())
        {
            if(mode.name().equalsIgnoreCase(name))
            {
                return mode.getCode();
            }
        }
        throw new IllegalArgumentException("Invalid PaymentType name: " + name);
    }

}
