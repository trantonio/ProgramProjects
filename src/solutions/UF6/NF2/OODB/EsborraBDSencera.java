/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solutions.UF6.NF2.OODB;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Predicate;
import java.util.List;
/**
 *
 * @author gmartinez
 */
public class EsborraBDSencera {
    private static void visualitzaDadesClients() {
        String valor;
        
        ObjectContainer db = Db4oEmbedded.openFile("baseDeDades/BDOOClients.db4o"); 
        System.out.println("visualitzaDadesClients(): INICI"); 
        
        try { 
            Predicate p = new Predicate<Client>() { 
                @Override 
                public boolean match(Client c) { 
                    return true; 
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
    
    
    private static void esborraTotaLaBD() {
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().objectClass(Client.class).cascadeOnDelete(true);
        ObjectContainer db = Db4oEmbedded.openFile(config, "baseDeDades/BDOOClients.db4o");        
        System.out.println("esborraTotaLaBD(): INICI");

        // Esborra tota la BD
        List result = db.queryByExample(new Object());
        /*
        while(result.hasNext()) {
            db.delete(result.next());
        }
        */
        for (Object cli : result) { 
            System.out.println("Esborrant: " + cli); 
            db.delete(cli);
        } 

        db.close();      
        System.out.println("esborraTotaLaBD(): FINAL");
        System.out.println("--------");
    }
    
    
    
    public static void main(String[] args) throws Exception { 
        visualitzaDadesClients();
        esborraTotaLaBD(); 
        visualitzaDadesClients();

    } 
}
