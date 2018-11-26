package solutions.UF5.NF3.ejercicios;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 3. Escriure un programa que compara dos arxius de text línia per línia.
 * Llegir una línia de cada arxiu. Compareu les dues línies. Si són idèntiques,
 * continuar amb els següents dues línies. En cas contrari, escriviu el número
 * de línia i les dues línies, i continuar.
 * 
 *
 */
public class Exercici3 {
	public static void main(String[] args) throws IOException {
		String path = "data/in/";
		String pathname1 = path+"a.txt";
		String pathname2 = path+"b.txt";

		BufferedReader buffer1=null;
		BufferedReader buffer2=null;
		try {
			buffer1 = new BufferedReader(new FileReader(new File(pathname1)));
			buffer2 = new BufferedReader(new FileReader(new File(pathname2)));
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
}
