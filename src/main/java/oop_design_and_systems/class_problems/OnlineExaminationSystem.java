package main.java.oop_design_and_systems.class_problems;
import java.util.*;

public class OnlineExaminationSystem {

    interface Question {
        String getQuestionText();
        boolean checkAnswer(String answer);
    }

    static class MultipleChoiceQuestion implements Question {
        private String questionText;
        private String correctAnswer;

        MultipleChoiceQuestion(String questionText, String correctAnswer) {
            this.questionText = questionText;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestionText() {
            return questionText;
        }

        public boolean checkAnswer(String answer) {
            return correctAnswer.equalsIgnoreCase(answer);
        }
    }

    static class TrueFalseQuestion implements Question {
        private String questionText;
        private String correctAnswer;

        TrueFalseQuestion(String questionText, String correctAnswer) {
            this.questionText = questionText;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestionText() {
            return questionText;
        }

        public boolean checkAnswer(String answer) {
            return correctAnswer.equalsIgnoreCase(answer);
        }
    }

    static class Student {
        private String name;

        Student(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }
    }

    static class Attempt {
        private Student student;
        private Examination examination;
        private Map<Integer, String> answers = new HashMap<>();
        private boolean submitted;

        Attempt(Student student, Examination examination) {
            this.student = student;
            this.examination = examination;
        }

        void answerQuestion(int questionNumber, String answer) {
            if (submitted) {
                System.out.println("Cannot change answers after submission.");
                return;
            }

            if (questionNumber < 1 || questionNumber > examination.questions.size()) {
                System.out.println("Invalid question number.");
                return;
            }

            answers.put(questionNumber, answer);
            System.out.println("Question " + questionNumber + " answered with '" + answer + "'.");
        }

        void submit() {
            if (submitted) {
                System.out.println("Attempt already submitted.");
                return;
            }

            submitted = true;
            System.out.println("Examination '" + examination.title + "' submitted successfully.");
            System.out.println("Result for '" + examination.title + "' attempt: "
                    + evaluate() + "/" + examination.questions.size() + " correct.");
        }

        private int evaluate() {
            int score = 0;

            for (int i = 0; i < examination.questions.size(); i++) {
                String answer = answers.get(i + 1);

                if (answer != null && examination.questions.get(i).checkAnswer(answer)) {
                    score++;
                }
            }

            return score;
        }

        boolean isSubmitted() {
            return submitted;
        }

        Student getStudent() {
            return student;
        }
    }

    static class Examination {
        private String title;
        private List<Question> questions = new ArrayList<>();
        private Map<String, Attempt> attempts = new HashMap<>();

        Examination(String title) {
            this.title = title;
        }

        void addQuestion(Question question) {
            questions.add(question);
        }

        Attempt startAttempt(Student student) {
            String key = student.getName();

            if (attempts.containsKey(key)) {
                Attempt existing = attempts.get(key);

                if (existing.isSubmitted()) {
                    System.out.println("Student has already submitted this examination.");
                    return null;
                }

                return existing;
            }

            Attempt attempt = new Attempt(student, this);
            attempts.put(key, attempt);
            System.out.println("Examination '" + title + "' started by " + student.getName() + ".");
            return attempt;
        }
    }

    public static void main(String[] args) {
        Student student = new Student("Student");

        Examination examination = new Examination("Math Quiz");
        examination.addQuestion(new MultipleChoiceQuestion("Question 1", "A"));
        examination.addQuestion(new MultipleChoiceQuestion("Question 2", "B"));

        Attempt attempt = examination.startAttempt(student);

        if (attempt != null) {
            attempt.answerQuestion(1, "A");
            attempt.answerQuestion(2, "C");
            attempt.submit();
            attempt.answerQuestion(1, "B");
        }
    }
}