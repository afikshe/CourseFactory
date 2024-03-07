package com.example.coursefactory;

public class TrueFalseQuestion extends Question{

    Boolean isTrue;
    Integer points;

    public TrueFalseQuestion(String questionContent, String correctAnswer, Boolean isTrue) {
        super(questionContent, correctAnswer);
        this.isTrue = isTrue;
        this.points = 5;
    }

    public Boolean getTrue() {
        return isTrue;
    }

    public Integer getPoints() {
        return points;
    }
}
