/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solutions.UF5.NF3.ejerciciosII;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;

/**
 *
 * @author gmartinez
 */
public class exercici10 {

    /**
     * @param args the command line arguments
     */
    public static String convertirLongTimeEnCalendar(long time){
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.setTimeInMillis(time);
        return (cal.get(Calendar.DAY_OF_MONTH) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
                + cal.get(Calendar.YEAR) + " a les " + cal.get(Calendar.HOUR_OF_DAY) + "h "
                + cal.get(Calendar.MINUTE) + "' " + cal.get(Calendar.SECOND) + "'' " + cal.get(Calendar.MILLISECOND));

    }



    public static void bloquejarPantalla() {
        Scanner in = new Scanner(System.in);
        System.out.print("Toca 'C' per a continuar ");
        while (in.hasNext()) {
            if ("C".equalsIgnoreCase(in.next())) break;
        }
        System.out.println();
        System.out.println();
    }


    public static void main(String[] args) {
        //FileUtils.iterateFiles(fDesti, fileFilter, dirFilter); //Per a recorrer els files que hi ha dins d'un directori ja que retorna un iterator?.

        //1. Crea el directori "directoriExercici10" i dins el directori "origen" (forceMkdir).
        //2. Crea amb un bucle 5 fitxers anomenats "fitxer1.txt" - "fitxer5.txt" (createNewFile)
        //3. Afegeix una llista de linies de text en els fitxers (les linies de text seràn el path + nom dels fitxers) (listFiles)(writeLines)
        //4. Mou els 5 fitxers a dins del directori "origen" (moveFileToDirectory)
        //5. Pregunta si el directori conté algún fitxer amb nom "fitxer3.txt" (getFile)(directoryContains)
        //6. Esborra el fitxer "fitxer3.txt" i torna a executar l'apartat 5 (forceDelete)
        //7. Comproba si el fitxer "fitxer1.txt" és mes vell que "Fitxer5.txt" (isFileOlder)
        //8. Copia, machacant el contingut anterior, la linia "linia sobreescrita" en el fitxer "fitxer1.txt" (writeStringToFile)
        //9. Comproba si el fitxer "fitxer1.txt" és mes nou que "fitxer5.txt"
        //10. Fes un touch sobre el fitxer "fitxer5.txt" (touch)
        //11. Mou el directori "origen" al directori "desti" (moveDirectoryToDirectory) i si no existeix previament "desti" donçs que es crei automàticament
        //12. Calcula el tamany del directori "desti" (sizeOfDirectoryAsBigInteger)




        try {
            //APARTAT 1: Crea el directori "directoriExercici10" i dins el directori "origen" (forceMkdir).
            String path = "directoriExercici10/";
            File directoriArrel = new File(path);
            FileUtils.forceMkdir(directoriArrel);
            File directoriOrigen = new File(path + "origen");
            FileUtils.forceMkdir(directoriOrigen);


            //APARTAT 2: Crea amb un bucle 5 fitxers anomenats "fitxer1.txt" - "fitxer5.txt" (createNewFile).
            String nomFitxer;
            boolean fitxerCreat = false;
            ArrayList<String> totsNomsFitxers = new ArrayList<String>();
            for(int i = 1; i < 6; i++){
                nomFitxer = path + "fitxer" + i + ".txt";
                totsNomsFitxers.add(nomFitxer);
                File fitxerTmp = new File(nomFitxer);
                fitxerCreat = fitxerTmp.createNewFile();
                System.out.println("APARTAT 2: S'ha creat el fitxer " + nomFitxer + "? " + fitxerCreat);
            }
            System.out.println();


            //APARTAT 3: Afegeix unes linies de text en els fitxers (les linies de text seràn els noms
            //dels fitxers) (listFiles)(writeLines).
            String[] extensionsFitxers = {"txt", "java","xml"};
            List<File> llistaFitxers = (List<File>)FileUtils.listFiles(directoriArrel, extensionsFitxers, true);

            for (File fitxer: llistaFitxers){
                FileUtils.writeLines(fitxer, "UTF-8", totsNomsFitxers, null, true);
            }


            System.out.println("ARA ES HORA DE MIRAR SI S'HA AFEGIT EL CONTINGUT ALS FITXERS QUE ENCARA ES TROBEN EN " + path);
            bloquejarPantalla();


            //APARTAT 4: Mou els 5 fitxers a dins del directori "origen" (moveFileToDirectory).
            Iterator<File> it = llistaFitxers.iterator();
            while (it.hasNext()){
                FileUtils.moveFileToDirectory(it.next(), directoriOrigen, true);
            }
            System.out.println("APARTAT 4: S'HAN MOGUT ELS 5 FITXERS A " + directoriOrigen.getPath());
            System.out.println();
            
            /*ESBORRAR D'AQUESTA MANERA NO VA.
            for (File fitxer: llistaFitxers){
                FileUtils.moveFileToDirectory(fitxer, directoriOrigen, true);
            }*/


            //APARTAT 5: Pregunta si el directori conté algún fitxer amb nom "fitxer3.txt" 
            //(getFile)(directoryContains).
            File fitxer3txt = FileUtils.getFile(directoriOrigen, "fitxer3.txt");
            System.out.println("APARTAT 5: Està fitxer3.txt?: " + FileUtils.directoryContains(directoriOrigen, fitxer3txt));
            System.out.println();


            //APARTAT 6: Esborra el fitxer "fitxer3.txt" i torna a executar l'apartat 5 (forceDelete).
            FileUtils.forceDelete(fitxer3txt);
            System.out.println("APARTAT 6: Està fitxer3.txt?: " + FileUtils.directoryContains(directoriOrigen, fitxer3txt));
            System.out.println();



            //APARTAT 7: Comproba si el fitxer "fitxer1.txt" és mes vell que "Fitxer5.txt" (isFileOlder).
            File fitxer1txt = FileUtils.getFile(directoriOrigen, "fitxer1.txt");
            File fitxer5txt = FileUtils.getFile(directoriOrigen, "fitxer5.txt");
            System.out.println("APARTAT 7:");
            System.out.println("Data (long Date) última modificació de fitxer1.txt: " + fitxer1txt.lastModified());
            System.out.println("Data (Calendar) última modificació de fitxer1.txt: " + convertirLongTimeEnCalendar(fitxer1txt.lastModified()));
            System.out.println("Data (long Date) última modificació de fitxer5.txt: " + fitxer5txt.lastModified());
            System.out.println("Data (Calendar) última modificació de fitxer5.txt: " + convertirLongTimeEnCalendar(fitxer5txt.lastModified()));
            System.out.println("Fitxer1.txt és meś vell que fitxer5.txt?: " + FileUtils.isFileOlder(fitxer1txt, fitxer5txt));
            System.out.println();


            //APARTAT 8: Copia, machacant el contingut anterior, la linia "linia sobreescrita" en el
            //fitxer "fitxer1.txt" (writeStringToFile)
            FileUtils.writeStringToFile(fitxer1txt, "linia sobreescrita", "UTF-8", false);
            System.out.println("APARTAT 8: CANVIAT EL CONTINGUT DEL FITXER fitxer1.txt");
            System.out.println();


            //APARTAT 9: Comproba si el fitxer "fitxer1.txt" és mes nou que "fitxer5.txt"
            System.out.println("APARTAT 9:");
            System.out.println("Data (long Date) última modificació de fitxer1.txt: " + fitxer1txt.lastModified());
            System.out.println("Data (Calendar) última modificació de fitxer1.txt: " + convertirLongTimeEnCalendar(fitxer1txt.lastModified()));
            System.out.println("Data (long Date) última modificació de fitxer5.txt: " + fitxer5txt.lastModified());
            System.out.println("Data (Calendar) última modificació de fitxer5.txt: " + convertirLongTimeEnCalendar(fitxer5txt.lastModified()));
            System.out.println("Fitxer1.txt és meś nou que fitxer5.txt?: " + FileUtils.isFileNewer(fitxer1txt, fitxer5txt));
            System.out.println();


            //APARTAT 10: Fes un touch sobre el fitxer "fitxer5.txt" i comproba si el fitxer "fitxer1.txt"
            //és mes nou que "fitxer5.txt"(touch).
            bloquejarPantalla();
            FileUtils.touch(fitxer5txt);
            System.out.println("APARTAT 10");
            System.out.println("Data (long Date) última modificació de fitxer1.txt: " + fitxer1txt.lastModified());
            System.out.println("Data (Calendar) última modificació de fitxer1.txt: " + convertirLongTimeEnCalendar(fitxer1txt.lastModified()));
            System.out.println("Data (long Date) última modificació de fitxer5.txt: " + fitxer5txt.lastModified());
            System.out.println("Data (Calendar) última modificació de fitxer5.txt: " + convertirLongTimeEnCalendar(fitxer5txt.lastModified()));
            System.out.println("Fitxer1.txt és meś nou que fitxer5.txt?: " + FileUtils.isFileNewer(fitxer1txt, fitxer5txt));
            System.out.println();


            //APARTAT 11: Mou el el directori "origen" al directori "desti" i si no existeix
            //previament "desti" donçs que es crei automàticament (moveDirectoryToDirectory).
            File directoriDesti = new File(path + "desti");
            FileUtils.moveDirectoryToDirectory(directoriOrigen, directoriDesti, true);
            System.out.println("APARTAT 11: S'HA MOGUT EL DIRECTORI " + directoriOrigen.getPath() + " A DINS DEL DIRECTORI " + directoriDesti.getPath());
            System.out.println();


            //APARTAT 12: Calcula el tamany del directori "desti" (sizeOfDirectoryAsBigInteger)
            System.out.println("APARTAT 12: EL TAMANY DEL DIRECTORI desti ES: " + FileUtils.sizeOfDirectoryAsBigInteger(directoriDesti));
            System.out.println("SUMEU EL TAMANY DELS 5 FITXERS QUE HI HA DINS DEL DIRECTORI desti I VEUREU QUE SUMEN 498 BYTES.");
            System.out.println();


            //APARTAT 13: Fer servir el iterador de FileUtils per a recorre el directori "directoriExercici10"
            //i treure per pantalla el seu contingut indicant si son fitxers o directoris (iterateFilesAndDirs).
            System.out.println("APARTAT 13:");
            Iterator it2 = FileUtils.iterateFilesAndDirs(directoriArrel, new WildcardFileFilter("*.txt"), new WildcardFileFilter("*"));
            while (it2.hasNext()){
                File fileTmp = (File)it2.next();
                System.out.println("NOM DEL FILE TROBAT: " + ((File) fileTmp).getName());
                System.out.println("PATH DEL FILE TROBAT: " + ((File) fileTmp).getPath());
                System.out.println("ES UN FITXER?: " + ((File) fileTmp).isFile());
                System.out.println("ES UN DIRECTORI?: " + ((File) fileTmp).isDirectory());
            }

            //FileUtils.cleanDirectory(fDesti);
            //FileUtils.contentEquals(fOrigen, fOrigen);
            //FileUtils.copyDirectory(d, fDesti);
            //FileUtils.copyDirectory(d, fDesti, filter);
            //FileUtils.copyDirectoryToDirectory(d, fDesti);
            //FileUtils.copyFile(fOrigen, fDesti, true);
            //FileUtils.copyFileToDirectory(fOrigen, fDesti, true);
            //FileUtils.copyURLToFile(source, fDesti, 0, 0);
            //FileUtils.deleteDirectory(fDesti);
            //FileUtils.deleteQuietly(fOrigen);
            //FileUtils.directoryContains(directori, file);
            //FileUtils.forceDelete(fOrigen);
            //FileUtils.forceMkdir(fDesti);
            //FileUtils.getFile(fDesti, names);
            //FileUtils.getUserDirectoryPath();
            //FileUtils.isFileNewer(fOrigen, date);
            //FileUtils.isFileNewer(fOrigen, fDesti);
            //FileUtils.isFileOlder(fOrigen, date);
            //FileUtils.isFileOlder(fOrigen, fDesti);
            //FileUtils.listFiles(fDesti, extensions, true);
            //FileUtils.listFilesAndDirs(fDesti, fileFilter, dirFilter);
            //FileUtils.moveDirectoryToDirectory(d, fDesti, true);
            //FileUtils.moveFileToDirectory(fOrigen, fDesti, true);
            //FileUtils.moveToDirectory(d, fDesti, true);
            //FileUtils.readFileToString(fOrigen, "UTF-8");
            //FileUtils.readLines(fOrigen, "UTF-8"); //LLegeix tot el fitxer i retorna un llista de Strings on en cada String hi ha 1 linia del fitxer.
            //FileUtils.sizeOfAsBigInteger(fOrigen);
            //FileUtils.sizeOfDirectoryAsBigInteger(fDesti);
            //FileUtils.touch(fOrigen);
            //FileUtils.write(fOrigen, charSequence, encoding, true);
            //FileUtils.writeLines(fOrigen, lines, true);
            //FileUtils.writeLines(fOrigen, lines, lineEnding, true);
            //FileUtils.writeLines(fOrigen, "UTF-8", lines, lineEnding, true);
            //FileUtils.writeStringToFile(fOrigen, StringACopiar, "UTF-8", true);
        } catch (IOException ex) {
            Logger.getLogger(exercici10.class.getName()).log(Level.SEVERE, null, ex);
        }

    }




}