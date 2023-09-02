import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;

class Question {
    private String questionText;
    private List<String> options;
    private int correctOptionIndex;

    public Question(String questionText, List<String> options, int correctOptionIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public boolean isCorrect(int selectedOptionIndex) {
        return selectedOptionIndex == correctOptionIndex;
    }
}

class Quiz {
    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;

    public Quiz(List<Question> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
    }

    public Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    public void submitAnswer(int selectedOptionIndex) {
        Question currentQuestion = getCurrentQuestion();
        if (currentQuestion.isCorrect(selectedOptionIndex)) {
            score++;
        }
        currentQuestionIndex++;
    }

    public boolean hasMoreQuestions() {
        return currentQuestionIndex < questions.size();
    }

    public int getScore() {
        return score;
    }
}

class QuizApp {
    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?",
                List.of("Berlin", "Paris", "Madrid", "Rome"), 1));
        

        Quiz quiz = new Quiz(questions);
        Scanner scanner = new Scanner(System.in);

        while (quiz.hasMoreQuestions()) {
            Question currentQuestion = quiz.getCurrentQuestion();
            System.out.println(currentQuestion.getQuestionText());
            List<String> options = currentQuestion.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            int selectedOption = scanner.nextInt() - 1;
            quiz.submitAnswer(selectedOption);
        }

        System.out.println("Quiz completed! Your score: " + quiz.getScore());
    }
}
