package origin;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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
import java.io.FileReader;

public class Split {

    public static void send(ArrayList<node> nodes) {
        try {
			// ArrayList<node> nodes = nodeConfig.getNodes();
			
            String working_Dir = System.getProperty("user.dir");
            
            String sourceFilePath = working_Dir + "\\server_storage\\result";
            // String destinationFilePath = working_Dir + "\\server_storage\\target";
            int count = 0;
			for (node n : nodes) {
                byte[] mybytearray = new byte[1024];                
				Socket s = new Socket("localhost", 11000);
				InputStream is = s.getInputStream();
				FileOutputStream fos = new FileOutputStream(sourceFilePath+"0.txt");
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                int bytesRead = is.read(mybytearray, 0, mybytearray.length);
				bos.write(mybytearray, 0, bytesRead);
				s.close();
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public static void splitFile(){
        //get the file path
        String working_Dir = System.getProperty("user.dir");

        BufferedReader br = new BufferedReader(new FileReader(working_Dir + "\\server_storage\\data.txt"));

        try {
            ArrayList<String> arrOfStr = new ArrayList<String>();
            String line = br.readLine();
            // read the file line by line
            while (line != null) {
                arrOfStr.add(line);
                System.out.println(line);
                line = br.readLine();
            }
            for (int i = 0; i < arrOfStr.size(); i++) {
                PrintWriter writer = new PrintWriter(working_Dir + "\\server_storage\\result" + i + ".txt", "UTF-8");
                writer.println(arrOfStr.get(i));
                writer.close();
            }
            System.out.println("Done");
        } finally {
            br.close();
        }
    }
    }

}
