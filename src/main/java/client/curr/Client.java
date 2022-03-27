package OriginalNode;

// import client.model.ClientConfig;
// import server.service.RMIService;

import java.io.*;
import java.rmi.registry.Registry;

public class Client {

    private static Registry registry;

    static ClientConfig getClientConfig(String host, int port){
        ClientConfig clientConfig = new ClientConfig(host, port);
        return clientConfig;
    }

    static RMIService getService(ClientConfig clientConfig) throws Exception
    {
        RMIService service = clientConfig.serviceLookup();
        return service;
    }

}