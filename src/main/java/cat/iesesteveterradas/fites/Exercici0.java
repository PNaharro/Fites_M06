package cat.iesesteveterradas.fites;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Objectius de l'exercici:
 * - Comprova l'existència del fitxer filePaht.
 * - Determina si el fitxer és ocult.
 * - Mostra la data de l'última modificació.
 * - Verifica si el fitxer pot ser modificat.
 * - Llista els arxius dins d'un directori basePath.
 */

public class Exercici0 {
    
    public static void main(String args[]) {
        
        String basePath = System.getProperty("user.dir") + "/data/exercici0/";
        String filePath = basePath + "Exercici0.dat";
        Path path = Paths.get( basePath + "Exercici0.dat");
        File f = new File(filePath);
        boolean existeix = false;
        boolean ocult = false;
        boolean esPotModificar = true;
        System.out.println(filePath);
        if (f.exists()){
            existeix = true;
        }
        if (f.isHidden()){
            ocult = true;
        }

        boolean bool = Files.isWritable(path);
        if(bool) {
         esPotModificar =true;
        } else {
         esPotModificar = false;
        }
    
        System.out.println("L'arxiu existeix: " + existeix);
        System.out.println("L'arxiu és ocult: " + ocult);

        Date modificat = new Date(0);
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyy hh:mm:ss");  
        String strModificat = dateFormat.format(f.lastModified());  
        System.out.println("Última modificació: " + strModificat);
        
        System.out.println("L'arxiu es pot modificar: " + esPotModificar);

        File directori =new File( System.getProperty("user.dir") + "/data/exercici0/");
        ArrayList<String> llistaArxius = new ArrayList<>();
        String contents[] = directori.list();
        for (int i = 0;i<contents.length;i++){
            llistaArxius.add(contents[i]);
        }
        System.out.println("La llista d'arxius d'aquesta carpeta és: " + llistaArxius);
    }
}
