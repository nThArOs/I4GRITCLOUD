import java.io.File;
import java.net.Socket;

public class MyThreadCC implements Runnable {
    private Socket socketCC; 
    public MyThreadCC (Socket socketCC){
        this.socketCC = socketCC;
    }

    public void run() {
        IOCommandes commandesSRV = new IOCommandes(socketCC);
        String messageThread; 
        System.out.println("Bonjours je suis le thead \n");
        
        System.out.println("File[] listFiles():\n");
        
        File dir = new File("D:/dossierserver");

        File[] children = dir.listFiles();

        for (File file : children) {
        	
        
           
            System.out.println(file.getAbsolutePath());
            String sync = file.getAbsolutePath();    
            
            commandesSRV.socketWrite("dossier server //"+sync);
           // System.out.println("message >"+commandesSRV.toString());
            
           // (!messageThread.equals("quit"));         
        }
   
        
        
        
        
        do{        	
            messageThread = commandesSRV.socketRead();
          //commandesSRV.socketWrite("echo > " + messageThread);
            commandesSRV.socketWrite("Message client Thr >" + messageThread);
            System.out.println("message >"+messageThread);
            
            String supfile = messageThread.substring(0,9);   	   
	        String newfile = messageThread.substring(0,9);
	        String ctnfile =messageThread.substring(27);
	       
	              System.out.print("    ["+supfile+"]      ");
	              System.out.println("    ["+newfile+"]      ");
	            if(supfile.equals("|supfile|")) { 
	            	 System.out.print("DOSSIER    ["+messageThread+"]SUPPRIMER      ");
	            }else if(newfile.equals("|newfile|")) { 
	            	 System.out.println(ctnfile);
	            	 System.out.println(ctnfile);
	            	 System.out.println(ctnfile);
	            	 System.out.println(ctnfile);
	            	 System.out.println(ctnfile);
	            	 System.out.println(ctnfile);
		           	   
	            	System.out.println(" DOSSIER   ["+messageThread+"]  AJOUTER    ");
	            	File crcfichier = new File("D:/dossierserver/"+ctnfile);
             	   boolean created = crcfichier.mkdir();
            		   System.out.println("Directory created? " + created);
            		   System.out.println("Le dossier " + ctnfile+"à était ajouter sur le server");
	            }
	            
        }while(!messageThread.equals("quit"));
        System.out.println("La boucle");
        commandesSRV.socketWrite("echo >" + messageThread);
        System.out.println("Client deconnecté....");
        System.out.println(messageThread);
    }
}