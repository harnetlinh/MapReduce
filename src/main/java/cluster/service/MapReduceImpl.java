package cluster.service;

import java.util.*;
import java.io.*;
public interface MapReduceImpl extends Serializable {
	public void executeMap(String blockin, String blockout);
	public void executeReduce(Collection<String> blocks, String finalresults);
}
