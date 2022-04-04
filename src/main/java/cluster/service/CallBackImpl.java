





public class CallBackImpl extends UnicastRemoteObject implements CallBack { 
    int nbnode; 
    public CallBackImpl(int n) throws RemoteException { 
        nbnode = n; 
    } 
    public synchronized void completed() throws RemoteException { 
        notify();
    }
    public synchronized void waitforall() { 
        for (i=0;i<nbnode;i++) wait();
    } 
}