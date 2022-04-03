package server;

import server.service.RMIService;
import server.service.RMIServiceImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    private static final int PORT = 1099;
    private static Registry registry = null;


    public static double a = 7d;
    public static double b = 8d;

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


    public static void main(String[] args) throws Exception {
        System.out.println("Server starting...");
        
        //Create multiple Nodes from registries & ports
        startRegistry();
        registerObject(RMIService.class.getSimpleName(), new RMIServiceImpl());
        
        
        // Server start and listen request from Client.
        System.out.println("Server started!");
    }
    
}
