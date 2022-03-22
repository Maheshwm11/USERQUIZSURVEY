import java.io.*;
import java.util.ArrayList;

public class Data {
    public ArrayList<Quiz> readInputFile(String directory, String name) {
        File dir = new File(directory);
        ArrayList<Quiz> quizzes = new ArrayList<>();
        File f = new File(dir, name);
        try (BufferedReader bfr = new BufferedReader(new FileReader(f))) {
            String line = bfr.readLine();
            while (line != null) {
                String[] array = line.split(",");
                Quiz temp = new Quiz(array[0], array[1], array[2], array[3], array[4], Integer.parseInt(array[5]),
                        Integer.parseInt(array[6]), Integer.parseInt(array[7]), array[8], false, false);
                quizzes.add(temp);
                line = bfr.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return quizzes;
    }

}

