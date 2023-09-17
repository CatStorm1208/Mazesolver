package de.catstorm.mazesolver;

import java.util.HashSet;
import java.util.Set;

public class Node
{
    byte id;
    Set<Connection> connections = new HashSet<>();

    public Node(byte id)
    {
        this.id = id;
    }

    //Not sure why this will ever be needed
    public void addConnection(byte connectTo, byte cost) throws InvalidConnectionException
    {
        for (Connection connection : connections)
        {
            if (connection.getConnectorId() == connectTo)
                throw new InvalidConnectionException("There are two nodes or two instances of the same node in a single set");
        }
        connections.add(new Connection(connectTo, cost));
    }

    public void addConnection(Connection connection)
    {
        connections.add(connection);
    }
}