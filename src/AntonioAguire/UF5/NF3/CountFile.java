package AntonioAguire.UF5.NF3;

import Utils.Constantes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CountFile implements Constantes {
    public CountFile() {
        List<String> arxius = new ArrayList<String>();
        arxius.add(PATH_IN+"a.txt");
        arxius.add(PATH_IN+"b.txt");
        for (String arxiu : arxius) {
            File f = new File(arxiu);
            FileReader reader;

            int nlinies = 0;
            int nchars = 0;

            try {
                reader = new FileReader(f);
                BufferedReader buffer = new BufferedReader(reader);
                String linia;
                while ((linia = buffer.readLine()) != null) {
                    nchars = nchars + linia.length();
                    nlinies++;
                }

            } catch (Exception e) {
                System.out.println("l'arxiu"+f.getName()+"ha generat un error" );
            }
            System.out.println("l'arxiu "+f.getName()+" té "+nlinies+" linies i "+nchars+" caracters" );
        }
    }
    public static String CountFile(File f){
        FileReader reader;
        int nLines = 0;
        int nChars = 0;
        String linea;
        try{
            reader = new FileReader(f);
            BufferedReader bff = new BufferedReader(reader);
            while ((linea = bff.readLine())!= null){
                nChars = nChars + linea.length();
                nLines++;
            }

        }catch (Exception e){
            System.out.println("El archivo "+f.getName()+" ha generado un error" );
        }

        return "El arhcivo "+f.getName()+" Tiene "+nLines+" lineas y "+nChars+ " caracteres.";
    }

}
