/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tt.pojo;

import java.util.Date;

/**
 *
 * @author anhtu
 */
public class Discount {
    private Date date;
    private Double percent;
    private boolean checkBudget = false;
    
    public Discount(Date date, Double percent){
        this.date = date;
        this.percent = percent;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the percent
     */
    public Double getPercent() {
        return percent;
    }

    /**
     * @param percent the percent to set
     */
    public void setPercent(Double percent) {
        this.percent = percent;
    }
    
        /**
     * @return the checkBudget
     */
    public boolean isCheckBudget() {
        return checkBudget;
    }

    /**
     * @param checkBudget the checkBudget to set
     */
    public void setCheckBudget(boolean checkBudget) {
        this.checkBudget = checkBudget;
    }
    
}
