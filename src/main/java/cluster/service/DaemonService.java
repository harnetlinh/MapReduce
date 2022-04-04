

public interface DaemonService extends Remote {
    
    public void call(Map m, String blockin, String blockout, CallBack cb) throws RemoteException;
    
    public String uploadData();
    public String downloadData();
   

}

