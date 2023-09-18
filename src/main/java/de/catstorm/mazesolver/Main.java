package de.catstorm.mazesolver;

import java.io.IOException;

import java.util.Set;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        //Home id: -16
        var startTime = System.nanoTime();
        Set<Node> nodes = Parser.parse(args[0]);
        System.out.println(System.nanoTime() - startTime);
        for (var node : nodes)
        {
            System.out.println(node + "\n");
        }
    }
}