package de.catstorm.mazesolver

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

    fun addConnectionToNode(id: Byte, connection: Connection)
    {
        for (node in nodes)
        {
            if (node.id == id)
            {
                node.addConnection(connection);
            }
        }
    }
}