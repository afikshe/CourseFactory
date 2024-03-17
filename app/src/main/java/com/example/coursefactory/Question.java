package com.example.coursefactory;

public class Question {

    String questionId, questionContent, correctAnswer;

    public Question(String questionId, String questionContent, String correctAnswer) {
        this.questionId = questionId;
        this.questionContent = questionContent;
        this.correctAnswer = correctAnswer;
    }

    public Question(String questionId, String questionContent) {
        this.questionId = questionId;
        this.questionContent = questionContent;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
