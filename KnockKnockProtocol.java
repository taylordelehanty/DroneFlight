import java.net.*;
import java.io.*;

public class KnockKnockProtocol {
    public void processInput(String theInput) {
        PrintWriter writer = new PrintWriter("commands.js", "UTF-8");
        writer.print(theInput);
        writer.close();
        
    }
}
