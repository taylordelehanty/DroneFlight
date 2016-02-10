import java.net.*;
import java.io.*;

public class KnockKnockProtocol {
    public void clearFile(){
       try{
        PrintWriter writer = new PrintWriter("commands.js", "UTF-8");
        writer.println("");
        } 
         catch(FileNotFoundException e){
        System.out.println(e);
       }
     catch(UnsupportedEncodingException e){}

    }
    public void processInput(String theInput) {
        try{
             PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("commands.js", true)));
             System.out.println(theInput);
            out.println(theInput);
            out.close();
        }
       catch(FileNotFoundException e){
        System.out.println(e);
       }
       catch(UnsupportedEncodingException e){}
       catch(IOException e){}
        
    }
}
