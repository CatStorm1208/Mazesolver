package de.catstorm.mazesolver;

import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Stack;

//For anyone reading this who isn't me: Have fun with the completely undocumented code
public class Main
{
    public static Stack<NodeWithNumerator> returnStack = new Stack<>();

    public static void main(String[] args) throws IOException
    {
        Parser.parse(args[0]);
        returnStack.push(new NodeWithNumerator((byte) -16));
        loop();
    }

    //TODO: better name
    private static void loop()
    {
        var top = returnStack.peek();
        if(top.getMaxNumerator() < top.getNumerator())
        {
            if (returnStack.size())
        }
        else if (top.id == -16)
        {
            //TODO: return to home
        }
        else if (top.getMaxNumerator() >= top.getNumerator())
        {
            returnStack.push(new NodeWithNumerator(top.next().id));
            loop();
        }
    }
}