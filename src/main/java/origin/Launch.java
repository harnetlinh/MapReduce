package origin;

import cluster.node;
import cluster.service.CallBackImpl;
import cluster.service.CallBackService;
import cluster.service.DaemonService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Map;

public class Launch {

    public final static Integer SIZE_PER_READING = 64 * 1024;
    ArrayList<node> nodes;

    public static void execNodes(ArrayList<node> nodes) throws NotBoundException, RemoteException {
        DaemonService _node_service = null;

        for (node node : nodes) {
            // Ping test
            _node_service = serviceLookup("localhost", node.getPortRMI(), "Daemon Service " + node.getPortRMI());
            System.out.println(_node_service.nodePing());

            // Callback create
            CallBackService cb = null;
            Map m = null;
            String blockin = "asd";
            String blockout = "null";
            try {

                cb = (CallBackService) new CallBackImpl(2);

                _node_service.call(m, blockin, blockout, cb);
                ((CallBackImpl) cb).waitforall();

            } catch (RemoteException ex) {
                ex.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // TODO:result download here

        }

    }

    public static DaemonService serviceLookup(String host, int port, String name)
            throws NotBoundException, RemoteException {
        Registry registry = LocateRegistry.getRegistry(host, port);
        DaemonService service = (DaemonService) registry
                .lookup(name);
        return service;
    }

    public static void download(node node) {
        try {
            String working_Dir = System.getProperty("user.dir");
            ServerSocket serverSocket = node.getServerSocket();
            byte[] bytes = new byte[SIZE_PER_READING];
            int dataReceived = 0;
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");
            InputStream inputStream = socket.getInputStream();
            String fileName = working_Dir + File.separator + "server_storage"
            + File.separator + "received" + File.separator + "data" + node.getPortSocket() + ".txt";
            OutputStream outputStream = new FileOutputStream(fileName);
            System.out.println("Receiving data...");
            while ((dataReceived = inputStream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, dataReceived);
            }
            System.out.println("Received data");
            outputStream.close();
            inputStream.close();
            serverSocket.close();
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        
    }

}
