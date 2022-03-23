import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class QuizApplication {
    private static String welcomeMessage = "User Quiz Calculator:";
    private static String menu = "Select one of the following options:\n" + "1. Import CSV file\n" + "2. Exit";
    private static String error = "Please enter a valid choice.";
    private static String exit = "Thank you for using the User Quiz Calculator.";
    private static String filedirectory = "Please enter the directory for the CSV file: ";
    private static String filename = "Please enter the CSV file name: ";
    private static String directory;
    private static String name;
    private static boolean exitProgram = true;
    private static String choice;
    private static ArrayList<ArrayList<Quiz>> quiz2D;

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println(welcomeMessage);
        while (exitProgram) {
            System.out.println(menu);
            choice = scan.nextLine();
            if (choice.equals("1")) {
                System.out.println(filedirectory);
                directory = scan.nextLine();
                System.out.println(filename);
                name = scan.nextLine();
                quiz2D = Data.readInputFile(directory, name);
                calculate(quiz2D);
                System.out.println("Results exported for student Check file!\n");
            } else if (choice.equals("2")) {
                System.out.println(exit);
                return;
            } else {
                System.out.println(error + "\n");
            }
        }
    }

    public static void calculate(ArrayList<ArrayList<Quiz>> quiz2D) throws FileNotFoundException {
        int index = 1;
        for (ArrayList<Quiz> quiz : quiz2D) {
            String courseName = quiz.get(0).getCourseName();
            String studentName = quiz.get(0).getStudentName();
            String studentEmail = quiz.get(0).getStudentEmail();
            float finalGrade;
            String notes = "";
            HashMap<String, Integer> strictlyQuizzes = new HashMap<>();
            int totalTasks = 0;
            int gradedTasks = 0;
            for (Quiz quizlet : quiz) {
                if (!quizlet.isTask() && !quizlet.isExam()) {
                    if (quizlet.isUntitled()) {
                        if (strictlyQuizzes.containsKey(quizlet.getFirstQuestion())) {
                            int i = strictlyQuizzes.get(quizlet.getFirstQuestion());
                            i++;
                            strictlyQuizzes.put(quizlet.getFirstQuestion(), i);
                        } else {
                            strictlyQuizzes.put(quizlet.getFirstQuestion(), 1);
                        }
                    } else {
                        if (strictlyQuizzes.containsKey(quizlet.getQuizName())) {
                            int i = strictlyQuizzes.get(quizlet.getQuizName());
                            i++;
                            strictlyQuizzes.put(quizlet.getQuizName(), i);
                        } else {
                            strictlyQuizzes.put(quizlet.getQuizName(), 1);
                        }
                    }
                }
            }
            for (Quiz quizlet : quiz) {
                if (quizlet.isTask()) {
                    totalTasks++;
                    if (quizlet.getCorrectQuestions() != Integer.MAX_VALUE) {
                        gradedTasks++;
                    }
                }
            }
            int ungradedTasks = totalTasks - gradedTasks;
            int sum = 0;
            for (int f : strictlyQuizzes.values()) {
                sum += f;
            }
            boolean twoRetakePenalty = (sum / (float) strictlyQuizzes.size()) > 1.1;
            boolean secondaryGrading = gradedTasks > 0;

            float examScore = 0;
            int count = 0;
            for (Quiz quizlet : quiz) {
                if (quizlet.isExam()) {
                    examScore = quizlet.getGrade();
                    count++;
                }
            }
            boolean tertiaryGrading = count == 0;

            float quizScore = 0;
            for (String str : strictlyQuizzes.keySet()) {
                Quiz main = null;
                for (Quiz quizlet : quiz) {
                    if (quizlet.isUntitled()) {
                        if (str.equals(quizlet.getFirstQuestion())) {
                            main = quizlet;
                            break;
                        }
                    } else {
                        if (str.equals(quizlet.getQuizName())) {
                            main = quizlet;
                            break;
                        }
                    }
                }
                if (twoRetakePenalty) {
                    if (strictlyQuizzes.get(str) >= 2) {
                        quizScore += 60;
                    } else {
                        quizScore += main.getGrade();
                    }
                } else {
                    if (strictlyQuizzes.get(str) >= 3) {
                        quizScore += 60;
                    } else if (strictlyQuizzes.get(str) == 2) {
                        if (main.getGrade() <= 80) {
                            quizScore += main.getGrade();
                        } else {
                            quizScore += 80;
                        }
                    } else {
                        quizScore += main.getGrade();
                    }
                }
            }
            quizScore = quizScore / strictlyQuizzes.size();
            float taskScore = 0;
            if (secondaryGrading && !tertiaryGrading) {
                for (Quiz quizlet : quiz) {
                    if (quizlet.isTask()) {
                        if (quizlet.getCorrectQuestions() != Integer.MAX_VALUE) {
                            taskScore += quizlet.getGrade();
                        }
                    }
                }
                taskScore = taskScore / (float) gradedTasks;
                finalGrade = 0.6f * examScore + 0.2f * quizScore + 0.2f * taskScore;
            } else if (!secondaryGrading && !tertiaryGrading) {
                finalGrade = 0.6f * examScore + 0.4f * quizScore;
            } else if (secondaryGrading) {
                for (Quiz quizlet : quiz) {
                    if (quizlet.isTask()) {
                        if (quizlet.getCorrectQuestions() != Integer.MAX_VALUE) {
                            taskScore += quizlet.getGrade();
                        }
                    }
                }
                taskScore = taskScore / (float) gradedTasks;
                finalGrade = 0.4f * quizScore + 0.6f * taskScore;
            } else {
                if (ungradedTasks > 0) {
                    finalGrade = 0.4f * quizScore;
                    float updatedGrade = finalGrade + 0.6f * 70;
                    notes += "Updated Grade: " + String.format("%.2f", updatedGrade) + " (temporary 70% in tasks for ungraded tasks only courses). ";
                } else {
                    finalGrade = quizScore;
                }
            }
            if (ungradedTasks > 0) {
                notes += "Note: Student has " + ungradedTasks + " ungraded tasks.";
            }
            Data.writeData(courseName, index, studentName, studentEmail, finalGrade, notes);
            index++;
        }
    }
}