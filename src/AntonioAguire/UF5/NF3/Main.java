package AntonioAguire.UF5.NF3;

import Utils.DeleteFolders;
import Utils.JSONhlp;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        //Configuramos la clase parse para que funcionen los json
        JSONhlp.configParser("NF3","Main");
        int numOptions= 3;
        System.out.println(JSONhlp.jsonObject.get("Intro"));

        for(int i = 0; i<=numOptions;i++){
            System.out.println("\t"+i+".- "+JSONhlp.jsonObject.get("Option"+i));
        }
        Scanner sc = new Scanner(System.in);
        Menu(sc.nextInt());

    }
    public static void Menu (int option) throws IOException {
        switch (option){
            case 0:
                File[] arxius = new File("data/in").listFiles();
                for (File f : arxius) {
                    DeleteFolders.deleteFolder(f);
                }
                break;
            case 1:
                ComparationArchive.CompareFiles("a","b");
                break;
            case 2:
                new BackUp();
                break;
            case 3:
                new CountFile();
                default:
                    System.out.println(JSONhlp.jsonObject.get("NoOption"));
                    break;
        }

    }

}
