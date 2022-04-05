package origin;

import cluster.nodeConfig;
import cluster.service.DaemonImpl;
import cluster.service.DaemonService;
import origin.model.ClientConfig;
import cluster.node;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import origin.Split;

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

    public static void main(String args[]) throws RemoteException, AlreadyBoundException, NotBoundException {
        // register object

        nodeConfig nodeConfig = new nodeConfig(3);
        ArrayList<node> nodes = nodeConfig.getNodes();

        for (node node : nodes) {
            DaemonImpl _daemon = new DaemonImpl(node.getPortRMI(), node.getPortSocket());
            _daemon.startRegistry();
            _daemon.registerObject("Daemon-" + node.getPortRMI(), _daemon);
            _daemon.initSocket();
            DaemonService _node_service = null;
            _node_service = serviceLookup("localhost", node.getPortRMI(), "Daemon Service " + node.getPortRMI());
            System.out.println(_node_service.nodePing());

        }
        System.out.println("------------------------------------------------------");
        // start send file
        Split.splitFile();
        Split.send(nodes);

        // System.out.println("Server starting...");
        // DaemonImpl node_1 = new DaemonImpl(10010, 11000);
        // node_1.startRegistry();
        // System.out.println("here");
        // node_1.registerObject("Daemon 1", node_1);
        // node_1.initSocket();
        // // Server start and listen request from Client.
        // System.out.println("Server started!");

        // DaemonService node1_ = null;
        // node1_ = serviceLookup("localhost", 10010, "Daemon 1");
        // System.out.println(node1_.nodePing());

        // System.out.println("Server starting...");
        // DaemonImpl node_2 = new DaemonImpl(10011, 12000);
        // node_2.startRegistry();
        // System.out.println("here");
        // node_2.registerObject("Daemon 2", node_2);
        // // Server start and listen request from Client.
        // System.out.println("Server started!");

        // System.out.println("Server starting...");
        // DaemonImpl node_3 = new DaemonImpl(10012, 13000);
        // node_3.startRegistry();
        // System.out.println("here");
        // node_3.registerObject("Daemon 3", node_3);
        // // Server start and listen request from Client.
        // System.out.println("Server started!");

        // DaemonService node2_ = null;
        // node2_ = serviceLookup("localhost", 10011, "Daemon 2");
        // System.out.println(node2_.nodePing());

        // DaemonService node3_ = null;
        // node3_ = serviceLookup("localhost", 10010, "Daemon 1");
        // System.out.println(node3_.nodePing());
    }
}
