package de.catstorm.mazesolver

import java.util.Stack

//Kotlin in order to maximize pain
//But seriously, I had to use Kotlin
//because I needed this specific type of object
//which is only available in Kotlin
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

    fun checkForAllNodes(returnStack: Stack<NodeWithNumerator>): Boolean
    {
        return try
        {
            var previous: Set<Byte> = HashSet();
            returnStack.forEach()
            {
                previous = previous.plus(it.id);
                getNode(it.id);
                val store = it.id;
                previous.forEach()
                { it1 ->
                    if (store == it1)
                    {
                        return false; //I give up
                    }
                }
            }
            true;
        }
        catch (e: InvalidNodeException)
        {
            false;
        }
    }
}