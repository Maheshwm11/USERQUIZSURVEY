package src.src;

import java.io.IOException;
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
    private static float quizDistribution;
    private static float taskDistribution;
    private static float finalDistribution;
    private static boolean exitProgram = true;
    private static String choice;
    private static ArrayList<src.src.Quiz> quiz;

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println(welcomeMessage);
        while (exitProgram) {
            System.out.println(menu);
            choice = scan.nextLine();
            if (choice.equals("1")) {
                break;
            } else if (choice.equals("2")) {
                System.out.println(exit);
                return;
            } else {
                System.out.println(error + "\n");
            }
        }
        System.out.print(filedirectory);
        directory = scan.nextLine();
        System.out.print(filename);
        name = scan.nextLine();
        quiz = src.src.Data.readInputFile("/Users/madhav/IdeaProjects/USERQUIZSURVEY/Database", "user2.csv");
        System.out.println(quiz.size());
        System.out.println(quiz.get(2).getCourseName());
    }
}