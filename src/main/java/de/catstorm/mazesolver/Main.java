package de.catstorm.mazesolver;

import java.io.IOException;
import java.util.ArrayList;
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
        var top = returnStack.peek();
        if (top.id == -16 && returnStack.size() > 1)
        {
            //TODO: check if the stack contains every single node except for the home node exactly once
            if (StoredNodes.INSTANCE.checkForAllNodes(returnStack))
            {
                printRoute(new ArrayList<>(returnStack));
            }
            else
            {
                returnStack.pop();
                return;
            }
        }
        else
        {
            returnStack.push(new NodeWithNumerator(top.next().id));
        }
        loop();
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
}