package solutions.UF5.NF3.ejercicios;

import Utils.Constantes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Escriu un programa que realitza còpies de seguretat de tots els arxius d'un
 * directori mitjançant la creació d'un nou subdirectori (del directori actual)
 * anomenat backUp0 i copiant tots els arxius del directori actual al mateix.
 * Els noms d'arxiu de les còpies són les mateixes que en el directori actual.
 * Si backUp0 ja existeix, a continuació, crear Backup1, i així successivament.
 * Utilitzeu orientat a bytes IO el que els arxius de tots els tipus són
 * recolzats. Utilitzeu el list() mètode de File per obtenir una llista de
 * fitxers i directoris. Utilitzeu la isFile() mètode per determinar quina de la
 * llista són els arxius. Utilitzeu mkdir() per crear el nou directori. Si un
 * arxiu no es pot llegir (marqui això amb canRead() ), escriure un missatge
 * d'advertència i continuar. El programa s'executa des de la línia de comandes:
 * 
 *
 */
public class Exercici4 implements Constantes {
	public static void main(String[] args) {
		String subdirectori="backUp";
		String path = "data/in";
		
		File [] arxius = new File(path).listFiles();
		int nBackup = detectarNumBackup(arxius);
		nBackup = nBackup+1;
		File backup = new File (PATH_IN+subdirectori+nBackup);
		backup.mkdir();
		String pathDesti = path+"/"+subdirectori+nBackup;
		copiarTotsArxius(path,pathDesti	,arxius);
		System.out.println("fi");
	}

	private static int detectarNumBackup(File[] arxius) {
		int max=0;
		for (File f : arxius) {
			if (f.isDirectory()){
				String nomArxiu = f.getName();
				if (nomArxiu.matches(".*backUp[0-9]+")){
					String nBackUp=nomArxiu.substring(6, nomArxiu.length());
					if(max < Integer.parseInt(nBackUp)){
						max=Integer.parseInt(nBackUp);
					}
				}
			}
		}
		return max;
	}

	private static void copiarTotsArxius(String pathOrigen, String pathToBackup, File[] list ) {
			for (File fOrigen : list) {
				if (fOrigen.isFile()){
					File fDesti = new File (pathToBackup+"/"+fOrigen.getName());
					try {
						if (fOrigen.canRead()){
							fDesti.createNewFile();
							copiar (fOrigen, fDesti);
						}
						else {
							System.out.println("L'arxiu "+fOrigen+" no s'ha pogut copiar ja que no té permisos de lectura");
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
	}
	
	private static void copiar(File fileI, File fileO) throws IOException {
		boolean append=true;
		byte buff[] = new byte[100];
		FileInputStream fileInputStream= new FileInputStream(fileI);
		FileOutputStream fileOutputStream = new FileOutputStream(fileO ,append);
		try {
			int reads;
			while ((reads = fileInputStream.read(buff, 0, 100))>-1){
				fileOutputStream.write(buff,0,reads);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			fileOutputStream.close();
			fileInputStream.close();

		}
	}
}
