package de.catstorm.mazesolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

//For anyone reading this who isn't me: Have fun with the completely undocumented code
public class Main
{
    public static Stack<NodeWithNumerator> returnStack = new Stack<>();

    public static void main(String[] args) throws IOException, InvalidConnectionException
    {
        Parser.parse(args[0]);
        /*for (var node : StoredNodes.INSTANCE.getAllNodes())
        {
            System.out.println(node + "\n");
        }*/
        returnStack.push(new NodeWithNumerator((byte) -16));
        loop();
    }

    //TODO: better name
    private static void loop() throws InvalidConnectionException
    {
        var n = 0;
        while (true)
        {
            System.out.println(n);
            n++;
            var top = returnStack.peek();
            if (top.getNumerator() >= top.getMaxNumerator() && !returnStack.isEmpty()) returnStack.pop();
            else if (top.id == -16 && returnStack.size() > 1)
            {
                //TODO: remove crash at end & do a proper printing
                Set<Byte> nodesInStack = new HashSet<>();
                Set<Byte> allNodes = new HashSet<>();
                for (var node : returnStack)
                {
                    nodesInStack.add(node.id);
                }
                for (var node : StoredNodes.INSTANCE.getAllNodes())
                {
                    allNodes.add(node.id);
                }
                if (nodesInStack.containsAll(allNodes)) System.out.println("hi");

                if (StoredNodes.INSTANCE.checkForAllNodes(returnStack))
                {
                    printRoute(new ArrayList<>(returnStack));
                }
                returnStack.pop();

            }
            else if (StoredNodes.INSTANCE.getAllNodes().size() > returnStack.size() && top.peekNext().id != -16 && !checkReturnStack(top.peekNext().id) && top.getNumerator() <= top.getMaxNumerator())
            {
                returnStack.push(new NodeWithNumerator(top.next().id));
            }
            else if (top.peekNext().id == -16)
            {
                returnStack.push(new NodeWithNumerator(top.next().id));
            }
            else if (checkIsDeadEnd(top.id) && top.id != -16)
            {
                returnStack.pop();
            }
            else if (top.getNumerator() >= top.getMaxNumerator()) returnStack.pop();
            else
            {
                top.next();
            }
        }
    }

    @Deprecated //Instant legacy code
    private static void printRoute(ArrayList<NodeWithNumerator> nodes) throws InvalidConnectionException
    {
        var cost = 0;
        for (var i = 0; i < nodes.size(); i++)
        {
            System.out.println(nodes.get(i));
            if (i == 0) continue;
            cost += StoredNodes.INSTANCE.getNode(nodes.get(i).id)
                .getConnectionByConnectorId(nodes.get(i-1).id).cost; //Was I drunk when I wrote this?
        }
        System.out.println("cost: " + cost);
    }

    private static boolean checkReturnStack(byte id)
    {
        for (var node : returnStack) if (id == node.id) return true;
        return false;
    }

    private static boolean checkIsDeadEnd(byte id)
    {
        for (var connection : StoredNodes.INSTANCE.getNode(id).connections)
            if (!checkReturnStack(connection.getConnectorId())) return false;
        return true;
    }

    private static void omega()
    {
        System.exit(0);
    }
}