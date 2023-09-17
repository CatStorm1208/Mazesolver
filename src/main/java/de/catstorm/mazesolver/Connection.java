package de.catstorm.mazesolver;

public class Connection
{
    Node connector;
    byte cost;
    
    public Connection(Node connectTo, byte cost)
    {
        connector = connectTo;
        this.cost = cost;
    }

    public byte getConnectorId()
    {
        return connector.id;
    }
}