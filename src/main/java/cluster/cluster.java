package cluster;

import cluster.service.RMIService;
import serclusterver.service.RMIServiceImpl;
import cluster.nodeConfig;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class cluster {

    private static final int PORT = 1099;
    private static Registry registry = null;

    public static void startRegistry(int port) throws RemoteException {
        try {
            registry = LocateRegistry.getRegistry(port);
            registry.list();
        }
        catch (RemoteException e) {
            registry = LocateRegistry.createRegistry(PORT);
        }

    }
    public static void registerObject(String name, Remote remoteObj)
            throws RemoteException, AlreadyBoundException {
        // Registry object on Register.
        // Assign name.
        // Client find register with name to call object.

        registry.rebind(name, remoteObj);
        System.out.println("Registered: " + name + " -> "
                + remoteObj.getClass().getName() + "[" + remoteObj + "]");
    }


    public void createNode() throws Exception {
        System.out.println("Server starting...");
        
        //Create multiple Nodes from registries & ports

        int port_;
        for (int i = 0; i < nodeConfig.cluster.size(); i++) { 		      
            System.out.println(arrlist.get(i)); 
            port_;
            startRegistry(port_);
            registerObject(RMIService.class.getSimpleName(), new RMIServiceImpl());	
        }

        
        
        //start registry in each port
        //register each daemon to this (add name, add new instance of daemon)
        
        
        // Server start and listen request from Client.
        System.out.println("Server started!");
    }
    
}
