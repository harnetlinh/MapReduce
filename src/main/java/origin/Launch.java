package origin;

import cluster.node;
import cluster.service.CallBackImpl;
import cluster.service.CallBackService;
import cluster.service.DaemonService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Map;

public class Launch {


    ArrayList<node> nodes ;
    public void execNodes(ArrayList<node> nodes) throws NotBoundException, RemoteException {
        DaemonService _node_service = null;



        for (node node : nodes) {
            //Ping test
            _node_service = serviceLookup("localhost", node.getPortRMI(), "Daemon Service " + node.getPortRMI());
            System.out.println(_node_service.nodePing());

            //Callback create
            CallBackService cb = null;
            try {

                cb = (CallBackService) new CallBackImpl(nodesNumber);
                ((CallBackImpl) cb).waitforall();

            } catch (RemoteException ex) {
                ex.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            _node_service.call(Map m, String blockin, String blockout, CallBackService cb);

            //Call on nodes

        }
        // wait all call back
    }
    public static DaemonService serviceLookup(String host, int port, String name)
            throws NotBoundException, RemoteException {
        Registry registry = LocateRegistry.getRegistry(host, port);
        DaemonService service = (DaemonService) registry
                .lookup(name);
        return service;
    }

}

