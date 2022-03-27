
import java.io.*;

public class main {
    public static void main(String[] args) throws IOException {
        System.out.println("Program Arguments:");
        for (int i = 1; i < args.length; i++)
            System.out.print(" " + args[i]);

        Calculation calculationJob = new Calculation(args[0], args[1]);
        calculationJob.Run();
    }
}