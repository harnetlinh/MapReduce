package server;

import org.junit.jupiter.api.Test;
import server.service.RMIServiceImpl;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RunShellTest {


    @Test
    void RunShellCommandFromJavaTest_() throws NotBoundException, IOException, InterruptedException {

        RunShellCommandFromJava.exec(

                "java -cp ./server_storage/ main ./server_storage/Data.csv ./server_storage/result_server.csv");
//        assertEquals(true
//                ,service.sendFile(files)
//        );

    }
}

