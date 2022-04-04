package server;
import server.service.RMIServiceImpl;

import java.rmi.RemoteException;




public class AppManager {
    public static double g = Server.a;
    public static double h = Server.b;
    public static int count = 0;

    static calculationLoader loadClass;

    static {
        try {
            loadClass = new calculationLoader();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private RMIServiceImpl service;


    public AppManager() throws Exception {
        super();

    }
    public static void requestCalculation(String scriptPath) throws Exception {
        
        RunShellCommandFromJava.exec("java -cp ./server_storage/ main " +
                "./server_storage/Data.csv ./server_storage/result_server.csv");

    }
}

