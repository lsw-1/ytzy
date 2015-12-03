package com.ludwigstralewiren.Controller;

import com.ludwigstralewiren.Model.Dice;
import com.ludwigstralewiren.Model.ScoreCombos;
import com.ludwigstralewiren.Model.Scores;
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
import java.util.List;

public class Controller implements EventHandler<Event> {


    @FXML
    private List<Button> diceList;
    @FXML
    private TableView<Scores> myBoard;
    @FXML
    private ObservableList<Scores> myViewData = FXCollections.observableArrayList();
    @FXML
    private Text message;
    @FXML
    private Button addpoints;
    @FXML
    private Button playbutton;
    @FXML
    private Button endturn;


    Dice dice1 = new Dice(1);
    Dice dice2 = new Dice(2);
    Dice dice3 = new Dice(3);
    Dice dice4 = new Dice(4);
    Dice dice5 = new Dice(5);


    private ScoreCombos mScoreCombos;
    private List<Dice> mAlltheDices;
    private List<Integer> mAllValues;
    private int mCount = 0;
    private int mTurn = 0;
    private boolean played = false;


    public Controller() {
        mScoreCombos = new ScoreCombos();
        mAlltheDices = new ArrayList<>();
        mAllValues = new ArrayList<>();

        mAlltheDices.add(dice1);
        mAlltheDices.add(dice2);
        mAlltheDices.add(dice3);
        mAlltheDices.add(dice4);
        mAlltheDices.add(dice5);

        myViewData.add(new Scores("ETTOR", 0, 0));
        myViewData.add(new Scores("TVÅOR", 1, 0));
        myViewData.add(new Scores("TREOR", 1, 0));
        myViewData.add(new Scores("FYROR", 1, 0));
        myViewData.add(new Scores("FEMMOR", 1, 0));
        myViewData.add(new Scores("SEXOR", 1, 0));
        myViewData.add(new Scores("ETT PAR", 1, 0));
        myViewData.add(new Scores("TVÅ PAR", 1, 0));
        myViewData.add(new Scores("FYRTAL", 1, 0));
        myViewData.add(new Scores("TRETAL", 1, 0));
        myViewData.add(new Scores("LITEN STRAIGHT", 1, 0));
        myViewData.add(new Scores("STOR STRAIGHT", 1, 0));
        myViewData.add(new Scores("KÅK", 1, 0));
        myViewData.add(new Scores("YATZY", 1, 0));
        myViewData.add(new Scores("CHANS", 1, 0));
    }

    public List<Integer> getAllValues() {
        return mAllValues;
    }

    public void initialize() {
        myBoard.setItems(myViewData);

    }


//    DICE METHODS

