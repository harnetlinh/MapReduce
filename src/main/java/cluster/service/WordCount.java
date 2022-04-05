package cluster;

import cluster.service.MapReduceService;

import java.util.*;
import java.io.*;

public class WordCount implements MapReduceService extends Runnable  {

     public static final String SEPARATOR = " - ";
     public String blockin_ ;
     public String blockout_ ;
     public CallBackService cb_;
     public WordCount(String blockin, String blockout, CallBackService cb )throws RemoteException {
        super();
        //Construct a daemon instance with node
        this.blockin_ = blockin;
        this.blockout_ = blockout;
        this.cb_ = cb;
    }

     @Override
     public void executeMap(String blockin, String blockout) {
          // read from blockin, compute count table, write to blockout		
          HashMap<String,Integer> hm = new HashMap<String,Integer>();
          try {
               BufferedReader br= new BufferedReader(
                                        new InputStreamReader(new FileInputStream(blockin)));
               BufferedWriter bw = new BufferedWriter(
                                        new OutputStreamWriter(new FileOutputStream(blockout)));
               String line;
               while ((line=br.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(line);
                    while (st.hasMoreTokens()) {
                         String tok = st.nextToken();
                         if (hm.containsKey(tok)) hm.put(tok, hm.get(tok)+1);
                         else hm.put(tok, 1);
                    }
               }
               for (String k : hm.keySet()) {
                    bw.write(k+SEPARATOR+hm.get(k).toString());
                    bw.newLine();
               }
               br.close();
               bw.close();
          } catch (Exception e) {
               e.printStackTrace();
          }
     }

     @Override
     public void executeReduce(Collection<String> blocks, String finalresults) {
          // read all files in blocks, merge and write to finalresults
          HashMap<String,Integer> hm = new HashMap<String,Integer>();
          try {
               for (String block : blocks) {
                    BufferedReader br= new BufferedReader(
                                                            new InputStreamReader(new FileInputStream(block)));
                    String line;
                    while ((line=br.readLine()) != null) {
                         String kv[] = line.split(SEPARATOR);
                         String k = kv[0];
                         int v = Integer.parseInt(kv[1]);
                         if (hm.containsKey(k)) hm.put(k, hm.get(k)+v);
                         else hm.put(k, v);
                    }
                    br.close();
               }
               BufferedWriter bw = new BufferedWriter(
                                                    new OutputStreamWriter(new FileOutputStream(finalresults)));
               for (String k : hm.keySet()) {
                    bw.write(k+SEPARATOR+hm.get(k).toString());
                    bw.newLine();
               }
               bw.close();
          } catch (Exception e) {
               e.printStackTrace();
          }
     }

     public void run()
    {
        try {
            // Displaying the thread that is running
            System.out.println(
                "Thread " + Thread.currentThread().getId()
                + " is running");
        }
        executeMap(this.blockin_, this.blockout_);
        cb_.completed();
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }

     // public static void main(String[] args) {
     //           WordCount wc = new WordCount();
     // /*
     //      wc.executeMap("data.txt", "result.txt");	
     // */
     //      Collection<String> blocks = new ArrayList<String>();
     //      blocks.add("result1.txt");
     //      blocks.add("result2.txt");
     //      wc.executeReduce(blocks, "finalresult.txt");
       
     // }
}
