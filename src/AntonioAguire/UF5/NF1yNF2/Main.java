package AntonioAguire.UF5.NF1yNF2;

import Utils.Utils;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ArrayListExample ale = new ArrayListExample();
        LinkedListExample lle = new LinkedListExample();
        System.out.printf("La diferencia es %,d nano segundos", lle.tempsInicial- ale.tempsInicial);

        System.out.println("----------------------------------------");

        Utils.printArgsWhitSpace("Argumento numero ","1 2 3 4 5 6 7 8 9 10 11 12");

    }
}
