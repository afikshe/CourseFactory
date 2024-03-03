package com.example.coursefactory;

public class Question {

    String questionContent;
    String correctAnswer;

    public Question(String questionContent, String correctAnswer) {
        this.questionContent = questionContent;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
