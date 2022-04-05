package origin;

import cluster.nodeConfig;
import cluster.service.CallBackImpl;
import cluster.service.CallBackService;
import cluster.service.DaemonImpl;
import cluster.service.DaemonService;
import cluster.node;

import java.io.File;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


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

    public static void main(String args[])
            throws IOException, AlreadyBoundException, NotBoundException, InterruptedException {
        // register object
        int numberOfNodes = 3;
        nodeConfig nodeConfig = new nodeConfig(numberOfNodes);
        ArrayList<node> nodes = nodeConfig.getNodes();
        DaemonService _node_service = null;

        //Create nodes RMI and connect to all of them
        for (node node : nodes) {
            DaemonImpl _daemon = new DaemonImpl(node.getPortRMI(), node.getPortSocket());
            _daemon.startRegistry();
            _daemon.registerObject("Daemon-" + node.getPortRMI(), _daemon);
            node.setServerSocket(_daemon.initSocket());
            System.out.println("DONE INIT SOCKET");

            //Ping test
            _node_service = serviceLookup("localhost", node.getPortRMI(), "Daemon-" + node.getPortRMI());
            System.out.println(_node_service.nodePing());

        }
        System.out.println("------------------------------------------------------");

        Split.splitFile(nodes);
        // start send file
        for (node node : nodes) {
            Split.send(node);
        }

        CallBackService cb = new CallBackImpl(numberOfNodes);
        Map m = null;
        String blockin = "";
        String blockout = "";
        //TODO: adding the right blocking, block out each node
        int i = 0;
        for (node node : nodes) {
            blockin = node.getFileName();
            blockout = node.getFilePath() + File.separator+"result"+i+".txt";
            node.setBlockFileName(blockout);
            _node_service = serviceLookup("localhost", node.getPortRMI(),
                    "Daemon-" + node.getPortRMI());
            i++;
            _node_service.call(m, blockin, blockout, cb);

        }
        ((CallBackImpl) cb).waitforall();

        System.out.println("wait finish");


        for (node node : nodes) {
            Launch.download(node);
        }
        //TODO: Final reduce
        Collection<String> blocks = new ArrayList<String>();
        //TODO: download files to blocks
        for(node node : nodes)
        {
            blocks.add(node.getBlockFileName());
        }
        Launch.finalReduce(blocks);

        //Launch call here
//        Launch.execNodes(nodes);
    }
}
