package cluster.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CallBackImpl extends UnicastRemoteObject implements CallBackService {
    int nbnode; 
    public CallBackImpl(int n) throws RemoteException {
        super();
        nbnode = n; 
    } 
    public synchronized void completed() throws RemoteException {
        notify();
    }
    public synchronized void waitforall() throws InterruptedException {

        for (int i=0; i<nbnode; i++) wait();
    } 
}