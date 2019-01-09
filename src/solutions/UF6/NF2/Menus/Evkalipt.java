/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solutions.UF6.NF2.Menus;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author gines
 */
public class Evkalipt {
    public static void bloquejarPantalla() {
        Scanner in = new Scanner(System.in);
        System.out.print("Toca 'C' per a continuar ");
        while (in.hasNext()) {
            if ("C".equalsIgnoreCase(in.next())) break;
        }
    }
    
    
    void menuEvkalipt() throws IOException  {
        String opcio;
        Scanner sc = new Scanner(System.in);
        StringBuilder menu = new StringBuilder("");
        TipusMissatges objTipusMissatges = new TipusMissatges();
        LinkedList<TipusMissatges_Dades> tipusMissatgesLLista = new LinkedList<>();
        
        
        do {
            menu.delete(0, menu.length());
            
            menu.append(System.getProperty("line.separator"));
            menu.append("EVKALIPT");
            menu.append(System.getProperty("line.separator"));
            menu.append(System.getProperty("line.separator"));
            menu.append("0. Enviar un missatge");
            menu.append(System.getProperty("line.separator"));
            menu.append(System.getProperty("line.separator"));
            
            menu.append("1. Carregar en memòria el fitxer de TipusMissatges");
            menu.append(System.getProperty("line.separator"));
            menu.append("2. Carregar en la BD els tipus de missatges carregats en memòria");
            menu.append(System.getProperty("line.separator"));
            menu.append("3. LListar els tipus de missatges");
            menu.append(System.getProperty("line.separator"));
            menu.append("4. Afegir un nou tipus de missatges");
            menu.append(System.getProperty("line.separator"));
            menu.append("5. Modificar un tipus de missatges");
            menu.append(System.getProperty("line.separator"));
            menu.append(System.getProperty("line.separator"));
            
            menu.append("10. Carregar en memòria el fitxer Usuaris");
            menu.append(System.getProperty("line.separator"));
            menu.append("11. Carregar en la BD els usuaris carregats en memòria");
            menu.append(System.getProperty("line.separator"));
            menu.append("12. LListar els usuaris (només ID i nom)");
            menu.append(System.getProperty("line.separator"));
            menu.append("13. LListar els usuaris (tota la informació)");
            menu.append(System.getProperty("line.separator"));
            menu.append("14. LListar els contactes d'un usuari");
            menu.append(System.getProperty("line.separator"));
            menu.append("15. Afegir un usuari");
            menu.append(System.getProperty("line.separator"));
            menu.append("16. Modificar un usuari");
            menu.append(System.getProperty("line.separator"));
            menu.append("17. Copiar/sobreescriure els contactes d'un usuari en un altre usuari");
            menu.append(System.getProperty("line.separator"));
            menu.append("18. LListar els usuaris que tinguin entre els seus contactes un usuari concret");
            menu.append(System.getProperty("line.separator"));
            menu.append("19. Eliminar un usuari");
            menu.append(System.getProperty("line.separator"));
            menu.append(System.getProperty("line.separator"));
            
            menu.append("30. Veure tots els missatges enviats d'un usuari");
            menu.append(System.getProperty("line.separator"));
            menu.append("31. Veure tots els missatges destinats a un usuari");
            menu.append(System.getProperty("line.separator"));
            menu.append("32. Modificar un missatge");
            menu.append(System.getProperty("line.separator"));
            menu.append("33. Censurar un missatge");
            menu.append(System.getProperty("line.separator"));
            menu.append(System.getProperty("line.separator"));
            
            menu.append("40. Fer una copia de seguretat de la BD");
            menu.append(System.getProperty("line.separator"));
            menu.append("41. Esborrar tota la BD");
            menu.append(System.getProperty("line.separator"));
            menu.append(System.getProperty("line.separator"));
            
            menu.append("50. Tornar al menú pare (PNS-24 Puma)");
            menu.append(System.getProperty("line.separator"));
            
            
            System.out.print(MenuConstructorPantalla.constructorPantalla(menu));
            
            opcio = sc.next();
            
            switch (opcio) {
                case "1":
                    //System.out.println("opció 1");
                    tipusMissatgesLLista = objTipusMissatges.menu1();
                    bloquejarPantalla();
                    break;
                case "2":
                    objTipusMissatges.menu2(tipusMissatgesLLista);
                    bloquejarPantalla();
                    break;
                case "3":
                    objTipusMissatges.menu3();
                    bloquejarPantalla();
                    break;
                case "4":
                    
                    bloquejarPantalla();
                    break;
                case "5":
                    
                    bloquejarPantalla();
                    break;
                case "6":
                    
                    bloquejarPantalla();
                    break;
                case "7":
                    
                    bloquejarPantalla();
                    break;
                case "10":
                    
                    bloquejarPantalla();
                    break;                    
                case "50":
                    break; 
                default:
                    System.out.println("COMANDA NO RECONEGUDA");
            }   
        } while (!opcio.equals("50"));
    }
    
    

}
