package server.service;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RMIService extends Remote {

    public String setClusterConfig(int numberOfNodes);
    public ArrayList<node> getNodesInfo();

    public String CreateCalculation(String scriptPath) throws Exception;
    public Boolean sendFile(List<String> files) throws IOException, InterruptedException;


}
