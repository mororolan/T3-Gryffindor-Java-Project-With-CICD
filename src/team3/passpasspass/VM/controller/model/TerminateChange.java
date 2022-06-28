package team3.passpasspass.VM.controller.model;

import team3.passpasspass.VM.controller.CustomerPanel;

import java.util.ArrayList;

public class TerminateChange {
    CustomerPanel customerPanel;

    public TerminateChange(CustomerPanel customerPanel, ArrayList<Integer> justCoin, CoinList coinList) {
        ChangeMemento changeMemento = new ChangeMemento(coinList);
        this.customerPanel = customerPanel;
        CustomerController customerController = new CustomerController();
        ChangeMemento newMemento = customerPanel.saveChangeMemento(changeMemento.newCoinsList(justCoin));
        customerController.add(newMemento);
    }
}
