# Student Grading Application

The Student Grading Application is a Java program that reads and processes CSV files containing student data for several courses comprising of quiz, exams and more information. It calculates the final grades for the student based on their quiz and exam scores, and exports the results for each course to a single TSV file.

## Features

The Application has the following features:

- Imports student data from a CSV file.
- Calculates final grades for the student based on their quiz and exam scores for each course.
- Exports the results to a TSV file.

Note: The grading system in the calculate function depends on a lot of external factors that are hard coded for now. If you want the application to be modular, you will have to update the constants.

## Prerequisites

Before running the Application, you need to have the following:

- Java Development Kit (JDK) installed on your machine.
- A CSV file containing student data in the correct format. The CSV file should have the following columns:
  - Course Name: The name of the course.
  - Survery/Quiz name: The name of the quiz.
  - Student Name: The name of the student (will be same for the entire file).
  - Student Email: The email of the student (same for the whole file).
  - Date completed: Completion timestamp
  - Total number of questions
  - Total correct
  - All questions (optional)

## Usage

You can use the application by using the `finalgrade.jar` OR you can follow these steps:

1. Clone the repository to your local machine.
2. Open the QuizApplication.java file in your preferred Java IDE.
3. Uncomment the `main` method by removing the multi-line comment `/* ... */`.
4. Compile and run the QuizApplication.java file to start the Quiz Application.
5. Enter the directory and filename of the CSV file containing the student data when prompted.
6. The Application will process the student data and calculate the final grades for each course the student has taken.
7. The results will be exported to a TSV file named "student name (student email).tsv" in the same directory as the input file.
8. You can view the final grades for each course in the exported TSV file.