    public void rollAllDices(ActionEvent event) {
        if (mCount < 3) {
            for (int i = 0; i < 5; i++) {
                mAlltheDices.get(i).rollDice(mAlltheDices.get(i).isStatus(), i);
                int diceVal = mAlltheDices.get(i).getValue();
                String stringVal = Integer.toString(diceVal);
                diceList.get(i).setText(stringVal);
            }
        }
        mCount++;
        if (mCount == 3) {
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
        mCount = 0;
        for (int i = 0; i < 5; i++) {
            diceList.get(i).setText("??");
        }
        for (int i = 0; i < 5; i++) {
            mAlltheDices.get(i).setStatus(true);
        }

        played = false;
        mTurn++;
        if (mTurn % 2 == 0) {
            message.setText("Första spelares tur");
        } else {
            message.setText("Andra spelares tur");
        }


    }

    public void getDiceValues() {
        mAllValues.clear();
        for (int i = 0; i < 5; i++) {
            mAllValues.add(mAlltheDices.get(i).getValue());
        }

    }

//    TABLEVIEW METHODS

    public void BUG(ActionEvent event) {
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
            if (mTurn % 2 == 0) {
                if (getScoreObject().getPlayerOnePoints() == 0 && getIndex() < 6) {
                    getScoreObject().setPlayerOnePoints(mScoreCombos.singleValuePoints(mAllValues, myBoard.getSelectionModel().getSelectedIndex()));
                } else if (getScoreObject().getPlayerOnePoints() == 0 && getIndex() == 6) {
                    getScoreObject().setPlayerOnePoints(mScoreCombos.scorePair(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
                } else if (getScoreObject().getPlayerOnePoints() == 0 && getIndex() == 7) {
                    getScoreObject().setPlayerOnePoints(mScoreCombos.twoPair(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
                } else if (getScoreObject().getPlayerOnePoints() == 0 && getIndex() == 8) {
                    getScoreObject().setPlayerOnePoints(mScoreCombos.fourOfAKind(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
                } else if (getScoreObject().getPlayerOnePoints() == 0 && getIndex() == 9) {
                    getScoreObject().setPlayerOnePoints(mScoreCombos.threeOfAKind(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
                } else if (getScoreObject().getPlayerOnePoints() == 0 && getIndex() == 10) {
                    getScoreObject().setPlayerOnePoints(mScoreCombos.smallStraight(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
                } else if (getScoreObject().getPlayerOnePoints() == 0 && getIndex() == 11) {
                    getScoreObject().setPlayerOnePoints(mScoreCombos.largeStraight(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
                } else if (getScoreObject().getPlayerOnePoints() == 0 && getIndex() == 12) {
                    getScoreObject().setPlayerOnePoints(mScoreCombos.fullHouse(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
                } else if (getScoreObject().getPlayerOnePoints() == 0 && getIndex() == 13) {
                    getScoreObject().setPlayerOnePoints(mScoreCombos.yatzy(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
                } else if (getScoreObject().getPlayerOnePoints() == 0 && getIndex() == 14) {
                    getScoreObject().setPlayerOnePoints(mScoreCombos.chance(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));

                }
            } else {

                if (getScoreObject().getPlayerTwoPoints() == 0 && getIndex() < 6) {
                    getScoreObject().setPlayerTwoPoints(mScoreCombos.singleValuePoints(mAllValues, myBoard.getSelectionModel().getSelectedIndex()));
                } else if (getScoreObject().getPlayerTwoPoints() == 0 && getIndex() == 6) {
                    getScoreObject().setPlayerTwoPoints(mScoreCombos.scorePair(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
                } else if (getScoreObject().getPlayerTwoPoints() == 0 && getIndex() == 7) {
                    getScoreObject().setPlayerTwoPoints(mScoreCombos.twoPair(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
                } else if (getScoreObject().getPlayerTwoPoints() == 0 && getIndex() == 8) {
                    getScoreObject().setPlayerTwoPoints(mScoreCombos.fourOfAKind(dice1.getValue(), dice2.getValue(), dice3.getValue(), dice4.getValue(), dice5.getValue()));
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
            endGame();
        }
    }


    public void endGame() {
        int sumPlayerOne = 0;
        int sumPlayerTwo = 0;
        List<Integer> listValuesOne = new ArrayList<>();
        List<Integer> listValuesTwo = new ArrayList<>();


        for (int i = 0; i < 14; i++) {
            int val = (Integer) myBoard.getColumns().get(1).getCellObservableValue(i).getValue();
            if (val != 0)
                listValuesOne.add(val);
        }
        for (int i = 0; i < 14; i++) {
            int val = (Integer) myBoard.getColumns().get(1).getCellObservableValue(i).getValue();
            if (val != 0)
                listValuesTwo.add(val);
        }

        if (listValuesOne.size() == 14 || listValuesOne.size() == 14) {
            for (int i = 0; i < 14; i++) {
                int val = (Integer) myBoard.getColumns().get(1).getCellObservableValue(i).getValue();
                sumPlayerOne += val;
            }
            for (int i = 0; i < 14; i++) {
                int val = (Integer) myBoard.getColumns().get(2).getCellObservableValue(i).getValue();
                sumPlayerTwo += val;
            }
            if (sumPlayerOne > sumPlayerTwo) {
                message.setText("Spelare 1 har vunnit med poäng: " + sumPlayerOne + " över spelare 2 poäng: " + sumPlayerTwo);
            } else {
                message.setText("Spelare 2 har vunnit med poäng: " + sumPlayerTwo + " över spelare 1 poäng: " + sumPlayerOne);
            }
            disableButtons();
        }

    }

    private void disableButtons() {
        addpoints.setDisable(true);
        endturn.setDisable(true);
        playbutton.setDisable(true);

    }
}


