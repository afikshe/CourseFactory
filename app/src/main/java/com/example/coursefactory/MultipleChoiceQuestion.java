package com.example.coursefactory;

public class MultipleChoiceQuestion extends Question{

    String option1, option2, option3, option4;
    Integer points;

    public MultipleChoiceQuestion(String questionId, String questionContent, String correctAnswer, String option1, String option2, String option3, String option4) {
        super(questionId, questionContent, correctAnswer);
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.points = 10;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public Integer getPoints() {
        return points;
    }
}
