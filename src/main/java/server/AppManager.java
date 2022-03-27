package server;
import server.service.RMIServiceImpl;

import java.rmi.RemoteException;

//import static server.Calculation.array;


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
//        try {
//            calculationJob = new Calculation();
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
    }

    private RMIServiceImpl service;


    public AppManager() throws Exception {
        super();
//        try {
////            array();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    public static void requestCalculation(String scriptPath) throws Exception {
        //Setup custom calculation
//        Class Calculation = loadClass.getClass("./server_storage/Calculation.class");
//        "./src/main/java/server/Calculation.class"
//        Object obj_Calculation = Calculation.newInstance();
//        Calculation.getMethod("Run", null).invoke(obj_Calculation, null);
        RunShellCommandFromJava.exec("java -cp ./server_storage/ main " +
                "./server_storage/Data.csv ./server_storage/result_server.csv");
//                "java ./server_storage/main ./server_storage/Data.csv ./server_storage/result_server.csv");
//        static Calculation calculationJob;
        //Calculation validation
//        Calculation calculationJob = new Calculation();;
//        calculationJob.Run();

        //Calculation method save to server (per user)
        //Booking calculation to queue
//        calculationJob.Run();

//        try {
//            scriptPath.split("/");
//            String[] name = scriptPath.split("/");
//            RunShellCommandFromJava.main("./" + name[name.length-1]);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


//    public static double plusArray() throws IOException {
//        double c = Calculation.plusArray();
//        return c;
//    }
//
//    public static double plus2Number() throws RemoteException {
//        double c = Calculation.plus2Number();
//        return c;
//    }
//
//    public static double minus2Number() throws RemoteException {
//        double c = Calculation.minus2Number();
//        return c;
//    }
//
//    public static double multiple2Number() throws RemoteException {
//        double c = Calculation.multiple2Number();
//        return c;
//    }
//
//    public static double divide2Number() throws Exception {
//        double c = Calculation.divide2Number();
//        return c;
//    }
//
//    public static String server_ping() throws Exception {
//        String c = Calculation.server_ping();
//        return c;
//    }
}

