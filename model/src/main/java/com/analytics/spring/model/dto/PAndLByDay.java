package com.analytics.spring.model.dto;

import java.io.Serializable;

/**
 * Created by Vitalii Ievtushenko on 05.06.2015 12:04.
 */

public class PAndLByDay implements Serializable{

    private Long date;
    private Double profit;

    public PAndLByDay() {
    }

    public PAndLByDay(Long date, Double profit) {
        this.date = date;
        this.profit = profit;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }
}
