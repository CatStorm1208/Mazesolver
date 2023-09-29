package de.catstorm.mazesolver;

import java.io.IOException;
import java.util.Stack;

//For anyone reading this who isn't me: Have fun with the completely undocumented code
public class Main
{
    public static Stack<NodeWithNumerator> returnStack = new Stack<>();

    public static void main(String[] args) throws IOException
    {
        Parser.parse("C:/Users/anton/IdeaProjects/Mzesolver/src/main/resources/yes.hex");
        returnStack.push(new NodeWithNumerator((byte) -16));
        loop();
    }

    //TODO: better name
    private static void loop()
    {
        var top = returnStack.peek();
        while (top.getMaxNumerator() > top.getNumerator())
        {
            if (top.id == -16)
            {
                if (returnStack.size() == StoredNodes.INSTANCE.getAllNodes().size() + 1)
                {
                    System.out.println("Nice");
                }
            }
            else if (top.getMaxNumerator() <= top.getNumerator())
            {
                var next = top.next();
                for (var node : returnStack.stream().toList())
                {
                    if (next.id == node.id) return;
                }
                returnStack.push(new NodeWithNumerator(next.id));
                System.out.println("69");
                loop();
            }
        }
        returnStack.pop();
    }
}