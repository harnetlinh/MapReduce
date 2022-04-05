package cluster;
import java.io.File;
import java.net.ServerSocket;

public class node {
    private int portRMI;
    private int portSocket;
    private String ip = "localhost";
    private ServerSocket serverSocket = null;
    private String fileName = "";
    private String filePath = System.getProperty("user.dir");
    private String blockFileName = "";
    public node() {
        this.filePath = System.getProperty("user.dir") + File.separator + "server_storage";
    }
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

    public String getFilePath() {
        return filePath;
    }

    public String getBlockFileName() {
        return blockFileName;
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

    public void setPath(String path) {
        this.filePath = path;
    }

    public void setBlockFileName(String fileName) {
        this.blockFileName = fileName;
    }

}