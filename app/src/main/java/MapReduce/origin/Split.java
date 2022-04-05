package origin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import cluster.node;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

public class Split {
    public static final Integer SIZE_PER_READING = 64 * 1024;

    // send file to each node
    public static void send(node node) {
        try {
            String working_Dir = System.getProperty("user.dir");

            String sourceFilePath = working_Dir + File.separator + "server_storage" + File.separator + "result";
            int count = 0;
            Socket socket = null;
            int DataSend;
            byte[] bytes = new byte[SIZE_PER_READING];
            socket = new Socket(node.getIp(), node.getPortSocket());
            String fileName = node.getFileName();
            File file = new File(fileName);
            node.setFileName(fileName);
            System.out.println("fileName: " + node.getFileName());
            InputStream inputStream = new FileInputStream(file);
            OutputStream outputStream = socket.getOutputStream();
            while ((DataSend = inputStream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, DataSend);
            }
            inputStream.close();
            outputStream.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // split file
    public static void splitFile(ArrayList<node> nodes) throws IOException {
        // get the file path
        String working_Dir = System.getProperty("user.dir");

        BufferedReader br = new BufferedReader(
                new FileReader(working_Dir + File.separator + "server_storage" + File.separator + "data.txt"));

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
                String fileName = working_Dir + File.separator + "server_storage" + File.separator + "data" + i + ".txt";
                PrintWriter writer = new PrintWriter(fileName,"UTF-8");
                System.out.println(nodes.get(i).getIp());
                nodes.get(i).setFileName(fileName);
                writer.println(arrOfStr.get(i));
                writer.close();
            }
            System.out.println("Done");
        } finally {
            br.close();
        }
    }
}
