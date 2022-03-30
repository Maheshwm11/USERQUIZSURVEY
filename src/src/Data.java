import java.io.*;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

public class Data {
    private static String dirName = FileSystems.getDefault().getPath("").toAbsolutePath() + "/Output";

    public static ArrayList<ArrayList<Quiz>> readInputFile(String name) throws Exception{
        ArrayList<Quiz> quizzes = new ArrayList<>();
        File f = new File(name);
        CsvParserSimple reader = new CsvParserSimple();
            List<String[]> result = reader.readFile(f, 1);
            for (String[] array : result) {
                boolean isTask = false;
                if (array[6].equals("")) {
                    array[6] = String.valueOf(Integer.MAX_VALUE);
                    array[7] = String.valueOf(Integer.MAX_VALUE);
                }
                if (array[1].toLowerCase().contains("task") || array[6].equals(String.valueOf(Integer.MAX_VALUE))) {
                    isTask = true;
                }
                boolean isExam = false;
                if (array[1].toLowerCase().contains("exam") && Integer.parseInt(array[5]) > 40) {
                    isExam = true;
                }
                boolean isUntitled = false;
                if (array[1].toLowerCase().contains("untitled")) {
                    isUntitled = true;
                }
                Quiz temp = new Quiz(array[0], array[1], array[2], array[3], array[4], Integer.parseInt(array[5]),
                        Integer.parseInt(array[6]), Integer.parseInt(array[7]), array[8], isExam, isTask, isUntitled);
                quizzes.add(temp);
            }
        return read2D(quizzes);
    }

    public static ArrayList<ArrayList<Quiz>> read2D(ArrayList<Quiz> quizzes) {
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<ArrayList<Quiz>> quizzes2D = new ArrayList<>();
        for (Quiz quiz : quizzes) {
            temp.add(quiz.getCourseName());
        }
        HashSet<String> unique = new HashSet<>(temp);
        ArrayList<String> tempUnique = new ArrayList<>(unique);
        for (int i = 0; i < tempUnique.size(); i++) {
            ArrayList<Quiz> temp1 = new ArrayList<>();
            for (Quiz quiz : quizzes) {
                if (quiz.getCourseName().equals(tempUnique.get(i))) {
                    temp1.add(quiz);
                }
            }
            quizzes2D.add(temp1);
        }
        return quizzes2D;
    }

    public static boolean writeData(String course, int index, String studentName, String studentEmail, float finalGrade,
            String notes)
            throws FileNotFoundException {
        File dir = new File(dirName);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File f = new File(dir, studentName + " (" + studentEmail + ").tsv");
        FileOutputStream fos = new FileOutputStream(f, true);
        try (PrintWriter pw = new PrintWriter(fos)) {
            if (notes.equals("")) {
                pw.println("Course "+ index + " : " + course + "\t" + "Student Name: " + studentName + "\t" + "Email: "
                        + studentEmail + "\t" + "Final Grade: " + Math.round(finalGrade * 100.0) / 100.0 + "\t" + "\n");
            } else {
                pw.println("Course "+ index + " : " + course + "\t" + "Student Name: " + studentName + "\t" + "Email: "
                        + studentEmail + "\t" + "Final Grade: " + Math.round(finalGrade * 100.0) / 100.0 + "\t"
                         + notes + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}