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

public class sendFileTest {
    RMIServiceImpl service = new RMIServiceImpl();

    public sendFileTest() throws RemoteException {
    }

    @Test
    void sendFile_() throws NotBoundException, IOException, InterruptedException {
        List<String> files = new ArrayList<String>();
        files = Arrays.asList("Calculation.class","./Calculation.ser","./main.class","./AbstractCalculation.class");

        assertEquals(true
                ,service.sendFile(files)
        );

    }
}
