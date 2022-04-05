package cluster.service;

//import cluster.service.CallBackService;
//import cluster.service.DaemonService;

import cluster.service.WordCount;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class DaemonImpl extends UnicastRemoteObject implements DaemonService {

    private int PORT = 10010;
    private Registry registry = null;
    private int PORT_SOCKET = 10010;
    public final Integer SIZE_PER_READING = 64 * 1024;

    public DaemonImpl(int port_, int port_socket_) throws RemoteException {
        super();
        // Construct a daemon instance with node
        this.PORT = port_;
        this.PORT_SOCKET = port_socket_;
    }

    @Override
    public void call(Map m, String blockin, String blockout, CallBackService cb) throws RemoteException {

        // Call map exec of this node. Threading
        Thread wc = new Thread(new WordCount(blockin,blockout,cb));
        wc.start();


        // WordCount wc = new WordCount();
        // wc.executeMap(blockin, blockout);
        // waiting for finishing

        // Callback complete
    }



    @Override
    public String uploadData() throws RemoteException {
        return "Upload done";
    };

    @Override
    public String downloadData() throws RemoteException {
        return "Download done";
    };

    @Override
    public int nodePing() throws RemoteException {
        return PORT;
    }

    public void startRegistry() throws RemoteException {
        try {
            registry = LocateRegistry.getRegistry(PORT);
            registry.list();
        } catch (RemoteException e) {
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

    public ServerSocket initSocket() throws RemoteException {
        String working_Dir = System.getProperty("user.dir");
        try {
            ServerSocket serverSocket = null;
            
            serverSocket = new ServerSocket();
            serverSocket.setReuseAddress(true);
            serverSocket.bind(new InetSocketAddress(PORT_SOCKET));
            System.out.println("Server is listening on port " + PORT_SOCKET);
            return serverSocket;
            // byte[] bytes = new byte[SIZE_PER_READING];
            // int dataReceived = 0;
            // Socket socket = serverSocket.accept();
            // System.out.println("Client connected");
            // InputStream inputStream = socket.getInputStream();
            // OutputStream outputStream = new FileOutputStream(working_Dir+File.separator+"server_storage"+File.separator+"received"+File.separator+"data"+this.PORT_SOCKET+".txt");
            // System.out.println("Receiving data...");
            // while ((dataReceived = inputStream.read(bytes)) > 0) {
            //     outputStream.write(bytes, 0, dataReceived);
            // }
            // System.out.println("Received data");
            // outputStream.close();
            // inputStream.close();
            // serverSocket.close();
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static void  main (String args[]) throws RemoteException, AlreadyBoundException {
        //register object
//        System.out.println("Server starting...");
//        startRegistry();
//        System.out.println("here");
//        registerObject("Daemon 1", new DaemonImpl(PORT));
//        // Server start and listen request from Client.
//        System.out.println("Server started!");
        DaemonService dae = new DaemonImpl(10010, 20010);
        CallBackService cb = null;
        Map m = null;
        String blockin = "asd.txt";
        String blockout = "null";
        dae.call( m,  blockin,  blockout,  cb);
        System.out.println("call first node");

        DaemonService dae2 = new DaemonImpl(10011, 20011);
        CallBackService cb2 = null;
        dae2.call( m,  blockin,  blockout,  cb2);
        System.out.println("call second node");
    }
    
}
