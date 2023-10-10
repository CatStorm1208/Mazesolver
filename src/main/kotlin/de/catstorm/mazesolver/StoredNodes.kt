package de.catstorm.mazesolver

//Kotlin in order to maximize pain
object StoredNodes
{
    private var nodes: Set<Node> = HashSet();

    fun addNode(node: Node)
    {
        nodes = nodes.plus(node);
    }

    fun getNode(id: Byte): Node
    {
        for (node in nodes)
        {
            if (node.id == id)
            {
                return node;
            }
        }
        throw InvalidNodeException("The node with id $id does not exist");
    }

    fun getAllNodes(): Set<Node>
    {
        return nodes;
    }

    fun addConnectionToNode(id: Byte, connection: Connection)
    {
        for (node in nodes)
        {
            if (node.id == id)
            {
                node.addConnection(connection);
                return;
            }
        }
        throw InvalidNodeException("The node with id $id does not exist");
    }

    fun getSize(): Int
    {
        return nodes.size;
    }
}