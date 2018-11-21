package solutions.UF5.NF3.ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Crear un programa que donada una url, agafi tots els links que contingui i
 * els guardi en un fitxer de text. En un altre fitxer guardarem totes les dades
 * de la pàgina eliminant tots els tags que trobem.
 * 
 *
 */
public class Exercici8 {
	public static void main(String[] args) throws IOException {
		String link = "http://www.escoladeltreball.org/ca";
		buscarLinks(link);
		obtenirContingut(link);
	}

	private static void obtenirContingut(String link) throws IOException {
		FileWriter fileW = null;
		PrintStream ps=null;
		BufferedReader br=null;
		try {
			URL url = new URL(link);
			URLConnection conn = url.openConnection();
			 br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			ps = new PrintStream(new File("contingut.txt"));
			fileW = new FileWriter("contingut.txt");
			Pattern p = Pattern.compile("(<p)([^>]*)(>)([^<]*?)(</p>)");
			String text="";
			String line = "";
			while ((line = br.readLine()) != null) {
				text=text+line;
			}
			Matcher matcher = p.matcher(text);
			while (matcher.find()) {
				fileW.write(matcher.group(4)+"\n");
				System.out.println(matcher.group(4));
				//ps.println(matcher.group(4));
			}
		} catch (Exception e) {
			System.out.println("error"+e.getMessage());
		}
		fileW.close();
		br.close();
		ps.close();		
	}

	private static void buscarLinks(String link) throws IOException {
		FileWriter fileW = null;
		PrintStream ps=null;
		BufferedReader br=null;
		try {
			URL url = new URL(link);
			URLConnection conn = url.openConnection();
		    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			ps = new PrintStream(new File("links.txt"));
			fileW = new FileWriter("links.txt");
			Pattern p = Pattern.compile("(<a href=\")([^\"]+)(\")");

			String line = "";
			while ((line = br.readLine()) != null) {
				Matcher matcher = p.matcher(line);
				while (matcher.find()) {
					fileW.write(matcher.group(2)+"\n");
			//		ps.println(matcher.group(2));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		fileW.close();
		br.close();
		ps.close();
	}
}
