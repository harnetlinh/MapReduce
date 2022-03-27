package server;

// file: RunShellCommandFromJava.java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Runtime.getRuntime;

public class RunShellCommandFromJava {

    public static void exec(String command ) throws InterruptedException, IOException {

        Process proc = getRuntime().exec(command);

//        Process exec = getRuntime().exec("/home/user/test/test.sh");
//        java.util.Scanner s = new java.util.Scanner(exec.getInputStream()).useDelimiter("\\A");


        // Read the output

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(proc.getInputStream()));

        String line = "";
        while((line = reader.readLine()) != null) {
            System.out.print(line + "\n");
        }

        proc.waitFor();

    }
} 
