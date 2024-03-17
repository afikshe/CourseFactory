package com.example.coursefactory;

public class TrueFalseQuestion extends Question{

    Boolean isTrue;
    Integer points;

    public TrueFalseQuestion(String questionId, String questionContent, Boolean isTrue) {
        super(questionId, questionContent);
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
