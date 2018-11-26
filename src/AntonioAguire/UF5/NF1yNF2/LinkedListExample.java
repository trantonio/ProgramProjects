package AntonioAguire.UF5.NF1yNF2;

import java.util.LinkedList;

public class LinkedListExample {

    LinkedList ll = new LinkedList();
    long tempsInicial = System.nanoTime();

    public LinkedListExample() {

        for (int i = 0; i <= 10000; i++) {
            ll.add(i);
        }
        System.out.printf("%,d \n",tempsInicial);

    }
    public long time(){

        System.out.printf("%,d \n",tempsInicial);

        return 0;
    }
}
