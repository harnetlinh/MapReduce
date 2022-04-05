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
import java.net.ServerSocket;
import java.net.Socket;

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
        Thread wc = new Thread(new WordCount(blockin, blockout));
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

    public void initSocket() throws RemoteException {
        String working_Dir = System.getProperty("user.dir");
        try {
            ServerSocket serverSocket = null;
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            byte[] bytes = new byte[SIZE_PER_READING];
            int dataReceived = 0;

            serverSocket = new ServerSocket(PORT);
            socket = serverSocket.accept();

            inputStream = socket.getInputStream();
            outputStream = new FileOutputStream(working_Dir + File.separator + "server_storage" + File.separator
                    + "received" + File.separator + "data" + this.PORT_SOCKET + ".txt");

            while ((dataReceived = inputStream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, dataReceived);
            }
            outputStream.close();
            inputStream.close();
            socket.close();
            serverSocket.close();
            // File file = new
            // File(working_Dir+"\\server_storage\\received\\data"+this.PORT_SOCKET+".txt");

            // while (true) {
            // System.out.println("Waiting for client on port " + this.PORT_SOCKET);
            // Socket socket = serverSocket.accept();
            // System.out.println("Just connected to " + socket.getRemoteSocketAddress());
            // byte[] mybytearray = new byte[(int) file.length()];
            // BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            // bis.read(mybytearray, 0, mybytearray.length);
            // OutputStream os = socket.getOutputStream();
            // os.write(mybytearray, 0, mybytearray.length);
            // os.flush();
            // socket.close();
            // }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // public static void main (String args[]) throws RemoteException,
    // AlreadyBoundException {
    // //register object
    // System.out.println("Server starting...");
    // startRegistry();
    // System.out.println("here");
    // registerObject("Daemon 1", new DaemonImpl(PORT));
    // // Server start and listen request from Client.
    // System.out.println("Server started!");
    // }

}
