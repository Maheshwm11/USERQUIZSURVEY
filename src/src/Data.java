package src.src;

import java.io.*;
import java.util.ArrayList;

public class Data {
    public static ArrayList<Quiz> readInputFile(String directory, String name) {
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

    public static boolean writeData(String studentName, String studentEmail, float finalGrade, String notes)
            throws FileNotFoundException {
        File f = new File("result.tsv");
        FileOutputStream fos = new FileOutputStream(f, false);
        try (PrintWriter pw = new PrintWriter(fos)) {
            pw.println("Student Name: "+ studentName + "\t" + "Email: " + studentEmail + "\t" + "Final Grade: " + finalGrade + "\t" + "Notes: " + notes);
            System.out.println("Results Exported!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}