package com.example.coursefactory;

public class MultipleChoiceQuestion extends Question {

    String option1;
    String option2;
    String option3;
    String option4;
    Integer points;
    private static final Integer MULTIPLE_CHOICE_QUESTION_POINTS = 10;

    public MultipleChoiceQuestion(String questionContent, String correctAnswer, String option1, String option2, String option3, String option4) {
        super(questionContent, correctAnswer);
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.points = MULTIPLE_CHOICE_QUESTION_POINTS;
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
