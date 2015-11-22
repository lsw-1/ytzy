package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class Controller {


    @FXML private Button buttonone;
    @FXML private HBox dices;
    @FXML private List<Button> diceList;

    Dice dice1 = new Dice(1);
    Dice dice2 = new Dice(2);
    Dice dice3 = new Dice(3);
    Dice dice4 = new Dice(4);
    Dice dice5 = new Dice(5);

    private List<Dice> theDices;

    public Controller() {
        theDices = new ArrayList<>();

        theDices.add(dice1);
        theDices.add(dice2);
        theDices.add(dice3);
        theDices.add(dice4);
        theDices.add(dice5);

    }

    public void rollAllDices(){
        for (int i = 0; i < 5; i++) {
            rollDices(i);
        }
        if()


    }

    private void rollDices(int i) {
        theDices.get(i).rollDice();
        int diceVal = theDices.get(i).getValue();
        String stringVal = Integer.toString(diceVal);
        diceList.get(i).setText(stringVal);
    }

    public void selectDices(Event event){
        List<Dice> listOfSelectedDices = new ArrayList<>();
//        TODO: Skapa metod för att stoppa tärningar för kastning






    }



    public int ebug(ActionEvent event) {
        theDices.get(1).setValue(5);
        int s = theDices.get(1).getValue();
        System.out.println(theDices);
        return s;
    }
}

