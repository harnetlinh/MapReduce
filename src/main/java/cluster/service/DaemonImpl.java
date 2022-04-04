package cluster.service;

//import cluster.service.CallBackService;
//import cluster.service.DaemonService;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.io.*;

public class DaemonImpl extends UnicastRemoteObject implements DaemonService {
    
    private int PORT = 10010;
    private Registry registry = null;

    public DaemonImpl(int port_)throws RemoteException {
        super();
        //Construct a daemon instance with node
        this.PORT = port_;
    }
    @Override
    public void call(Map m, String blockin, String blockout, CallBackService cb) throws RemoteException{
        
        
        // Call map exec of this node. Threading
        Thread wc
                = new Thread(new WordCount(blockin,blockout));
            wc.start();
        
        // WordCount wc = new WordCount();
        // wc.executeMap(blockin, blockout);
        // waiting for finishing
        
        // Callback complete
    }
    @Override
    public String uploadData()throws RemoteException{
        return "Upload done";
    };
    @Override
    public String downloadData()throws RemoteException{
        return "Download done";
    };
    @Override
    public int nodePing()throws RemoteException{
        return PORT;
    }

	public void startRegistry() throws RemoteException {
        try {
            registry = LocateRegistry.getRegistry(PORT);
            registry.list();
        }
        catch (RemoteException e) {
            registry = LocateRegistry.createRegistry(PORT);
        }

    }
    public void registerObject(String name, Remote remoteObj)
            throws RemoteException, AlreadyBoundException {
        // Registry object on Register.
        // Assign name.
        // Client find register with name to call object.

        registry.rebind(name, remoteObj);
        System.out.println("Registered: " + name + " -> "
                + remoteObj.getClass().getName() + "[" + remoteObj + "]");
    }

//    public static void  main (String args[]) throws RemoteException, AlreadyBoundException {
//        //register object
//        System.out.println("Server starting...");
//        startRegistry();
//        System.out.println("here");
//        registerObject("Daemon 1", new DaemonImpl(PORT));
//        // Server start and listen request from Client.
//        System.out.println("Server started!");
//    }
    
}
