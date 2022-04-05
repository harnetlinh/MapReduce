package cluster;

//import cluster.service.RMIService;
import cluster.service.DaemonService;



import java.util.ArrayList;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class nodeConfig {
    private String host;
    private int port;
    //list of port RMI, port socket
    private int numberOfNodes = 2; //default number
    static ArrayList<node> cluster = new ArrayList<node>();
    ArrayList<Integer> allPortsUsed = new ArrayList<Integer>();
    public nodeConfig(){

    }

    public nodeConfig(int numberOfNodes_) {
        this.numberOfNodes = numberOfNodes_;
        // init all configs
        generateClusterConfig(this.numberOfNodes);

    }

    public void generateClusterConfig(int numberOfNode_){
        int port_RMI = 10010;
        int port_Socket = 20010;
        for (int i = 0; i < numberOfNode_; i++) { 
            port_RMI +=i;
            port_Socket +=i;
            if (this.allPortsUsed.contains(port_RMI) || this.allPortsUsed.contains(port_Socket)) {
                break;
            }    
            
            node node_ = new node();
            node_.setPortRMI(port_RMI);
            node_.setPortSocket(port_Socket);
            this.allPortsUsed.add(port_RMI);
            this.allPortsUsed.add(port_Socket);
            

            cluster.add(node_);
          }
    }
    public ArrayList<node> getNodes(){
        return cluster;    
    }
    //Config port,host
    public void applyDaemonConfig (){

    }
}
