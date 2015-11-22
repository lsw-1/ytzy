package sample;

import javafx.fxml.FXML;

import java.util.Random;

/**
 * Created by Ludwig on 2015-11-14.
 */
public class Dice {
    private int mValue;
    private int mOrder;
    private FXML mButton;

    public Dice(int order) {
        mValue = 0;
        mOrder = order;
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

    public void rollDice()
    {
        int value;
        Random random = new Random();
        value = random.nextInt(6)+1;
        System.out.println(value);
        setValue(value);

    }

    public void showDiePicture(int value){
        switch (value){
            case 1:
        }
    }


}
