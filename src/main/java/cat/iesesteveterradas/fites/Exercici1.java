package cat.iesesteveterradas.fites;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Objectiu de l'exercici:
 * - Llegir el fitxer 'Exercici1.java'.
 * - Crear un nou fitxer 'Exercici1out.txt'.
 * - Invertir el text de cada línia utilitzant 'giraText'.
 *
 * Exemple:
 * - Si 'Exercici1.java' comença amb "package cat.iesesteveterradas.fites;",
 *   en 'Exercici1Solucio.txt' es convertirà en ";setif.sadatsevetsertei.tac egakcap".
 *
 * Ubicació del fitxer de sortida:
 * - Emmagatzemar a 'data/exercici1' dins del directori data/exercici1
 */

public class Exercici1 {
    public static void main(String args[]) {
        String basePathIn = System.getProperty("user.dir") + "/src/main/java/cat/iesesteveterradas/fites/";
        String basePathOut = System.getProperty("user.dir") + "/data/exercici1/";
        String filePathIn = basePathIn + "Exercici1.java";
        String filePathOut = basePathOut + "Exercici1Solucio.txt";
        File fileout = new File(filePathOut);
        String source ="";
        try {
             if (fileout.createNewFile()) {
            System.out.println("File created: " + fileout.getName());
             } else {
            System.out.println("File already exists.");
            }
            FileWriter fOut = new FileWriter(filePathOut);            
            File file = new File(filePathIn);
            Scanner scnr = new Scanner(file);
            while(scnr.hasNextLine()){
                String line = scnr.nextLine();
                line = giraText(line);
                source += line +"\n";
              
            }
            // Bucle que llegeix línia a línia 'filePathIn'
            // i escriu línia a línia 'filePathOut' amb el text girat
            
            fOut.write(source);
            scnr.close();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String giraText (String text) {
        String resultat = "";
        for(int cnt = text.length() - 1; cnt >= 0; cnt--) {
            resultat = resultat + text.charAt(cnt);
        }
        return resultat;
    }
}
