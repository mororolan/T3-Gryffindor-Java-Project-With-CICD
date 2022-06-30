package team3.Gryffindor.VM.model;

import java.util.ArrayList;

public class TerminateMemento {
    ArrayList<Integer> coinsEntered;

    public TerminateMemento() {
        coinsEntered = new ArrayList<>();
    }

    public void addCoin(int coinValue){//setState
        coinsEntered.add(coinValue);
    }

    public ArrayList<Integer> getState(){
        return coinsEntered;
    }

    public void clearcoinsEntered(){
        this.coinsEntered.clear();
    }
}
