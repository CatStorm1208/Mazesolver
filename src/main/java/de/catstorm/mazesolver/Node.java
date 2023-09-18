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

    public void addConnection(Connection connection)
    {
        connections.add(connection);
    }

    public String toString()
    {
        String result = id + "";
        for (var connection : connections)
        {
            result += "\n" + connection.getConnectorId() + " " + connection.cost;
        }
        return result;
    }
}