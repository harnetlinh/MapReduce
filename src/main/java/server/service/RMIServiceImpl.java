package server.service;

import server.AppManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

//import static server.Calculation.array;


public class RMIServiceImpl extends UnicastRemoteObject implements RMIService {
    public RMIServiceImpl() throws RemoteException {
//        try {
//           Calculation.array();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

//    public double plusArray() throws IOException {
//        double e = 0;
//        e = AppManager.plusArray();
//        return e;
//    }
    public Boolean sendFile(List<String> files) throws IOException, InterruptedException{
        File pathFile = null;
//        List<String>  files = new ArrayList<String>();


        try {
        for (int i = 0; i < files.size(); i++) {
            if (files.get(i) == "") {continue;};
            pathFile = new File(files.get(i));
            String[] name = files.get(i).split("/");


                byte[] pathFileData = new byte[(int) pathFile.length()];
//                FileInputStream in_path = new FileInputStream(path);
                System.out.println("uploading to server...");
                System.out.println(name[name.length-1]);
//                in_path.read(pathFileData, 0, pathFileData.length);


                File serverpath_ = new File("./server_storage/"+ name[name.length-1]);
                FileOutputStream out = new FileOutputStream(serverpath_);
                out.write(pathFileData);
                out.flush();
                out.close();
//                in_path.close();
        }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        
        return true;
    };

    public String CreateCalculation(String scriptPath) throws Exception {
        //TODO: put task to AppManager
        AppManager.requestCalculation(scriptPath);
        //TODO: return string confirm to client
        return "Calculation Created";
    };

//    public double plus2Number() {
//        double c = 0;
//        try {
//            c = AppManager.plus2Number();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return c;
//    }
//
//    public double minus2Number() {
//        double c = 0;
//        try {
//            c = AppManager.minus2Number();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return c;
//    }
//
//    public double multiple2Number() {
//        double c = 0;
//        try {
//            c = AppManager.multiple2Number();
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//        return c;
//    }
//
//    public double divide2Number() throws Exception {
//        double c;
//        c = AppManager.divide2Number();
//        if (AppManager.h == 0) {
//            throw new Exception("Divided by zero!");
//        } else {
//            return c;
//        }
//    }
//
//    public String server_ping() throws Exception{
//        String c;
//        c = AppManager.server_ping();
//        return c;
//    }

//    public String[] getNum() {
//        return array;
//    }
}