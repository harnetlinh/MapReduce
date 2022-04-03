package server;

import server.service.RMIService;
import server.node;


import java.util.ArrayList;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class nodeConfig {
    private String host;
    private int port;
    //list of port RMI, port socket
    private int numberOfNodes = 3; //default number
    ArrayList<node> cluster = new ArrayList<node>();
    ArrayList<Integer> allPortsUsed;
    public nodeConfig(){

    }

    public nodeConfig(int numberOfNodes_) {
        this.numberOfNodes = numberOfNodes_;
        // init all configs
        generateClusterConfig(this.numberOfNodes);

    }

    public generateClusterConfig(int numberOfNode_){
        port_RMI = 100010;
        port_Socket = 200010;
        for (int i = 0; i < numberOfNode_; i++) { 
            port_RMI +=i;
            port_Socket +=i;
            if (this.allPortsUsed.contains(port_RMI) || this.allPortsUsed.contains(port_Socket)){
                break;
            }    
            
            Node node_ = new Node();
            node_.setPortRMI(port_RMI);
            node_.setPortSocket(port_Socket);
            this.allPortsUsed.add(port_RMI);
            this.allPortsUsed.add(port_Socket);
            

            cluster.add(node_);
          }
    }
    public ArrayList<node> getNodes(){
        return this.cluster;    
    }
    //Config port,host
}
