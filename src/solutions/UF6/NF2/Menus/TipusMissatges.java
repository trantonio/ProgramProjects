/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solutions.UF6.NF2.Menus;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Predicate;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gines
 */
public class TipusMissatges {
    
    //1. Carregar en memòria el fitxer de TipusMissatges
    LinkedList menu1() throws IOException {
        File fitxer = new File("arxiusPerCarregar/TipusMissatges.txt");     // Adreçament relatiu.
        FileReader fr = null;                     // Entrada.
        BufferedReader br;                        // Buffer.

        LinkedList<TipusMissatges_Dades> tipusMissatgesLLista = new LinkedList<>();

        String liniaLLegida;
        String comanda[];
        int id = 0;
        String nom = "";
        String[] dataTmp;
        int dadaTmp;
        Calendar dataCreacio = Calendar.getInstance();      
        Calendar dataCreacioTmp = Calendar.getInstance();
        Calendar dataAnulacio = Calendar.getInstance();
        Calendar dataAnulacioTmp = Calendar.getInstance();
        boolean actiu = true;
            

        try {  
            fr = new FileReader (fitxer);          // Inicialitza entrada del fitxer
            br = new BufferedReader(fr);           // Inicialitza buffer amb l’entrada
            
            while((liniaLLegida = br.readLine())!= null) {
                comanda = liniaLLegida.split("=");
                
                //System.out.print("comanda.length = " + comanda.length +  "; comandes[0] = " + comanda[0]);
                //if (comanda.length == 2) {
                //    System.out.println("; comandes[1] = " + comanda[1].length());
                //}

                switch (comanda[0]) {
                    case "id":
                        id = Integer.parseInt(comanda[1]);
                        break;
                    case "nom":
                        nom = comanda[1];
                        break;
                    case "actiu":
                        actiu = Boolean.parseBoolean(comanda[1]);
                        //System.out.println("comanda[1] = " + comanda[1] + "; actiu = " + actiu);
                        break;                        
                    case "dataCreacio":
                        dataTmp = comanda[1].split("-");
                        //System.out.println("dataTmp[0] = " + dataTmp[0] + "; dataTmp[1] = " + dataTmp[1] + "; dataTmp[2] = " + dataTmp[2]);
                        
                        dataCreacioTmp.set(Integer.parseInt(dataTmp[2]), (Integer.parseInt(dataTmp[1]))-1, Integer.parseInt(dataTmp[0]));
                        dataCreacio = (Calendar) dataCreacioTmp.clone();
                        //System.out.println("dataCreacio = " + dataCreacio.get(Calendar.DAY_OF_MONTH) + "-" + (dataCreacio.get(Calendar.MONTH) + 1)  + "-" + dataCreacio.get(Calendar.YEAR));
                        break;
                    case "dataAnulacio":
                        actiu = true;
                        if (comanda.length == 2) {
                            dataTmp = comanda[1].split("-");
    	                    dataAnulacioTmp.set(Integer.parseInt(dataTmp[2]), (Integer.parseInt(dataTmp[1]))-1, Integer.parseInt(dataTmp[0]));
                            if (dataAnulacioTmp.before(Calendar.getInstance())) {
                                actiu = false;
                            }
                        } else {
                            dataAnulacioTmp.set(1900, 0, 1);
                        }
                        
                        //Si volem guardar una variable Calendar en la LinkedList i que no ens machaqui els valors dels altres 
                        //Calendars que ja tenim ficats, hem de clonar el Calendar i ficar el clon.
                        //Si comenteu la linia "dataAnulacio = (Calendar) dataAnulacioTmp.clone();" i feu
                        //"tipusMissatgesLLista.add(new TipusMissatges_Dades(id, nom, actiu, dataCreacio, dataAnulacioTmp));"
                        //veureu que la última dataAnulacioTmp machaca el valor de totes les anteriors.
                        
                        dataAnulacio = (Calendar) dataAnulacioTmp.clone();
                        //System.out.println("dataAnulacio = " + dataAnulacio.get(Calendar.DAY_OF_MONTH) + "-" + (dataAnulacio.get(Calendar.MONTH) + 1)  + "-" + dataAnulacio.get(Calendar.YEAR));
                        tipusMissatgesLLista.add(new TipusMissatges_Dades(id, nom, actiu, dataCreacio, dataAnulacio));
                        
                        break;
                    default:
                        System.out.println("COMANDA EN FITXER SUBTIPUS NO RECONEGUDA");                    
                }
            }
            
            /*PER A MOSTRAR LA LLISTA A VEURE SI HEM CARREGAT BE LES DADES.
            System.out.println();
            int i = 0;
            Iterator<TipusMissatges_Dades> iterador = tipusMissatgesLLista.iterator();
            while (iterador.hasNext()) {
                System.out.println("tipusMissatgesLLista[" + i + "]:" + iterador.next());   
                i++;
            }
            */

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TipusMissatges.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TipusMissatges.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (fr != null) fr.close();
            System.out.println("menu1(): FINAL");
        }
        
        return tipusMissatgesLLista;
    }
    
    
    //2. Carregar en la BD els tipus de missatges carregats en memòria.
    void menu2(LinkedList<TipusMissatges_Dades> tipusMissatgesLLista){
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().objectClass(Calendar.class).callConstructor(true);
        ObjectContainer db = Db4oEmbedded.openFile(config, "baseDeDades/Leonov.db4o");  
        //S'ha de fer una configuració especial perquè db4o pugui treballar amb Calendar.
        
        try {
            for (TipusMissatges_Dades dadaTmp: tipusMissatgesLLista) {
                //Comprobem si a la BD hi ha algun tipus de missatge com el que volem insertar.
                
                int tipusMissatgeId = dadaTmp.getId();
                
                Predicate p = new Predicate<TipusMissatges_Dades>() { 
                    @Override 
                    public boolean match(TipusMissatges_Dades c) { 
                        return c.getId() == tipusMissatgeId;
                    } 
                }; 
            
                ObjectSet<TipusMissatges_Dades> result = db.query(p);       //En result tenim els objectes que s'han trobat en la BD que compleixen la condició de filtratge (la query).
                
                if (!result.isEmpty()) {
                    //Hem trobat en la BD un tipus de missatge amb el mateix ID. L'anem a updatear.
                    
                    TipusMissatges_Dades tipusMissatgeTmp = result.next();  //tipusMissatgeTmp apunta al mateix objecte que el trobat en la BD.
                    System.out.println("menu2(): UPDATE, tipusMissatgeId = " + tipusMissatgeId + ", tipusMissatgeTmp.nom = " + tipusMissatgeTmp.getNom());
                    tipusMissatgeTmp.setNom(dadaTmp.getNom());
                    tipusMissatgeTmp.setActiu(dadaTmp.isActiu());
                    tipusMissatgeTmp.setDataCreacio(dadaTmp.getDataCreacio());
                    tipusMissatgeTmp.setDataAnulacio(dadaTmp.getDataAnulacio());
                    
                    db.store(tipusMissatgeTmp);
                    
                } else{
                    //No hem trobat en la BD un tipus de missatge amb el mateix ID. L'insertem.
                    db.store(dadaTmp);
                    System.out.println("menu2(): INSERT");
                }
            }
        } finally {
            db.close();     //Fem el bloc try-finally per asegurar-nos el tancament de la DB.
            System.out.println("menu2(): FINAL");
        }
    }
    
    
    //3.LListar els tipus de missatges.
    void menu3() {
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().objectClass(Calendar.class).callConstructor(true);
        ObjectContainer db = Db4oEmbedded.openFile(config, "baseDeDades/Leonov.db4o"); 

        try { 
            Predicate p = new Predicate<TipusMissatges_Dades>() { 
                @Override 
                public boolean match(TipusMissatges_Dades c) { 
                    return true; 
                } 
            }; 
            
            List<TipusMissatges_Dades> result = db.query(p); 
            
            for (TipusMissatges_Dades dadaTmp : result) { 
                System.out.println(dadaTmp); 
            } 
            
            /*UNA ALTRA FORMA:
            ObjectSet<TipusMissatges_Dades> result = db.query(p); 
            
            Iterator<TipusMissatges_Dades> iterador = result.iterator();
            while (iterador.hasNext()) {
                System.out.println("Tipus de missatge:" + iterador.next());   
            }*/
            
        } finally { 
            db.close(); 
            System.out.println("menu3(): FINAL");
        }         
    }
    
    
        
    
}
