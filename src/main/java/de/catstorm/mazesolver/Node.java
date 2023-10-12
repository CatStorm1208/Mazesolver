package de.catstorm.mazesolver;

import java.util.HashSet;
import java.util.Set;

public class Node {
    byte id;
    Set<Connection> connections = new HashSet<>();

    public Node(byte id) {
        this.id = id;
    }

    public void addConnection(Connection connection) {
        connections.add(connection);
    }

    @Override
    public String toString() {
        String result = id + "";
        for (var connection : connections) {
            result += "\n" + connection.getConnectorId() + " " + connection.cost;
        }
        return result;
    }

    public Connection getConnectionByConnectorId(byte id) throws InvalidConnectionException {
        for (var connection : connections) {
            if (connection.getConnectorId() == id) return connection;
        }
        throw new InvalidConnectionException("The connection from " + this.id + " to " + id + " doesn't exist");
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object obj) {
        return ((Node) obj).id == this.id;
    }
}