package src.src;

public class Quiz {
    private String courseName;
    private String quizName;
    private String studentName;
    private String studentEmail;
    private String timestamp;
    private int totalQuestions;
    private int correctQuestions;
    private float grade;
    private String firstQuestion;
    private boolean isExam;
    private boolean isTask;

    public Quiz(String courseName, String quizName, String studentName, String studentEmail,
                String timestamp, int totalQuestions, int correctQuestions, float grade,
                String firstQuestion, boolean isExam, boolean isTask) {
        this.courseName = courseName;
        this.quizName = quizName;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.timestamp = timestamp;
        this.totalQuestions = totalQuestions;
        this.correctQuestions = correctQuestions;
        this.grade = grade;
        this.firstQuestion = firstQuestion;
        this.isExam = isExam;
        this.isTask = isTask;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getCorrectQuestions() {
        return correctQuestions;
    }

    public void setCorrectQuestions(int correctQuestions) {
        this.correctQuestions = correctQuestions;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String getFirstQuestion() {
        return firstQuestion;
    }

    public void setFirstQuestion(String firstQuestion) {
        this.firstQuestion = firstQuestion;
    }

    public boolean isExam() {
        return isExam;
    }

    public void setExam(boolean exam) {
        isExam = exam;
    }

    public boolean isTask() {
        return isTask;
    }

    public void setTask(boolean task) {
        isTask = task;
    }
}