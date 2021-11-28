import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import org.w3c.dom.ls.LSException;

public class IOCommandes{
    private BufferedReader commandRead; //Lire écran
    private PrintStream commandWrite;// Ecrire écran
    private BufferedReader socketRead;
    private PrintStream socketWrite;

    public IOCommandes(Socket monSocket){ //Initialisaton des attribut : 
        commandRead = new BufferedReader(new InputStreamReader(System.in));
        commandWrite = System.out;

        try {
            socketRead = new BufferedReader(new InputStreamReader(monSocket.getInputStream()));
            socketWrite=new PrintStream(monSocket.getOutputStream(), true);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    //    System.out.println("Saisir mot : ");
    }
    
    //fonction permatant l'ecriture écran
    public void println(String montext){ 
        commandWrite.println(montext);
    }

    ////fonction permatant la lecture
    public String readline(){
        String text = null; 
        try{
            text = commandRead.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
        return text;
    }

    public void socketWrite(String monTextSoc)throws IOException{
        //commandWrite.println("echo du mot "+monTextSoc);
        //commandWrite.println("lla"+socketRead.readLine());
        socketWrite.println(monTextSoc);

    } 
    
    public int checkid(int verif)throws IllegalArgumentException{
    	if(verif == 1 || verif == 2) {
		return verif;
    	}else {
    		return verif;
    	}
    }
    
    public String toString(){
        return "The name of the pet is " + this.commandWrite;
    }
   
    public String socketRead(){
       String textsoc = null; 
       try{
           textsoc = socketRead.readLine();
       }catch(IOException e){
           e.printStackTrace();
       }
       return textsoc;

   }
}
