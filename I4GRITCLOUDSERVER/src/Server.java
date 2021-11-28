
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketPermission;

public class Server{
    public static void main(String arg[]){
        ServerSocket listenMode = null;
        String message; 
        Socket scocketOfClient = null;

        try{
            listenMode = new ServerSocket(4999);
             //Socket monSocket;
            //Socket monSocket = new Socket("127.0.0.1", 4999);
            

        }catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }

        try {
            System.out.println("Wait for client connection... ");

            while(true){
           
            scocketOfClient = listenMode.accept();
            
            System.out.println("Connexion successful");
            System.out.println(scocketOfClient.getInetAddress());
            
            IOCommandes commandesSRV = new IOCommandes(scocketOfClient);
            
            MyThreadCC myThread = new MyThreadCC(scocketOfClient);
            Thread ThredN = new Thread(myThread);
            ThredN.start();

          
            
          //  message = commandesSRV.socketRead();*/

       /*while(!message.equals("quit")){
                commandesSRV.socketWrite("echo server >>" + message);
                
                message = commandesSRV.socketRead();
                commandesSRV.println(message);
            
                              
        }*/
           //scocketOfClient.close();
         }
        }catch (IOException e) {
            System.out.println("Client disconect ...!");
            e.printStackTrace();
        }
        //System.out.println("Server stop");
    }
}