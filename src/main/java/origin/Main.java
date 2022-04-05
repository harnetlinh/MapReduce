package origin;

import cluster.nodeConfig;
import cluster.service.DaemonImpl;
import cluster.service.DaemonService;
import origin.model.ClientConfig;
import cluster.node;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//import cluster.service.DaemonImpl.registerObject;
//import cluster.service.DaemonImpl.startRegistry;

public class Main {
    //
    // private static final String HOST = "localhost";
    // private static final int PORT = 1099;
    // private static final ClientConfig clientConfig = Client.getClientConfig(HOST,
    // PORT);
    public static DaemonService serviceLookup(String host, int port, String name)
            throws NotBoundException, RemoteException {
        Registry registry = LocateRegistry.getRegistry(host, port);
        DaemonService service = (DaemonService) registry
                .lookup(name);
        return service;
    }

    public static void main(String args[]) throws IOException, AlreadyBoundException, NotBoundException {
        // register object

        nodeConfig nodeConfig = new nodeConfig(2);
        ArrayList<node> nodes = nodeConfig.getNodes();

        //Create nodes RMI and connect to all of them
        for (node node : nodes) {
            DaemonImpl _daemon = new DaemonImpl(node.getPortRMI(), node.getPortSocket());
            _daemon.startRegistry();
            _daemon.registerObject("Daemon-" + node.getPortRMI(), _daemon);
            _daemon.initSocket();
            DaemonService _node_service = null;
            //Ping test
            _node_service = serviceLookup("localhost", node.getPortRMI(), "Daemon Service " + node.getPortRMI());
            System.out.println(_node_service.nodePing());

        }
        System.out.println("------------------------------------------------------");
        
        
        // start send file
        Split.splitFile();
        Split.send(nodes);

        //Launch call here
        Launch.execNodes(nodes);
    }
}
