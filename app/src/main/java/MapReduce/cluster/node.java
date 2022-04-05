package cluster;

import java.net.ServerSocket;

public class node {
    private int portRMI;
    private int portSocket;
    private String ip = "localhost";
    private ServerSocket serverSocket = null;
    private String fileName = "";

    public int getPortRMI() {
        return portRMI;
    }

    public int getPortSocket() {
        return portSocket;
    }

    public String getIp() {
        return ip;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public String getFileName() {
        return fileName;
    }

    public void setPortRMI(int port) {
        this.portRMI = port;
    }

    public void setPortSocket(int port_) {
        this.portSocket = port_;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}