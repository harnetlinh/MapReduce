package origin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MapReduce {
    public static void main(String[] args) throws IOException {
        reduce();
    }

    public static void reduce() throws IOException {
        //get the file path
        String working_Dir = System.getProperty("user.dir");

        BufferedReader br = new BufferedReader(new FileReader(working_Dir + "\\server_storage\\data.txt"));

        try {
            ArrayList<String> arrOfStr = new ArrayList<String>();
            String line = br.readLine();
            // read the file line by line
            while (line != null) {
                arrOfStr.add(line);
                System.out.println(line);
                line = br.readLine();
            }
            for (int i = 0; i < arrOfStr.size(); i++) {
                PrintWriter writer = new PrintWriter(working_Dir + "\\server_storage\\result" + i + ".txt", "UTF-8");
                writer.println(arrOfStr.get(i));
                writer.close();
            }
            System.out.println("Done");
        } finally {
            br.close();
        }
    }
}
