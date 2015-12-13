package com.analytics.spring.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dmitry Natalenko on 08.06.2015.
 */
@JsonInclude(JsonInclude.Include.NON_NULL )
public class CurrencyPair implements Serializable {

    private String symbol;
    private Double ratio;
    private Date date;
    private Double prevClose;

    public CurrencyPair() {
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrevClose() {
        return prevClose;
    }

    public void setPrevClose(Double prevClose) {
        this.prevClose = prevClose;
    }
}
