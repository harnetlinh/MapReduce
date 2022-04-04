package cluster;
import cluster.node;
import cluster.nodesConfig;
import cluster.MapReduceService;

import java.util.*;
import java.io.*;

public class DaemonImpl extends UnicastRemoteObject implements DaemonService {
    
    private static final int PORT;
    private static Registry registry = null;

    public DaemonImpl(int port_)throws RemoteException{
        //Construct a daemon instance with node
        this.PORT = port_;
    }
    @Override
    public void call(Map m, String blockin, String blockout, CallBack cb) throws RemoteException{
        // getting node
        // Callbacksetup
        // Call map exec of this node. Threading
        // waiting for finishing
        // Call reduce exec
    }
    @Override
    public String uploadData(){};
    @Override
    public String downloadData(){};

	public static void startRegistry() throws RemoteException {
        try {
            registry = LocateRegistry.getRegistry(PORT);
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

    public static void  runRMI () throws RemoteException, AlreadyBoundException {
        //register object
        System.out.println("Server starting...");
        startRegistry();
        registerObject(Pad.class.getSimpleName(), new PadImpl());
        // Server start and listen request from Client.
        System.out.println("Server started!");
    }
    
}
