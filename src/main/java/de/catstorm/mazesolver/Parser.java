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

    //TODO: parse node connections
    public static void parseNodeHexConnections(String hex)
    {

    }

    private static String fileToHex(String path) throws IOException
    {
        InputStream is = new FileInputStream(path);
        return Hex.encodeHexString(IOUtils.toByteArray(is));
    }
}