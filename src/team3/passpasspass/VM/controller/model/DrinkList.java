package team3.passpasspass.VM.controller.model;

import team3.passpasspass.VM.controller.CustomerPanel;

import java.util.ArrayList;

public class DrinkList {//Observer ---- Concrete Object
    private ArrayList<String[]> cans;
    CustomerPanel customerPanel;

    public DrinkList(CustomerPanel customerPanel){
        cans = ReadCSV.readCSV("./data/dwd_drink_info.csv");
        this.customerPanel = customerPanel;
    }

//    public ArrayList<String[]> getCans(){
//        return cans;
//    }

    public void setNumber(int id, int num){
        cans.get(id)[2] = String.valueOf(num);
        notifyNoStock();
    }

    public int getNumber(int id){
        return Integer.parseInt(cans.get(id)[2]);
    }

    public void setPrice(int id, int price){
        cans.get(id)[3] = String.valueOf(price);
    }

    public int getPrice(int id){
        return Integer.parseInt(cans.get(id)[3]);
    }

    public void notifyNoStock(){
        customerPanel.updateStock();
    }
}
