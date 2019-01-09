/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gines
 */
package solutions.UF6.NF2.OODB;
import com.db4o.*; 
import com.db4o.query.Predicate; 
import java.util.Scanner;


public class CercaParametritzada {
    private int valor = 0;
    
    public void setValor(int v) { 
        valor = v; 
    }

    public int getValor() {
        return valor;
    }
    

    public void cercaClients() throws Exception { 
        ObjectContainer db = Db4oEmbedded.openFile("baseDeDades/BDOOClients.db4o"); 
        try { 
            Predicate p = new Predicate<Client>() { 
                @Override 
                public boolean match(Client c) { 
                    return valor <= c.getNreComandes(); 
                } 
            }; 
            
            ObjectSet<Client> result = db.query(p); 
            
            while (result.hasNext()) { 
                Client cli = result.next(); 
                System.out.println("Client amb nº comandes >= " + getValor() + ":"); 
                System.out.println(cli); 
            } 
        } finally { 
            db.close(); 
        } 
    }

    public void cercaEncarrecs() { 
        ObjectContainer db = Db4oEmbedded.openFile("baseDeDades/BDOOClients.db4o"); 
        try { 
            Predicate p = new Predicate<Encarrec>() { 
                @Override 
                public boolean match(Encarrec c) { 
                    return valor <= c.getQuantitat(); 
                } 
            }; 
            
            ObjectSet<Encarrec> result = db.query(p); 
            
            while (result.hasNext()) { 
                Encarrec e = result.next(); 
                System.out.println("Encarrecs amb nº de productes demanats >= " + getValor() + ":"); 
                System.out.println(e); 
            } 
        } finally { 
            db.close(); 
        } 
    }
    
    private static void cercaEncarrecs2(final int numero, final String producte) { 
        ObjectContainer db = Db4oEmbedded.openFile("baseDeDades/BDOOClients.db4o"); 
        try { 
            Predicate p = new Predicate<Encarrec>() { 
                @Override 
                public boolean match(Encarrec c) { 
                    return (numero <= c.getQuantitat()) && (c.getNom().equalsIgnoreCase(producte)); 
                } 
            }; 
            
            ObjectSet<Encarrec> result = db.query(p); 
            
            while (result.hasNext()) { 
                Encarrec e = result.next(); 
                System.out.println("Encarrecs2 amb nº de productes demanats >= " + numero + " i nom producte = " + producte + ":");                 
                System.out.println(e); 
            } 
        } finally { 
            db.close(); 
        } 
    }

    
    public static void main(String[] args) throws Exception { 
        int numMinCerca = 0;
        String producteNom;
        
        CercaParametritzada cp = new CercaParametritzada(); 
        Scanner in = new Scanner(System.in); 
        System.out.print("Quin és el valor mínim a cercar? "); 
        cp.setValor(in.nextInt()); 
        cp.cercaClients();
        cp.cercaEncarrecs(); 
        
        System.out.println("--------"); 
        Scanner in2 = new Scanner(System.in); 
        System.out.print("Quin és el valor mínim a cercar i el nom del producte? "); 
        numMinCerca = in2.nextInt(); 
        producteNom = in2.next(); 
        cercaEncarrecs2(numMinCerca, producteNom); 
        System.out.println("FINAL"); 
    }    
}
