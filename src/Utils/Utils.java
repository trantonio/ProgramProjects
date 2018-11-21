package Utils;

import java.util.Scanner;

public class Utils {

    public static void printArgsWhitSpace(String text,String dada){
        Scanner sc = new Scanner(dada);
        do {
            dada = sc.next();
            System.out.println(text+ dada);
        } while (sc.hasNext());
    }
}
