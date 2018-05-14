package process;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HTMLtoString {
    public static String loadHTML(String filename) throws FileNotFoundException {
        String toReturn = "";
        Scanner file = new Scanner(new File(System.getProperty("user.dir") + "/src/main/java/" + filename));
        while (file.hasNextLine()) {
            toReturn += file.nextLine() + " ";
        }

        return toReturn;
    }
}

