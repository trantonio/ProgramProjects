/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solutions.UF6.NF2.OODB;
import com.db4o.*;


/**
 *
 * @author gmartinez
 */
public class CreaBDIInicialitzaDades {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ObjectContainer db = Db4oEmbedded.openFile("baseDeDades/BDOOClients.db4o");
        
        try {
            Client[] clients = {
                new Client("Client1", "Adreça1", "e−mail1@domini.com", "+34911112211"),
                new Client("Client2", "Adreça2", "e−mail2@domini.com", "+34922223322"),
                new Client("Client3", "Adreça3", "e−mail3@domini.com", "+34933112233"),
                new Client("Client4", "Adreça4", "e−mail4@domini.com", "+34944112244")
            };
            
            clients[0].addComanda(new Encarrec("patates",27));
            clients[0].addComanda(new Encarrec("ratoli",100));
            clients[2].addComanda(new Encarrec("Impressora",1));
            clients[2].addComanda(new Encarrec("Toner Impressora",4));
            clients[2].addComanda(new Encarrec("Paquest A4", 20));
            
            for(int i = 0; i < clients.length; i++) {
                db.store(clients[i]);   //S'emmagatzema clients[i] i automàticament totes les seves referències (els encarrecs associats).
            }
        } finally {
            db.close();
        }
    }
    
}
