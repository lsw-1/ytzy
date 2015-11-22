package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ludwig on 2015-11-14.
 */
public class AllDices {
    private List<Dice> listOfDices;

    public AllDices() {
        listOfDices = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Dice dice = new Dice(4);
            listOfDices.add(dice);
        }
    }

    public void setListOfDices(List<Dice> listOfDices) {
        this.listOfDices = listOfDices;
    }

    public List<Dice> getListOfDices() {
        return listOfDices;
    }


}
