





public class node{
    private int portRMI;
    private int portSocket;
    private String ip = "localhost";

    public int getPortRMI(){
        return portRMI;
    }
    public int getPortSocket(){
        return portSocket;
    }
    public String getIp(){
        return ip;
    }

    public void setPortRMI(int port){
        this.portRMI = port; 
    }
    public void setPortSocket(int port_){
        this.portSocket = port_; 
    }

}