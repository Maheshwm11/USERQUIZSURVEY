# Quiz Application

The Quiz Application is a Java program that reads and processes CSV files containing quiz data. It calculates the final grades for students based on their quiz and exam scores, and exports the results to a CSV file.

## Features

The Quiz Application has the following features:

- Imports quiz data from a CSV file.
- Calculates final grades for students based on their quiz and exam scores.
- Exports the results to a CSV file.

Note: The grading system in the calcultae function depends on a lot of external factors that are hard coded for now. If you want the application to be modular, you will have to update the constants.

## Prerequisites

Before running the Quiz Application, you need to have the following:

- Java Development Kit (JDK) installed on your machine.
- A CSV file containing quiz data in the correct format. The CSV file should have the following columns:
  - Course Name: The name of the course.
  - Survery/Quiz name: The name of the quiz.
  - Student Name: The name of the student.
  - Student Email: The email of the student.
  - Date completed: Completion timestamp
  - Total number of questions
  - Total correct
  - All questions (optional)

## Usage

To use the Quiz Application, follow these steps:

1. Clone the repository to your local machine.
2. Open the QuizApplication.java file in your preferred Java IDE.
3. Uncomment the `main` method by removing the multi-line comment `/* ... */`.
4. Compile and run the QuizApplication.java file to start the Quiz Application.
5. Enter the directory and filename of the CSV file containing the quiz data when prompted.
6. The Quiz Application will process the quiz data and calculate the final grades for students.
7. The results will be exported to a CSV file named "student Check file.csv" in the same directory as the input file.
8. You can view the final grades in the exported CSV file.
