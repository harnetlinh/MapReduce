package cluster.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

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
    public static void  main (String args[]) throws InterruptedException, RemoteException {
        CallBackService cb = new CallBackImpl(2);

        DaemonService dae = new DaemonImpl(10010, 20010);
        Map m = null;
        String blockin = "asd.txt";
        String blockout = "null";
        dae.call( m,  blockin,  blockout,  cb);
        System.out.println("call first node");

        DaemonService dae2 = new DaemonImpl(10011, 20011);
        dae2.call( m,  blockin,  blockout,  cb);
        System.out.println("call second node");


        ((CallBackImpl) cb).waitforall();
        System.out.println("wait finish");

    }
}