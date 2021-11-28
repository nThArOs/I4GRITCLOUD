import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.InputMismatchException;

public class Principale {
    public static void main(String[] args)throws InputMismatchException {
    	java.util.Scanner entree =   new java.util.Scanner(System.in);
        Socket monSocket;
        int ctr = 0;
        try {
            monSocket = new Socket ("127.0.0.1", 4999);
            IOCommandes commandesSRV = new IOCommandes(monSocket); //Intanciation de IOCommande par commandes1
            String text = ""; //Lecture du texte saisis par le clavier (entrée).
            //commandesSRV.socketWrite(text);//AppelleJ de la fonction prrintln (Affichage écran)
           
          //g  text = commandesSRV.readline();//Echo
            
            System.out.println("Synchronisation:\n");

	        File dir = new File("D:/dossierclient1");

	        File[] children = dir.listFiles();

	        for (File file : children) {
	            System.out.println(file.getAbsolutePath());
	            String sync = file.getAbsolutePath();   
	            commandesSRV.socketWrite("dossier client"+sync);  
	        }
	      
	    	
           
            while(!text.equalsIgnoreCase("quit")){ //attente de la Commande de fin d'execution "quit"
          //  commandesSRV.socketWrite(text);
            	ctr=0;
            	text = commandesSRV.socketRead();
                String messTest = text.substring(0,14);
                String dossServ = text.substring(34);
  
               if(messTest.equals("dossier server")){            	   
            	   System.out.println(text);
            	   for (File file : children) {
       	              String sync = file.getAbsolutePath(); 
       	              String str = sync.substring(18);   	   
       	              System.out.print("    ["+dossServ+"]      ");
       	              System.out.println("    ["+str+"]      ");
       	            if(dossServ.equals(str)) {
       	            	System.out.println("!!!!!!!!!!!"+text+"EST BIEN DANS LE DOSSIER CLIENT ET SERVER");
       	            	ctr++;
       	            }
       	        }
            	   if(ctr==0) {
                	   System.out.println("Pas de correspondance du fichier | "+dossServ+" | sur le client");
                	   System.out.println("Création du dossier"+dossServ+"Sur le client");
                	   File fichier = new File("D:/dossierclient1/"+dossServ);
                	   boolean created = fichier.mkdir();
               		   System.out.println("Directory created? " + created);
               		   commandesSRV.socketWrite("le fichait"+dossServ+"Manquait sur le client ol l'a donc crée");  
                   }
               }
               if(!messTest.equals("dossier server")){ 
            	   System.out.println("Taper 1 = Ajouter dossier / 2 = Supprimer dossier");
            	   int cpt=0;
            	   while(cpt !=1 && cpt !=2){
            		   cpt = entree.nextInt();
            	   }
            	   if(cpt == 1) {
            		   System.out.println("Veuiller saisir le nom du dossier a creér");
            		   String newnamefile = "";
            		   newnamefile = entree.next();
            		   File crcfichier = new File("D:/dossierclient1/"+newnamefile);
                	   boolean created = crcfichier.mkdir();
               		   System.out.println("Directory created? " + created);
               		   System.out.println("Le dossier " + newnamefile+"à était ajouter sur le server");
               		   commandesSRV.socketWrite("|newfile|"+dossServ); 
            	   }
            	   if(cpt == 2) {
            		   System.out.println("Veuiller saisir le nom du dossier a supprimer");
            		   String suprifile = "";
            		   suprifile = entree.next();
            		   File supfile = new File("D:/dossierclient1/"+suprifile);
            		   
            		   if(supfile.delete()){
            		       System.out.println(supfile.getName() + " est supprimé.");
            		      }else{
            		       System.out.println("Opération de suppression echouée");
            		      }
                	  
               		  
               		   System.out.println("Le dossier " + supfile+"vas être supprimer sur le server");
               		   commandesSRV.socketWrite("|supfile|"+dossServ); 
            	   }
            	   
            	   
            	   
            	   
            	   
					
               }	
            	
               System.out.println(" --------------------------- ");   
               
            }
    
       } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }  
}