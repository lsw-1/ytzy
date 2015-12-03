package com.ludwigstralewiren.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Ludwig on 2015-11-28.
 */
public class Scores {

    private  SimpleStringProperty typeOfScore;
    private  SimpleIntegerProperty playerOnePoints;
    private  SimpleIntegerProperty playerTwoPoints;


    public Scores(){

    }

    public Scores(String typeOfScore, int playerOnePoints, int playerTwoPoints) {
        this.typeOfScore = new SimpleStringProperty(typeOfScore);
        this.playerOnePoints = new SimpleIntegerProperty(playerOnePoints);
        this.playerTwoPoints = new SimpleIntegerProperty(playerTwoPoints);
    }




    public String getTypeOfScore() {
        return typeOfScore.get();
    }

    public SimpleStringProperty typeOfScoreProperty() {
        return typeOfScore;
    }

    public void setTypeOfScore(String typeOfScore) {
        this.typeOfScore.set(typeOfScore);
    }

    public int getPlayerOnePoints() {
        return playerOnePoints.get();
    }

    public SimpleIntegerProperty playerOnePointsProperty() {
        return playerOnePoints;
    }

    public void setPlayerOnePoints(int playerOnePoints) {
        this.playerOnePoints.set(playerOnePoints);
    }

    public int getPlayerTwoPoints() {
        return playerTwoPoints.get();
    }

    public SimpleIntegerProperty playerTwoPointsProperty() {
        return playerTwoPoints;
    }

    public void setPlayerTwoPoints(int playerTwoPoints) {
        this.playerTwoPoints.set(playerTwoPoints);
    }
}
