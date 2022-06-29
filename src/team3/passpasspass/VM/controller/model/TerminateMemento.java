package team3.passpasspass.VM.controller.model;

import team3.passpasspass.VM.controller.CustomerPanel;

import java.util.ArrayList;

public class TerminateMemento {
    ArrayList<Integer> coinsEntered;

    public TerminateMemento() {
//        this.customerPanel = customerPanel;
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
