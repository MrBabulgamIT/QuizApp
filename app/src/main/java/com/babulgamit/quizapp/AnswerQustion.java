package com.babulgamit.quizapp;

public class AnswerQustion {
    int optionA,optionB,optionC,optionD,questionId,answerId;

    public AnswerQustion(int questionIda, int optiona, int optionb, int optionc, int optiond, int answerIda) {
        this.questionId = questionIda;
        this.optionA = optiona;
        this.optionB = optionb;
        this.optionC = optionc;
        this.optionD = optiond;
        this.answerId = answerIda;
    }
    public int getQuestionId() {
        return questionId;
    }

    public int getOptionA() {
        return optionA;
    }

    public int getOptionB() {
        return optionB;
    }

    public int getOptionC() {
        return optionC;
    }

    public int getOptionD() {
        return optionD;
    }


    public int getAnswerId() {
        return answerId;
    }
}
