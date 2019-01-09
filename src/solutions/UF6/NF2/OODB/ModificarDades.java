/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solutions.UF6.NF2.OODB;
import com.db4o.*;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Predicate;
import java.util.Scanner;

/**
 *
 * @author gines
 */
public class ModificarDades {
    private static void ModificaAElectronica() {
        ObjectContainer db = Db4oEmbedded.openFile("baseDeDades/BDOOClients.db4o");
        Scanner in = new Scanner(System.in);
        System.out.print("ModificaAElectronica(): Quin és nom del client? ");
        String nom = in.nextLine();

        //Cercar clients a la BDOO i obtenir−los a memòria com a objectes del programa
        //S’usa una cerca per exemple
        Client qbe = new Client(nom, null, null, null);
        ObjectSet<Client> clients = db.queryByExample(qbe);

        if (clients.size() != 1) {
            System.out.println("No es pot modificar aquest nom. N'hi ha més d'1 o 0.");
        } else {
            System.out.print("Quina és la nova adreça? ");
            String ad = in.nextLine();
            
            Client c = clients.next();
            c.setAElectronica(ad);
            db.store(c);
        }
        db.close();
        System.out.println("ModificaAElectronica(): FINAL");
        System.out.println("--------");
    }
    
    
    private static void AfegirEncarrec() {
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().objectClass(Client.class).cascadeOnUpdate(true);
        ObjectContainer db = Db4oEmbedded.openFile(config, "baseDeDades/BDOOClients.db4o");

        Scanner in = new Scanner(System.in);
        System.out.print("AfegirEncarrec(): Quin és nom del client? ");
        final String nom = in.nextLine();
        
        //Cercar clients a la BDOO i obtenir−los a memòria com a objectes del programa
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
                System.out.print("Quin és nom del producte? ");
                String prod = in.nextLine();
                System.out.print("Quants en vols encarregar? ");
                String txtQuan = in.nextLine();
                int quant = Integer.parseInt(txtQuan);

                Encarrec ne = new Encarrec(prod, quant);
                Client c = clients.next();
                c.addComanda(ne);
                db.store(c);
            }
        } finally { 
            db.close(); 
            System.out.println("AfegirEncarrec(): FINAL");
            System.out.println("--------");
        } 
    }
    
    
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
    
    
    
    
    public static void main(String[] args) throws Exception {
        visualitzaDadesClients();
        ModificaAElectronica();
        AfegirEncarrec();
        visualitzaDadesClients();
    }

}
