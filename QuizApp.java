public import java.util.*;

public class QuizApp {
    static class Question {
        String question;
        String[] options;
        char correctAnswer;

        public Question(String question, String[] options, char correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }

        void display() {
            System.out.println("\n" + question);
            for (int i = 0; i < options.length; i++) {
                System.out.println((char) ('A' + i) + ". " + options[i]);
            }
        }
    }

    static List<Question> questions = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static int score = 0;
    static List<String> summary = new ArrayList<>();

    public static void main(String[] args) {
        loadQuestions();

        System.out.println("Welcome to the Quiz!");
        System.out.println("You have 10 seconds to answer each question.\n");

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            q.display();

            TimerTask task = new TimerTask() {
                public void run() {
                    System.out.println("\nTime's up! Moving to next question...");
                    scanner.nextLine(); // clear input
                }
            };

            Timer timer = new Timer();
            timer.schedule(task, 10000); // 10 seconds

            System.out.print("Your answer (A/B/C/D): ");
            String input = scanner.nextLine().toUpperCase();
            timer.cancel();

            if (!input.matches("[A-D]")) {
                summary.add("Q" + (i + 1) + ": No Answer (Time Out)");
                continue;
            }

            char userAnswer = input.charAt(0);
            if (userAnswer == q.correctAnswer) {
                score++;
                summary.add("Q" + (i + 1) + ": Correct");
            } else {
                summary.add("Q" + (i + 1) + ": Incorrect (Correct: " + q.correctAnswer + ")");
            }
        }

        // Show result
        System.out.println("\n--- Quiz Completed ---");
        System.out.println("Score: " + score + "/" + questions.size());
        System.out.println("Summary:");
        for (String s : summary) {
            System.out.println(s);
        }
    }

    static void loadQuestions() {
        questions.add(new Question(
            "What is the capital of France?",
            new String[]{"Berlin", "London", "Paris", "Madrid"},
            'C'));

        questions.add(new Question(
            "Which planet is known as the Red Planet?",
            new String[]{"Earth", "Mars", "Jupiter", "Saturn"},
            'B'));

        questions.add(new Question(
            "Who wrote 'Romeo and Juliet'?",
            new String[]{"Shakespeare", "Tolkien", "Austen", "Dickens"},
            'A'));

        questions.add(new Question(
            "What is the largest ocean on Earth?",
            new String[]{"Atlantic", "Indian", "Pacific", "Arctic"},
            'C'));
    }
} 
    
