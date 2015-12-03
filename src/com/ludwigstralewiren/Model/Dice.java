package com.ludwigstralewiren.Model;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.List;
import java.util.Random;

/**
 * Created by Ludwig on 2015-11-14.
 */
public class Dice {
    private int mValue;
    private int mOrder;
    private boolean mStatus;

    public Dice(int order) {
        mValue = 0;
        mOrder = order;
        mStatus = true;
    }

    public boolean isStatus() {
        return mStatus;
    }

    public int getOrder() {
        return mOrder;
    }

    public void setStatus(boolean status) {
        mStatus = status;
    }

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
       mValue = value;
    }

    @Override
    public String toString(){
        return "Die " + mOrder + " value of " + mValue;
    }

    public void rollDice(boolean status, int i)
    {
        int value;
        Random random = new Random();
        if (status == true){
            value = random.nextInt(6)+1;
            System.out.println(value);
            setValue(value);
        }


    }



}
