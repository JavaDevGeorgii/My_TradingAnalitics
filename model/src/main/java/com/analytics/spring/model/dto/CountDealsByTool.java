package com.analytics.spring.model.dto;

/**
 * Created by Alex on 19.06.2015.
 */
public class CountDealsByTool {

    String Tool;
    Integer countDeal;

    public CountDealsByTool() {
    }

    public String getTool() {
        return Tool;
    }

    public void setTool(String tool) {
        Tool = tool;
    }

    public Integer getCountDeal() {
        return countDeal;
    }

    public void setCountDeal(Integer countDeals) {
        this.countDeal = countDeals;
    }
}
