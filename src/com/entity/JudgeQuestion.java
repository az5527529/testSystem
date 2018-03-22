package com.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by victor on 2018/2/28.
 */
@Entity
@Table(name = "judge_question", schema = "test", catalog = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class JudgeQuestion {
    private long judgeQuestionId;
    private String content;
    private boolean answer;
    private String questionNo;

    @Id
    @Column(name = "judge_question_id", nullable = false)
    public long getJudgeQuestionId() {
        return judgeQuestionId;
    }

    public void setJudgeQuestionId(long judgeQuestionId) {
        this.judgeQuestionId = judgeQuestionId;
    }

    @Basic
    @Column(name = "content", nullable = false, length = 256)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "answer", nullable = false)
    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    @Basic
    @Column(name = "question_no", nullable = false, length = 8)
    public String getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(String questionNo) {
        this.questionNo = questionNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JudgeQuestion that = (JudgeQuestion) o;

        if (judgeQuestionId != that.judgeQuestionId) return false;
        if (answer != that.answer) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (questionNo != null ? !questionNo.equals(that.questionNo) : that.questionNo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (judgeQuestionId ^ (judgeQuestionId >>> 32));
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (answer ? 1 : 0);
        result = 31 * result + (questionNo != null ? questionNo.hashCode() : 0);
        return result;
    }
}
