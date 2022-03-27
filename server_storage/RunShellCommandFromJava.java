
// file: RunShellCommandFromJava.java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Runtime.getRuntime;

public class RunShellCommandFromJava {

    public static void main(String[] args ) throws InterruptedException, IOException {

//        Process proc = getRuntime().exec("java main ./Data.csv ../result_server.csv");
//
////        Process exec = getRuntime().exec("/home/user/test/test.sh");
////        java.util.Scanner s = new java.util.Scanner(exec.getInputStream()).useDelimiter("\\A");
//
//
//        // Read the output
//
//        BufferedReader reader =
//                new BufferedReader(new InputStreamReader(proc.getInputStream()));
//
//        String line = "";
//        while((line = reader.readLine()) != null) {
//            System.out.print(line + "\n");
//        }
//
//        proc.waitFor();

        ProcessBuilder processBuilder = new ProcessBuilder("../");

        processBuilder.directory(new File("foo"));

        // Windows
        processBuilder.command("bash", "-c","java main ./Data.csv ../result_server.csv");

        try {

            Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
} 
