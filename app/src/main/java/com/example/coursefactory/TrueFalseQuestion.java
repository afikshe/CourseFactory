package com.example.coursefactory;

public class TrueFalseQuestion extends Question{

    Boolean isTrue;
    Integer points;
    private static final Integer TRUE_FALSE_QUESTION_POINTS = 5;

    public TrueFalseQuestion(String questionContent, String correctAnswer, Boolean isTrue, Integer points) {
        super(questionContent, correctAnswer);
        this.isTrue = isTrue;
        this.points = TRUE_FALSE_QUESTION_POINTS;
    }

    public Boolean getTrue() {
        return isTrue;
    }

    public Integer getPoints() {
        return points;
    }
}
