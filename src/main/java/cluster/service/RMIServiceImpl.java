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
    
    private nodeConfig nodeConfigs;
    public RMIServiceImpl() throws RemoteException {

    }


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

    public String setClusterConfig(int numberOfNodes){
        // check if a cluster already exits
        // destroy if yes
        // create cluster
        this.nodeConfigs = new nodeConfig(numberOfNodes);
        return "Set config successfully"

    };

    public ArrayList<node> getNodesInfo(){
        return this.nodeConfigs.getNodes();
    };
    
    public void createNodes(){
        
    }


}