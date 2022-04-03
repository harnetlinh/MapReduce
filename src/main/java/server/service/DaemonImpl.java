package server;
import server.node;
import server.nodesConfig;

import java.util.*;
import java.io.*;

public class DaemonImpl extends UnicastRemoteObject implements DaemonService {
    private Node node ;

    public DaemonImpl(){
        //Construct a daemon instance with node
    }

	public void call();
    
}
