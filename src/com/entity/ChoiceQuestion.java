package com.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by victor on 2018/2/28.
 */
@Entity
@Table(name = "choice_question", schema = "test", catalog = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ChoiceQuestion {
    private long choiceQuestionId;
    private String content;
    private String answer;
    private String questionNo;
    private String keya;
    private String keyb;
    private String keyc;
    private String keyd;

    @Id
    @Column(name = "choice_question_id", nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    public long getChoiceQuestionId() {
        return choiceQuestionId;
    }

    public void setChoiceQuestionId(long choiceQuestionId) {
        this.choiceQuestionId = choiceQuestionId;
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
    @Column(name = "answer", nullable = false, length = 8)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
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

        ChoiceQuestion that = (ChoiceQuestion) o;

        if (choiceQuestionId != that.choiceQuestionId) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;

        if (answer != null ? !answer.equals(that.answer) : that.answer != null) return false;
        if (questionNo != null ? !questionNo.equals(that.questionNo) : that.questionNo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (choiceQuestionId ^ (choiceQuestionId >>> 32));
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (questionNo != null ? questionNo.hashCode() : 0);
        return result;
    }


    @Basic
    @Column(name = "keya", nullable = false, length = 128)
    public String getKeya() {
        return keya;
    }

    public void setKeya(String keya) {
        this.keya = keya;
    }

    @Basic
    @Column(name = "keyb", nullable = false, length = 128)
    public String getKeyb() {
        return keyb;
    }

    public void setKeyb(String keyb) {
        this.keyb = keyb;
    }

    @Basic
    @Column(name = "keyc", nullable = false, length = 128)
    public String getKeyc() {
        return keyc;
    }

    public void setKeyc(String keyc) {
        this.keyc = keyc;
    }

    @Basic
    @Column(name = "keyd", nullable = false, length = 128)
    public String getKeyd() {
        return keyd;
    }

    public void setKeyd(String keyd) {
        this.keyd = keyd;
    }
}
