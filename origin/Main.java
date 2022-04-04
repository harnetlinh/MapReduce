package client;

import client.model.ClientConfig;
import server.service.RMIService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    //
    private static final String HOST = "localhost";
    private static final int PORT = 1099;
    private static final ClientConfig clientConfig = Client.getClientConfig(HOST, PORT);
    private static RMIService service = null;

    static {
        try {
            service = Client.getService(clientConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        //TODO: get host, port info from user database

        //TODO: remove input data, import data from server side
        System.out.println("======== Lets Do Some Calculation on Remote Server ========");

        //TODO: Abstract calculation implementation here, remove old example
        // Plus a and b

//        String calFile = "./Calculation.java";
//
        String shFile = "";
//        String classFile = "./Calculation.class";
//        String serFile = "./Calculation.ser";
//
//        //Create .class file
//        System.out.print("creating .class file /n");
//        String command = "javac " + calFile;
//        Client.exec(command);
//        Client.exec("javac main.java");
//
//
//        //Serial file
//        Client.seriCalculation(serFile);
//        List<String> files = new ArrayList<String>();
//        files = Arrays.asList(classFile,serFile,"./main.class","./AbstractCalculation.class");
//
//        Boolean sent = service.sendFile(files);
//        if (sent == true){System.out.println("updated calculation to server");
//            service.CreateCalculation(shFile);
//        };
        service.CreateCalculation(shFile);
    }
}
