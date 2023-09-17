package de.catstorm.mazesolver;

public class Connection
{
    byte connector;
    byte cost;
    
    public Connection(byte connectTo, byte cost)
    {
        connector = connectTo;
        this.cost = cost;
    }

    public byte getConnectorId()
    {
        return connector;
    }
}