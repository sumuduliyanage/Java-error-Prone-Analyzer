import java.io.*;

public class Data {
    public static void main(String[] args) {
        String line;

        try (BufferedReader inputData = new BufferedReader(new FileReader("mydata.txt"))) {
            while ((line = inputData.readLine()) != null) {
                // Do something with line
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
