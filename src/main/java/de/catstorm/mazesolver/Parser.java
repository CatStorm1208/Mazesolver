package de.catstorm.mazesolver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;

@SuppressWarnings({"CollectionAddAllCanBeReplacedWithConstructor", "MismatchedQueryAndUpdateOfCollection"})
public class Parser
{
    public static Set<String> parseHexFile(String path) throws IOException
    {
        Set<String> result = new HashSet<>();
        result.addAll(Arrays.asList(fileToHex(path).split("0000")));
        return result;
    }

    public static Set<String> parseHexFile(File file) throws IOException
    {
        String path = file.getPath();
        Set<String> result = new HashSet<>();
        result.addAll(Arrays.asList(fileToHex(path).split("0000")));
        return result;
    }

    public static void parseNodeHexId(String hex)
    {
        var id = (byte) Integer.parseInt(hex.substring(0, 2), 16);
        StoredNodes.INSTANCE.addNode(new Node(id));
    }

    public static void parseNodeHexConnections(String hex)
    {
        var id = (byte) Integer.parseInt(hex.substring(0, 2), 16);
        var connectionString = hex.substring(2);
        Set<String> connections = new HashSet<>();
        for (var n = 0; n < connectionString.length(); n+=4)
        {
            connections.add(connectionString.substring(n, n+4));
        }
        for (var connection : connections)
        {
            StoredNodes.INSTANCE.addConnectionToNode(id, new Connection
                (StoredNodes.INSTANCE.getNode((byte) Integer.parseInt(connection.substring(0, 2), 16)),
                (byte) Integer.parseInt(connection.substring(2), 16)));
        }
    }

    public static Set<Node> parse(String path) throws IOException
    {
        Set<String> hexes = Parser.parseHexFile(path);
        for (var hex : hexes)
        {
            Parser.parseNodeHexId(hex);
        }
        for (var hex : hexes)
        {
            Parser.parseNodeHexConnections(hex);
        }
        return StoredNodes.INSTANCE.getAllNodes(); //Idk why we would ever need this
    }

    private static String fileToHex(String path) throws IOException
    {
        InputStream is = new FileInputStream(path);
        return Hex.encodeHexString(IOUtils.toByteArray(is));
    }
}