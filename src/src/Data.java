import com.mkyong.io.csv.CsvParserSimple;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Data {
    public static ArrayList<Quiz> readInputFile(String directory, String name) {
        File dir = new File(directory);
        ArrayList<Quiz> quizzes = new ArrayList<>();
        File f = new File(dir, name);
        CsvParserSimple reader = new CsvParserSimple();
        try {
            List<String[]> result = reader.readFile(f, 1);
            for (String[] array : result) {
                Boolean isTask = false;
                if (array[6].equals("")) {
                    array[6] = String.valueOf(Integer.MAX_VALUE);
                    array[7] = String.valueOf(Integer.MAX_VALUE);
                }
                if (array[1].toLowerCase().contains("task") || array[6].equals(String.valueOf(Integer.MAX_VALUE))) {
                    isTask = true;
                }
                Boolean isExam = false;
                if (!array[1].toLowerCase().contains("quiz") && Integer.parseInt(array[5]) > 10) {
                    isExam = true;
                }
                Boolean isUntitled = false;
                if (array[1].toLowerCase().contains("untitled")) {
                    isUntitled = true;
                }
                Quiz temp = new Quiz(array[0], array[1], array[2], array[3], array[4], Integer.parseInt(array[5]),
                        Integer.parseInt(array[6]), Integer.parseInt(array[7]), array[8], isExam, isTask, isUntitled);
                quizzes.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quizzes;
    }

    public static boolean writeData(String studentName, String studentEmail, float finalGrade, String notes)
            throws FileNotFoundException {
        File f = new File("result.tsv");
        FileOutputStream fos = new FileOutputStream(f, false);
        try (PrintWriter pw = new PrintWriter(fos)) {
            pw.println("Student Name: " + studentName + "\t" + "Email: " + studentEmail + "\t" + "Final Grade: " + finalGrade + "\t" + "Notes: " + notes);
            System.out.println("Results Exported!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}