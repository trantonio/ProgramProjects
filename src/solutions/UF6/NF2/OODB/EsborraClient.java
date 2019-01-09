/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solutions.UF6.NF2.OODB;
import com.db4o.*;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Predicate;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author gines
 */
public class EsborraClient {
    private static void visualitzaDadesClients() {
        String valor;
        
        ObjectContainer db = Db4oEmbedded.openFile("baseDeDades/BDOOClients.db4o"); 
        System.out.println("visualitzaDadesClients(): INICI"); 
        
        try { 
            Predicate p = new Predicate<Client>() { 
                @Override 
                public boolean match(Client c) { 
                    return c.getNom().length() > 0; 
                } 
            }; 
            
            ObjectSet<Client> result = db.query(p); 
            
            while (result.hasNext()) { 
                Client cli = result.next(); 
                System.out.println("Client: " + cli); 
            } 
        } finally { 
            db.close(); 
            System.out.println("visualitzaDadesClients(): FINAL");
            System.out.println("--------");
        } 
    }
    
    
    private static void esborraClientAmbCercaObjectesPerExemple(){
        ObjectContainer db = Db4oEmbedded.openFile("baseDeDades/BDOOClients.db4o");

        Scanner in = new Scanner(System.in);
        System.out.print("Quin és nom del client? ");
        String nom = in.nextLine();

        //Cercar clients a la BDOO i obtenir−los a memòria com objectes del programa
        Client qbe = new Client(nom, null, null, null);
        ObjectSet<Client> clients = db.queryByExample(qbe);

        if (clients.size() != 1) {
            System.out.println("No es pot modificar aquest nom.");
        } else {
            Client c = clients.next();
            List<Encarrec> li = c.getComandes();
            Iterator<Encarrec> it = li.iterator();
            //Anem esborrant tots els encàrrecs, un per un
            while (it.hasNext()) {
                Encarrec e = it.next();
                db.delete(e);
            }
            //Ara, ja es pot esborrar el client
            db.delete(c);
        }
        db.close();
        System.out.println("esborraClientAmbCercaObjectesPerExemple(): FINAL");
        System.out.println("--------");
    }
    
    
    private static void esborraClientAmbCercaObjectesPerCercaNativa(){
        ObjectContainer db = Db4oEmbedded.openFile("baseDeDades/BDOOClients.db4o");

        Scanner in = new Scanner(System.in);
        System.out.print("Quin és nom del client? ");
        final String nom = in.nextLine();

        //Cercar clients a la BDOO i obtenir−los a memòria com objectes del programa
        try { 
            Predicate p = new Predicate<Client>() { 
                @Override 
                public boolean match(Client c) { 
                    return c.getNom().equalsIgnoreCase(nom); 
                } 
            }; 
            
            ObjectSet<Client> clients = db.query(p); 
            
            if (clients.size() != 1) {
                System.out.println("No es pot modificar aquest nom. N'hi ha més d'1 o 0.");
            } else {
                Client c = clients.next();
                
                List<Encarrec> li = c.getComandes();
                Iterator<Encarrec> it = li.iterator();
                //Anem esborrant tots els encàrrecs, un per un
                while (it.hasNext()) {
                    Encarrec e = it.next();
                    db.delete(e);
                }
                
                //Ara, ja es pot esborrar el client
                db.delete(c);
            }
        } finally { 
            db.close(); 
            System.out.println("esborraClientAmbCercaObjectesPerCercaNativa(): FINAL");
            System.out.println("--------");
        } 
    }
    
    
    private static void visualitzaElsEncarrecs(ObjectContainer db) {
        Predicate p2 = new Predicate<Encarrec>() { 
            @Override 
            public boolean match(Encarrec c) { 
                return true; 
            } 
        }; 

        ObjectSet<Encarrec> encarrecs = db.query(p2); 

        while (encarrecs.hasNext()) { 
            Encarrec encarrec = encarrecs.next(); 
            System.out.println("Encarrec: " + encarrec); 
        } 
    }
    
    
    private static void esborraClientAmbCercaObjectesPerCercaNativaAmbCascade(){
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().objectClass(Client.class).cascadeOnDelete(true);
        ObjectContainer db = Db4oEmbedded.openFile(config, "baseDeDades/BDOOClients.db4o");

        Scanner in = new Scanner(System.in);
        System.out.print("Quin és nom del client? ");
        final String nom = in.nextLine();

        System.out.println("visualitzaElsEncarrecs 1");
        visualitzaElsEncarrecs(db);
        
        //Cercar clients a la BDOO i obtenir−los a memòria com objectes del programa
        try { 
            Predicate p = new Predicate<Client>() { 
                @Override 
                public boolean match(Client c) { 
                    return c.getNom().equalsIgnoreCase(nom); 
                } 
            }; 
            
            ObjectSet<Client> clients = db.query(p); 
            
            if (clients.size() != 1) {
                System.out.println("No es pot modificar aquest nom. N'hi ha més d'1 o 0.");
            } else {
                Client c = clients.next();
                
                db.delete(c);
                //Busquem tots els encàrrecs a veure si l'esborrar en cascada ha esborrar els 
                //encàrrecs d'un client a l'esborrar el client.
                System.out.println("visualitzaElsEncarrecs 2");
                visualitzaElsEncarrecs(db);
            }
            

            
        } finally { 
            db.close(); 
            System.out.println("esborraClientAmbCercaObjectesPerCercaNativaAmbCascade(): FINAL");
            System.out.println("--------");
        } 
    }
    
    
    public static void main(String[] args) throws Exception {
        visualitzaDadesClients();
        //esborraClientAmbCercaObjectesPerExemple();
        //esborraClientAmbCercaObjectesPerCercaNativa();
        esborraClientAmbCercaObjectesPerCercaNativaAmbCascade();
        visualitzaDadesClients();
    }
    
}
