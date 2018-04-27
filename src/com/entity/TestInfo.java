package com.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by victor on 2018/2/28.
 */
@Entity
@Table(name = "test_info", schema = "test", catalog = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TestInfo implements Serializable {
    private Long testInfoId;
    private Integer numberOfJudge;
    private Double valueOfJudge;
    private Integer numberOfSelect;
    private Double valueOfSelect;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "test_info_id", nullable = false)
    public Long getTestInfoId() {
        return testInfoId;
    }

    public void setTestInfoId(Long testInfoId) {
        this.testInfoId = testInfoId;
    }

    @Basic
    @Column(name = "number_of_judge", nullable = false)
    public Integer getNumberOfJudge() {
        return numberOfJudge;
    }

    public void setNumberOfJudge(Integer numberOfJudge) {
        this.numberOfJudge = numberOfJudge;
    }

    @Basic
    @Column(name = "value_of_judge", nullable = false, precision = 0)
    public Double getValueOfJudge() {
        return valueOfJudge;
    }

    public void setValueOfJudge(Double valueOfJudge) {
        this.valueOfJudge = valueOfJudge;
    }

    @Basic
    @Column(name = "number_of_select", nullable = false)
    public Integer getNumberOfSelect() {
        return numberOfSelect;
    }

    public void setNumberOfSelect(Integer numberOfSelect) {
        this.numberOfSelect = numberOfSelect;
    }

    @Basic
    @Column(name = "value_of_select", nullable = false, precision = 0)
    public Double getValueOfSelect() {
        return valueOfSelect;
    }

    public void setValueOfSelect(Double valueOfSelect) {
        this.valueOfSelect = valueOfSelect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestInfo testInfo = (TestInfo) o;

        if (testInfoId != testInfo.testInfoId) return false;
        if (numberOfJudge != testInfo.numberOfJudge) return false;
        if (Double.compare(testInfo.valueOfJudge, valueOfJudge) != 0) return false;
        if (numberOfSelect != testInfo.numberOfSelect) return false;
        if (Double.compare(testInfo.valueOfSelect, valueOfSelect) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (testInfoId ^ (testInfoId >>> 32));
        result = 31 * result + numberOfJudge;
        temp = Double.doubleToLongBits(valueOfJudge);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + numberOfSelect;
        temp = Double.doubleToLongBits(valueOfSelect);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
