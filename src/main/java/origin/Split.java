package origin;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
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
    // public static final String HOST = "127.0.0.1";
	// public static final Integer POST = 4444;
	public static final Integer SIZE_PER_READING = 64 * 1024;

    public static void send(ArrayList<node> nodes) {
        try {
			// ArrayList<node> nodes = nodeConfig.getNodes();
			
            String working_Dir = System.getProperty("user.dir");
            
            String sourceFilePath = working_Dir + "\\server_storage\\result";
            // String destinationFilePath = working_Dir + "\\server_storage\\target";
            int count = 0;
			for (node n : nodes) {
                Socket socket = null;
                int DataSend;
		        byte[] bytes = new byte[SIZE_PER_READING];
                socket = new Socket(n.getIp(), n.getPortSocket());
                File file = new File(sourceFilePath+count+".txt");
                InputStream inputStream = new FileInputStream(file);
                OutputStream outputStream = socket.getOutputStream();
                while ((DataSend = inputStream.read(bytes)) > 0) {
                    outputStream.write(bytes, 0, DataSend);
                }
				inputStream.close();
                outputStream.close();
                socket.close();
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public static void splitFile() throws IOException {
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

