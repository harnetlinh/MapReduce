package cluster.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CallBackService extends Remote {
    void completed() throws RemoteException;


}