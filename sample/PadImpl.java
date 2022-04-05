package server;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;

public class PadImpl extends UnicastRemoteObject implements Pad {
    private Hashtable<String, RRecord> tab = new Hashtable<String, RRecord>();

    private static final int PORT = 1099;
    private static Registry registry = null;

    public PadImpl()throws RemoteException{};

    @Override
    public void add (SRecord sr) throws RemoteException{
        String name = sr.getName();
        String email = sr.getEmail();
        RRecord rr = new RRecordImpl(name,email);
        tab.put(name,rr);


    }

    @Override
    public RRecord consult(String n, boolean forward) throws RemoteException {
        return tab.get(n);
    }



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

    public static void  main (String args[]) throws RemoteException, AlreadyBoundException {
        //register object
        System.out.println("Server starting...");
        startRegistry();
        registerObject(Pad.class.getSimpleName(), new PadImpl());
        // Server start and listen request from Client.
        System.out.println("Server started!");
    }


}