package AntonioAguire.UF5.NF3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CountFile {
    public void CountFile(){
        List<String> arxius = new ArrayList<String>();
        arxius.add("a.txt");
        arxius.add("b.txt");

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
}
