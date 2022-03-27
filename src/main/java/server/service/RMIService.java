package server.service;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RMIService extends Remote {

//    public double plus2Number() throws RemoteException;
//    public double minus2Number() throws RemoteException;
//    public double multiple2Number() throws RemoteException;
//    public double divide2Number() throws Exception;
//    public String server_ping() throws RemoteException, Exception;
//    public double plusArray() throws IOException, InterruptedException;
    public String CreateCalculation(String scriptPath) throws Exception;
    public Boolean sendFile(List<String> files) throws IOException, InterruptedException;
}
