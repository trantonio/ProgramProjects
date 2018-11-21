package AntonioAguire;

import java.util.ArrayList;

public class ArrayListExample {

    ArrayList ll = new ArrayList();
    long tempsInicial = System.nanoTime();

    public ArrayListExample() {

        for (int i = 0; i <= 10000; i++) {
            ll.add(i);
        }
        System.out.printf("%,d \n",tempsInicial);
    }
}
