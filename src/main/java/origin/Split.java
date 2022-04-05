package origin;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import cluster.node;
import cluster.nodeConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Split {
    public static void main(String[] args) throws IOException {
        send();
    }
    public static void send() {
        try {
			// ArrayList<node> nodes = nodeConfig.getNodes();
			
            String working_Dir = System.getProperty("user.dir");
            
            String sourceFilePath = working_Dir + "\\server_storage\\result";
            // String destinationFilePath = working_Dir + "\\server_storage\\target";
            int count = 0;
			// for (node n : nodes) {
                byte[] mybytearray = new byte[1024];                
				Socket s = new Socket("localhost", 11000);
				InputStream is = s.getInputStream();
				FileOutputStream fos = new FileOutputStream(sourceFilePath+"0.txt");
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                int bytesRead = is.read(mybytearray, 0, mybytearray.length);
				bos.write(mybytearray, 0, bytesRead);
				s.close();
			// }
					
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
