package AntonioAguire.UF5.NF3;

import Utils.Constantes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ComparationArchive implements Constantes {

        private String pathName1 = PATH_IN + "a.txt";
        private String pathName2 = PATH_IN + "b.txt";

        ComparationArchive() throws IOException {
            BufferedReader buffer1 = null;
            BufferedReader buffer2 = null;
            try {
                buffer1 = new BufferedReader(new FileReader(new File(pathName1)));
                buffer2 = new BufferedReader(new FileReader(new File(pathName2)));
                String line1 = buffer1.readLine();
                String line2 = buffer2.readLine();
                int i = 0;
                while (line1 != null && line2 != null && line1.equals(line2)) {
                    line1 = buffer1.readLine();
                    line2 = buffer2.readLine();
                    i++;
                }
                if (line1 != null || line2 != null) {
                    System.out.format("Falla la línia %d %n", i);
                    System.out.println("contingut de la linia " + line1);
                    System.out.println("contingut de la linia " + line2);
                } else {
                    System.out.println("Els arxius són iguals");
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                buffer1.close();
                buffer2.close();
            }
        }
        public static void CompareFiles(String f1, String f2) throws IOException{
            String file1= PATH_IN+f1+".txt";
            String file2 = PATH_IN+f2+".txt";
            BufferedReader buffer1 = null;
            BufferedReader buffer2 = null;


            try {
                buffer1 = new BufferedReader(new FileReader(new File(file1)));
                buffer2 = new BufferedReader(new FileReader(new File(file2)));
                String line1 = buffer1.readLine();
                String line2 = buffer2.readLine();


                int i = 1;
                while (line1 != null && line2 != null) {
                    //Compara si son diferentes y si son diferentes devuelve la linea y el fallo
                    if(!line1.equals(line2)){
                        System.out.format("Falla la línia %d %n", i);
                        System.out.println("Contenido linea es: " + line1);
                        System.out.println("Contenido linea es: " + line2);
                    }
                    line1 = buffer1.readLine();
                    line2 = buffer2.readLine();
                    i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                buffer1.close();
                buffer2.close();
            }
        }


}
