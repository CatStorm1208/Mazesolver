package de.catstorm.mazesolver;

import java.util.ArrayList;

public class NumeratedNode {
    public final byte id;
    private int numerator = -1;
    public ArrayList<Byte> connectionIds;

    public NumeratedNode(byte id) {
        this.id = id;
        connectionIds = new ArrayList<>();
        for (Connection connection : StoredNodes.INSTANCE.getNode(id).connections) {
            connectionIds.add(connection.getConnectorId());
        }
    }

    public Node next() {
        if (numerator <= getMaxNumerator()) {
            numerator++;
            return StoredNodes.INSTANCE.getNode(connectionIds.get(numerator));
        }
        return null;
    }

    public Node peekNext() {
        if (numerator <= getMaxNumerator()) return StoredNodes.INSTANCE.getNode(connectionIds.get(numerator + 1));
        else return null;
    }

    public int getMaxNumerator() {
        return connectionIds.size() - 1; //Conversion from true size to index size
    }

    public int getNumerator() {
        return numerator;
    }
}