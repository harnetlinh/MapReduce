package MapReduce;
import origin.Main;
public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        try {
            Main.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Main.main(args);
    }
}
