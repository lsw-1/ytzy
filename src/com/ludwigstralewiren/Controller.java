package com.ludwigstralewiren;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller implements EventHandler<Event> {


    @FXML
    private List<Button> diceList;
    @FXML
    private TableView<Scores> myBoard;
    @FXML
    private ObservableList<Scores> myViewData = FXCollections.observableArrayList();
    @FXML
    private Text message;


    Dice dice1 = new Dice(1);
    Dice dice2 = new Dice(2);
    Dice dice3 = new Dice(3);
    Dice dice4 = new Dice(4);
    Dice dice5 = new Dice(5);


    private ScoreCombos mScoreCombos;
    private List<Dice> mAlltheDices;
    private List<Integer> allValues;
    private int count = 0;
    private int turn = 0;
    private Map<String, Integer> players;
    private boolean played = false;


    public Controller() {
        mScoreCombos = new ScoreCombos();
        players = new HashMap<>();

        mAlltheDices = new ArrayList<>();
        allValues = new ArrayList<>();

        mAlltheDices.add(dice1);
        mAlltheDices.add(dice2);
        mAlltheDices.add(dice3);
        mAlltheDices.add(dice4);
        mAlltheDices.add(dice5);

        myViewData.add(new Scores("ETTOR", 0, 0));
        myViewData.add(new Scores("TVÅOR", 0, 0));
        myViewData.add(new Scores("TREOR", 0, 0));
        myViewData.add(new Scores("FYROR", 0, 0));
        myViewData.add(new Scores("FEMMOR", 0, 0));
        myViewData.add(new Scores("SEXOR", 0, 0));
        myViewData.add(new Scores("ETT PAR", 0, 0));
        myViewData.add(new Scores("TVÅ PAR", 0, 0));
        myViewData.add(new Scores("FYRTAL", 0, 0));
        myViewData.add(new Scores("TRETAL", 0, 0));
        myViewData.add(new Scores("LITEN STRAIGHT", 0, 0));
        myViewData.add(new Scores("STOR STRAIGHT", 0, 0));
        myViewData.add(new Scores("KÅK", 0, 0));
        myViewData.add(new Scores("YATZY", 0, 0));
        myViewData.add(new Scores("CHANS", 0, 0));
    }

    public List<Integer> getAllValues() {
        return allValues;
    }

    public void initialize() {
        myBoard.setItems(myViewData);

    }


//    DICE METHODS

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

        played = false;
        turn++;
        if(turn % 2 == 0){
            message.setText("Första spelares tur");
        } else {
            message.setText("Andra spelares tur");
        }


    }

    public void getDiceValues() {
        allValues.clear();
        for (int i = 0; i < 5; i++) {
            allValues.add(mAlltheDices.get(i).getValue());
        }

    }

//    TABLEVIEW METHODS

    public void BUG(ActionEvent event) {
        Integer sum = 0;
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            ObservableValue<?> val = myBoard.getColumns().get(1).getCellObservableValue(i);
        }
    }


    private Scores getScoreObject() {
        Scores sum = myViewData.get(myBoard.getSelectionModel().getSelectedIndex());
        return sum;
    }

    private int getIndex() {
        return myBoard.getSelectionModel().getSelectedIndex();
    }


    public void addPoints(ActionEvent event) {
        if (played == false) {
            if (turn % 2 == 0) {
                if (getScoreObject().getPlayerOnePoints() == 0 && getIndex() < 6) {
                    getScoreObject().setPlayerOnePoints(mScoreCombos.singleValuePoints(allValues, myBoard.getSelectionModel().getSelectedIndex()));
                } else if (getScoreObject().getPlayerOnePoints() == 0 && getIndex() == 6) {
                    getScoreObject().setPlayerOnePoints(mScoreCombos.scorePair(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
                } else if (getScoreObject().getPlayerOnePoints() == 0 && getIndex() == 7) {
                    getScoreObject().setPlayerOnePoints(mScoreCombos.twoPair(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
                } else if (getScoreObject().getPlayerOnePoints() == 0 && getIndex() == 8) {
                    getScoreObject().setPlayerOnePoints(mScoreCombos.four_of_a_kind(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
                } else if (getScoreObject().getPlayerOnePoints() == 0 && getIndex() == 9) {
                    getScoreObject().setPlayerOnePoints(mScoreCombos.threeOfAKind(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
                } else if (getScoreObject().getPlayerOnePoints() == 0 && getIndex() == 10) {
                    getScoreObject().setPlayerOnePoints(mScoreCombos.smallStraight(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
                } else if (getScoreObject().getPlayerOnePoints() == 0 && getIndex() == 11) {
                    getScoreObject().setPlayerOnePoints(mScoreCombos.largeStraight(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
                } else if (getScoreObject().getPlayerOnePoints() == 0 && getIndex() == 12) {
                    getScoreObject().setPlayerOnePoints(mScoreCombos.fullHouse(2, 2, 2, 3, 3));
                } else if (getScoreObject().getPlayerOnePoints() == 0 && getIndex() == 13) {
                    getScoreObject().setPlayerOnePoints(mScoreCombos.yatzy(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
                } else if (getScoreObject().getPlayerOnePoints() == 0 && getIndex() == 14) {
                    getScoreObject().setPlayerOnePoints(mScoreCombos.chance(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));

                }
            } else {

                if (getScoreObject().getPlayerTwoPoints() == 0 && getIndex() < 6) {
                    getScoreObject().setPlayerTwoPoints(mScoreCombos.singleValuePoints(allValues, myBoard.getSelectionModel().getSelectedIndex()));
                } else if (getScoreObject().getPlayerTwoPoints() == 0 && getIndex() == 6) {
                    getScoreObject().setPlayerTwoPoints(mScoreCombos.scorePair(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
                } else if (getScoreObject().getPlayerTwoPoints() == 0 && getIndex() == 7) {
                    getScoreObject().setPlayerTwoPoints(mScoreCombos.twoPair(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
                } else if (getScoreObject().getPlayerTwoPoints() == 0 && getIndex() == 8) {
                    getScoreObject().setPlayerTwoPoints(mScoreCombos.four_of_a_kind(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
                } else if (getScoreObject().getPlayerTwoPoints() == 0 && getIndex() == 9) {
                    getScoreObject().setPlayerTwoPoints(mScoreCombos.threeOfAKind(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
                } else if (getScoreObject().getPlayerTwoPoints() == 0 && getIndex() == 10) {
                    getScoreObject().setPlayerTwoPoints(mScoreCombos.smallStraight(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
                } else if (getScoreObject().getPlayerTwoPoints() == 0 && getIndex() == 11) {
                    getScoreObject().setPlayerTwoPoints(mScoreCombos.largeStraight(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
                } else if (getScoreObject().getPlayerTwoPoints() == 0 && getIndex() == 12) {
                    getScoreObject().setPlayerTwoPoints(mScoreCombos.fullHouse(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
                } else if (getScoreObject().getPlayerTwoPoints() == 0 && getIndex() == 13) {
                    getScoreObject().setPlayerTwoPoints(mScoreCombos.yatzy(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
                } else if (getScoreObject().getPlayerTwoPoints() == 0 && getIndex() == 14) {
                    getScoreObject().setPlayerTwoPoints(mScoreCombos.chance(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
                }
            }
            played = true;
        }
    }


    public void endGame(){
    }
}

