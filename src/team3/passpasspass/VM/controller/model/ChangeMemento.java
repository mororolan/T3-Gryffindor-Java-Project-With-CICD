package team3.passpasspass.VM.controller.model;

import team3.passpasspass.VM.controller.CustomerPanel;

import java.awt.*;
import java.util.ArrayList;

public class ChangeMemento {
    ArrayList<String[]> coins;
    CustomerPanel customerPanel;
    CoinList coinList;
    CoinList originalCoinlist = coinList;

    public ChangeMemento(CoinList coinList) {
        coins = ReadCSV.readCSV("./data/dwd_money_stat.csv");
        this.coinList = coinList;
//        this.customerPanel = customerPanel;
    }

    public CoinList newCoinsList(ArrayList<Integer> justCoin) {
        for(int i = 0; i < justCoin.size(); i++) {
            for (int j = 1; j < coins.size(); j++) {
                if(justCoin.get(i) == Integer.parseInt(coins.get(j)[0]))
                    coinList.setNumber(j,Integer.parseInt(coins.get(j)[1]) + 1);
            }
        }
        return coinList;
    }

    public CoinList getCoinList() {
        return coinList;
    }
}
