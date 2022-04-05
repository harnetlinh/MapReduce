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
    public static void execNodes(ArrayList<node> nodes) throws NotBoundException, RemoteException {
        DaemonService _node_service = null;



        for (node node : nodes) {
            //Ping test
            _node_service = serviceLookup("localhost", node.getPortRMI(), "Daemon Service " + node.getPortRMI());
            System.out.println(_node_service.nodePing());

            //Callback create
            CallBackService cb = null;
            Map m = null;
            String blockin = "asd";
            String blockout = "null";
            try {

                cb = (CallBackService) new CallBackImpl(2);



                _node_service.call( m,  blockin,  blockout,  cb);
                ((CallBackImpl) cb).waitforall();

            } catch (RemoteException ex) {
                ex.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            //TODO:result download here

        }

    }
    public static DaemonService serviceLookup(String host, int port, String name)
            throws NotBoundException, RemoteException {
        Registry registry = LocateRegistry.getRegistry(host, port);
        DaemonService service = (DaemonService) registry
                .lookup(name);
        return service;
    }

}

