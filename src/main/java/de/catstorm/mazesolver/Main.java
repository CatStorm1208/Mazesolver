package de.catstorm.mazesolver;

import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Stack;

//For anyone reading this who isn't me: Have fun with the completely undocumented code
//TODO: make program run without debug mode
//TODO: make final jar build work
@SuppressWarnings("InfiniteLoopStatement")
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
        try
        {
            while (true)
            {
                var top = returnStack.peek();
                if (top.getNumerator() >= top.getMaxNumerator() && !returnStack.isEmpty()) returnStack.pop();
                else if (top.id == -16 && returnStack.size() > 1)
                {
                    if (returnStack.size() == StoredNodes.INSTANCE.getSize() + 1) printRoute();
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
        catch (EmptyStackException emptyStackException)
        {
            omega();
        }
    }

    private static void printRoute() throws InvalidConnectionException
    {
        for (var node : returnStack) System.out.println(node.id);

        var cost = 0;
        for (var i = 0; i < returnStack.size(); i++)
        {
            if (i != 0)
            {
                cost += StoredNodes.INSTANCE.getNode(returnStack.get(i).id).getConnectionByConnectorId
                    (returnStack.get(i-1).id).cost;
            }
        }
        System.out.println("Cost: " + cost + "B\n");
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
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