package OriginalNode;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientConfig {
    private String host;
    private int port;

    public ClientConfig() {

    }

    public ClientConfig(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public RMIService serviceLookup() throws NotBoundException, RemoteException {
        Registry registry = LocateRegistry.getRegistry(host, port);
        RMIService service = (RMIService) registry
                .lookup(RMIService.class.getSimpleName());
        return service;
    }
}
