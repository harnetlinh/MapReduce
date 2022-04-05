

import java.io.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Calculation extends AbstractCalculation implements java.io.Serializable {
//    private RMIServiceImpl service;
    public static double a = 7d;
    public static double b = 8d;
//    public static int m = 100;
    public static String[] array = new String[5];
    public static String[] f;
    public static double[] numbers;
    public static ArrayList<Double> dataLines = new ArrayList<>();
//    AbstractCalculation calculationJob;

    public static String inputData = null;
    public static String outputData = null;

    public Calculation(String input, String output) throws RemoteException {
        super();
        inputData = input;
        outputData = output;
//        this.service = new RMIServiceImpl();

    }

    @Override
    public String getData(){

        return "validated Data";
    }

    @Override
    public void Run() throws IOException {
        try {
            System.out.print("Check /n");
            System.out.print("Check" + inputData);
            System.out.print("Check /n");
            double result = plusArray();
            System.out.println("Server done!");
            System.out.println(result);
            dataLines.add(
                    result);

//            .addAll(Arrays.asList(question2));
            String savedResult = WriteCSV(dataLines);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double plus2Number() {
        double c = a+b;
//        AppManager.count++;
        return c;
    }
    public static double minus2Number() {
        double c = a-b;
//        AppManager.count++;
        return c;
    }
    public static double multiple2Number() {
//        AppManager.count++;
        double c = a*b;
        return c;
    }
    public static double divide2Number() throws Exception {
//        AppManager.count++;
        double c = a/b;
        if (b == 0) {
            throw new Exception("Divided by zero!");
        } else {
            return c;
        }
    }
    public static double[] array() throws IOException {
        ReadCSV();
        f = array;
        numbers= Arrays.stream(f).mapToDouble(Double::valueOf).toArray();
        return numbers;
    }
    public static double plusArray() throws IOException {
        double e = 0;
        numbers = array();
        for (int i = 0; i<numbers.length; i++){
            e += numbers[i];
        }
        return e;
    }

    public static String server_ping() throws Exception{
        String c = "hello from the server side";
        return c;
    }

    // Read Data
    public static String ReadCSV() throws IOException {
        String path = inputData;
        System.out.print("Check" + path);
        String line = "";
        {
            try {
                BufferedReader read = new BufferedReader(new FileReader(path));
                for (line = read.readLine(); line != null; line = read.readLine()) {
                    array = line.split(",");
                }
                read.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return array[0];
    }
    public static String WriteCSV(ArrayList<Double> result) throws IOException {
        String path = outputData;
        arraySave(path, result);
        return "Done";
    }
    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }


    public static void arraySave(String CSV_FILE_NAME,ArrayList<Double> data) throws IOException {
        File csvOutputFile = new File(CSV_FILE_NAME);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            int datList = data.size();
            System.out.println("Saving ! ");
            for (int i = 0; i < datList; i++) {
                pw.write(String.valueOf(data.get(i)));
                System.out.println(String.valueOf(data.get(i)));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


//    abstract static class AbstractCalculation
//    {
//        public abstract String getData();
//
//        public abstract void Run() throws IOException;
//
//    }


}
