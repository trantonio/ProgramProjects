package solutions.UF5.NF3.ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 5. Escriure un programa que rebi per paràmetre una llista de fitxers, volem
 * que el programa conti el número de línies de cada fitxer que s'especifica al
 * arguments del programa. S'ha de mostrar el nom de cada fitxer, el número de
 * línies i el número de caràcters. Si es produeix un error al intentar llegir
 * un d'aquests fitxers haurà d'imprimir un missatge d'error per aquest fitxer,
 * però haurà de processar els restants.
 * 
 *
 */
public class Exercici5 {
	public static void main(String[] args) {
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
