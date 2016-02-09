import java.net.*;
import java.io.*;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
public class Server {
    public static void main(String[] args) throws IOException {
    	System.out.println("PROGRAM MADE");
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }
        System.out.println("FIRST TRY CATCH MADE");
        Socket clientSocket = null;
        try {
        	 System.out.println("STUCK");
            clientSocket = serverSocket.accept();
            System.out.println("ACCEPTED");
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
         System.out.println("SECOND TRY CATCH MADE");

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                clientSocket.getInputStream()));
        String inputLine, outputLine;
        out.println("SERVER MADE");
        KnockKnockProtocol kkp = new KnockKnockProtocol();

        outputLine = kkp.processInput(null);
        out.println(outputLine);
        System.out.println(clientSocket.isBound());
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine + " inside while loop");
             outputLine = kkp.processInput(inputLine);
             if(inputLine.equals("t")){
                Runtime.getRuntime().exec("node repl.js");
                // Runtime.getRuntime().exec("takeoff()");
                System.out.println("EXECUTED");
             }
             out.println(outputLine);
             if (outputLine.equals("Bye."))
                break;
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
