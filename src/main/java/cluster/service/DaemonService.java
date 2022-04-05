package cluster.service;

import cluster.service.CallBackImpl;
import cluster.service.CallBackService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface DaemonService extends Remote {
    
    public void call(Map m, String blockin, String blockout, CallBackService cb) throws RemoteException;
    
    public String uploadData() throws RemoteException;
    public String downloadData() throws RemoteException;



    public int nodePing()throws RemoteException;
   

}

