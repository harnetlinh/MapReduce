package cluster;
import cluster.node;
import cluster.nodesConfig;
import cluster.MapReduceService;

import java.util.*;
import java.io.*;

public class DaemonImpl extends UnicastRemoteObject implements DaemonService {
    private Node node ;

    public DaemonImpl(){
        //Construct a daemon instance with node
    }
    public void call(Map m, String blockin, String blockout, CallBack cb) throws RemoteException{
        // getting node
        // Callbacksetup
        // Call map exec of this node. Threading
        // waiting for finishing
        // Call reduce exec
    }
	
    
}
