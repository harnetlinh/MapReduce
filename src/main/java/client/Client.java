package client;

import client.model.ClientConfig;
import server.service.RMIService;

import java.io.*;
import java.rmi.registry.Registry;

public class Client {

    private static Registry registry;

    static ClientConfig getClientConfig(String host, int port){
        ClientConfig clientConfig = new ClientConfig(host, port);
        return clientConfig;
    }

    static RMIService getService(ClientConfig clientConfig) throws Exception
    {
        RMIService service = clientConfig.serviceLookup();
        return service;
    }
    public static void exec(String command) throws InterruptedException, IOException {

//        String command = "java main";

        Process proc = Runtime.getRuntime().exec(command);

        // Read the output

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(proc.getInputStream()));

        String line = "";
        while((line = reader.readLine()) != null) {
            System.out.print(line + "\n");
        }

        proc.waitFor();
    }

    public static void seriCalculation(String path) {
        Calculation e = null;
//        AbstractCalculation a = null;
        try { e = new Calculation();
        } catch (IOException i) {
            i.printStackTrace();
        };

        try {
            System.out.printf("start");
            FileOutputStream fileOut =
                    new FileOutputStream(path);
//            FileOutputStream fileOutA =
//                    new FileOutputStream("./AbstractCalculation.ser");

            ObjectOutputStream out = new ObjectOutputStream(fileOut);
//            ObjectOutputStream outA = new ObjectOutputStream(fileOutA);

            out.writeObject(e);
//            outA.writeObject(a);
            out.close();
//            outA.close();
            fileOut.close();
//            fileOutA.close();
            System.out.printf("Serialized data is saved in ./Calculation.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
