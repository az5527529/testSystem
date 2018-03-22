package com.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by victor on 2018/2/28.
 */
@Entity
@Table(name = "answer_detail", schema = "test", catalog = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class AnswerDetail {
    private long answerDetailId;
    private long questionId;
    private int questionType;
    private String userAnswer;
    private boolean result;
    private long userInfoId;

    @Id
    @Column(name = "answer_detail_id", nullable = false)
    public long getAnswerDetailId() {
        return answerDetailId;
    }

    public void setAnswerDetailId(long answerDetailId) {
        this.answerDetailId = answerDetailId;
    }

    @Basic
    @Column(name = "question_id", nullable = false)
    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    @Basic
    @Column(name = "question_type", nullable = false)
    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    @Basic
    @Column(name = "user_answer", nullable = false, length = 8)
    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    @Basic
    @Column(name = "result", nullable = false)
    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Basic
    @Column(name = "user_info_id", nullable = false)
    public long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(long userInfoId) {
        this.userInfoId = userInfoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerDetail that = (AnswerDetail) o;

        if (answerDetailId != that.answerDetailId) return false;
        if (questionId != that.questionId) return false;
        if (questionType != that.questionType) return false;
        if (result != that.result) return false;
        if (userInfoId != that.userInfoId) return false;
        if (userAnswer != null ? !userAnswer.equals(that.userAnswer) : that.userAnswer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = (int) (answerDetailId ^ (answerDetailId >>> 32));
        result1 = 31 * result1 + (int) (questionId ^ (questionId >>> 32));
        result1 = 31 * result1 + questionType;
        result1 = 31 * result1 + (userAnswer != null ? userAnswer.hashCode() : 0);
        result1 = 31 * result1 + (result ? 1 : 0);
        result1 = 31 * result1 + (int) (userInfoId ^ (userInfoId >>> 32));
        return result1;
    }
}
