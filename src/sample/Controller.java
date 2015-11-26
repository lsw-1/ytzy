package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Controller implements EventHandler<Event> {


    @FXML
    private List<Button> diceList;
    @FXML
    private Text tvapar;
    @FXML
    private Text ettor;

    Dice dice1 = new Dice(1);
    Dice dice2 = new Dice(2);
    Dice dice3 = new Dice(3);
    Dice dice4 = new Dice(4);
    Dice dice5 = new Dice(5);

    private List<Dice> mAlltheDices;
    List<Integer> allValues;

    private int count = 0;


    public Controller() {
        mAlltheDices = new ArrayList<>();
        allValues = new ArrayList<>();

        mAlltheDices.add(dice1);
        mAlltheDices.add(dice2);
        mAlltheDices.add(dice3);
        mAlltheDices.add(dice4);
        mAlltheDices.add(dice5);

    }

    public void rollAllDices(ActionEvent event) {
        if (count < 3) {
            for (int i = 0; i < 5; i++) {
                mAlltheDices.get(i).rollDice(mAlltheDices.get(i).isStatus(), i);
                int diceVal = mAlltheDices.get(i).getValue();
                String stringVal = Integer.toString(diceVal);
                diceList.get(i).setText(stringVal);
            }
        }
        count++;
        if (count == 3) {
            getDiceValues();
        }
    }


    public void handle(Event event) {
        if (event.getSource().equals(diceList.get(0))) {
            if (mAlltheDices.get(0).isStatus() == true && diceList.get(0).getText() != "??")
                mAlltheDices.get(0).setStatus(false);
        } else if (event.getSource().equals(diceList.get(1)) && diceList.get(0).getText() != "??") {
            if (mAlltheDices.get(1).isStatus() == true)
                mAlltheDices.get(1).setStatus(false);
        } else if (event.getSource().equals(diceList.get(2)) && diceList.get(0).getText() != "??") {
            if (mAlltheDices.get(2).isStatus() == true)
                mAlltheDices.get(2).setStatus(false);
        } else if (event.getSource().equals(diceList.get(3)) && diceList.get(0).getText() != "??") {
            if (mAlltheDices.get(3).isStatus() == true)
                mAlltheDices.get(3).setStatus(false);
        } else if (event.getSource().equals(diceList.get(4)) && diceList.get(0).getText() != "??") {
            if (mAlltheDices.get(4).isStatus() == true)
                mAlltheDices.get(4).setStatus(false);
        }
    }

    public void endTurn(ActionEvent event) {
        count = 0;
        for (int i = 0; i < 5; i++) {
            diceList.get(i).setText("??");
        }
        for (int i = 0; i < 5; i++) {
            mAlltheDices.get(i).setStatus(true);
        }


    }

    public void getDiceValues() {
        allValues.clear();
        for (int i = 0; i < 5; i++) {
            allValues.add(mAlltheDices.get(i).getValue());
        }

    }


    public void tvaPar(ActionEvent event) {
        int sum = 0;
        int count = 0;
        if (count < 1) {
            for (int value : allValues) {
                switch ()
                case 1

                if (value == 2) {
                    sum = sum + value;
                }
                String stringSum = Integer.toString(sum);
                tvapar.setText(stringSum);
            }
            count++;
        }
    }





    public void BUG(ActionEvent event) {
        System.out.println(allValues);
    }
}




