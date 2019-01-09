/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solutions.UF6.NF2.OODB;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import java.util.List;


/**
 *
 * @author gmartinez
 */
public class VeureTotsElsClients {
    private static void veureClients1() { 
        ObjectContainer db = Db4oEmbedded.openFile("baseDeDades/BDOOClients.db4o");

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
            System.out.println("veureClients1(): FINAL");
            System.out.println("--------");
        } 
    }     
    
    
    private static void veureClients2() { 
        ObjectContainer db = Db4oEmbedded.openFile("baseDeDades/BDOOClients.db4o");

        try { 
            Predicate p = new Predicate<Client>() { 
                @Override 
                public boolean match(Client c) { 
                    return true; 
                } 
            }; 
            
            List<Client> result = db.query(p); 
            
            for (Client cli : result) { 
                System.out.println("Client: " + cli); 
            } 
        } finally { 
            db.close(); 
            System.out.println("veureClients2(): FINAL");
            System.out.println("--------");
        } 
    } 
    
    
    
    public static void main(String[] args) throws Exception { 
        veureClients1();
        veureClients2();
    }

}
