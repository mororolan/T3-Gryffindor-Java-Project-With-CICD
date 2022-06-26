package team3.passpasspass.VM.controller;

import team3.passpasspass.VM.controller.GUI.*;
import team3.passpasspass.VM.controller.model.CheckKeyType;
import team3.passpasspass.VM.controller.model.ReadCSV;
import team3.passpasspass.VM.controller.model.WriteCSV;

import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

// TODO
public class MachinerySimulatorPanel extends T3Frame {

public MachinerySimulatorPanel(String title, boolean loginStatus) {
    super(title);
    ArrayList<String[]> coins = ReadCSV.readCSV("./data/dwd_money_stat.csv");
    ArrayList<String[]> cans = ReadCSV.readCSV("./data/dwd_drink_info.csv");
    AtomicBoolean unlockStatus = new AtomicBoolean(loginStatus);


    JLabel bigTitle = new TextFactory(title);

    JPanel coinContainer = new T3Panel();
    JPanel canContainer = new T3Panel();
    System.out.println(coins.size());
    int y = 50;
    TextFactory warning = new TextFactory("No change");

    showCoinsChange(coins, unlockStatus, warning, coinContainer);
    showCansChange(cans, unlockStatus, warning, canContainer);

    JCheckBox lockBox = new JCheckBox("Door Locked", !loginStatus);
    System.out.println("现在状态是"+unlockStatus);

    if (loginStatus == true) {
        lockBox.addItemListener(e -> {
            unlockStatus.set(!unlockStatus.get());
            System.out.println("hahaha");

        });

//        public void changeUnlockStatus(AtomicBoolean unlockStatus){
//            unlockStatus.set(!unlockStatus.get());
//            System.out.println("hahaha");
//        }

    }
    JButton backBnt = ButtonFactory.buttonFactory("Back to the Main Panel", "machineryPanel");

    this.add(bigTitle);
    this.add(new TextFactory("Quantity of Coins", 1));
    this.add(coinContainer);
    this.add(new TextFactory("Quantity of Cans", 1));
    this.add(canContainer);
    this.add(lockBox);
    this.add(warning);
    this.add(backBnt);

    backBnt.addActionListener((e -> {
        this.dispose();
        new SimulatorControlPanel("VMCS - Simulator Control Panel", loginStatus);

    }));

}

    public void showCoinsChange(ArrayList<String[]> coins,AtomicBoolean unlockStatus,TextFactory warning,JPanel coinContainer){
        for (int i = 1; i < coins.size(); i++) {
            TextFactory a = new TextFactory(coins.get(i)[0]);
            JTextField b = new JTextField(coins.get(i)[1], 5);
            JButton bnt = new JButton("Change Simulator");
            bnt.setEnabled(unlockStatus.get());

            int finalI = i;
            b.addKeyListener(new CheckKeyType());

            bnt.addActionListener(e -> {
                coinsChange(coins,unlockStatus,warning,finalI,b);
            });
            coinContainer.add(a);
            coinContainer.add(b);
            coinContainer.add(bnt);
        }
    }
    public void coinsChange(ArrayList<String[]> coins,AtomicBoolean unlockStatus,TextFactory warning,int finalI,JTextField b){
        int testText = Integer.parseInt(b.getText());
        String type = "coin";
        System.out.println(testText);
        System.out.println(unlockStatus.get());
        if (!unlockStatus.get()) {
            cantChange(warning);
        } else if (testText >= 0 & testText <= 40) {
            canChange(coins, warning, finalI, b, type);
        } else {
            failToChange(warning, type);
        }
    }

    public void showCansChange(ArrayList<String[]> cans,AtomicBoolean unlockStatus,TextFactory warning,JPanel canContainer){
        for (int i = 1; i < cans.size(); i++) {
            TextFactory a = new TextFactory(cans.get(i)[1]);
            JTextField b = new JTextField(cans.get(i)[2], 5);
            JButton bnt = new JButton("Change Simulator");
            bnt.setEnabled(unlockStatus.get());

            int finalI = i;
            b.addKeyListener(new CheckKeyType());

            bnt.addActionListener(e -> {
                cansChange(cans,unlockStatus,warning,finalI,b);
            });

            canContainer.add(a);
            canContainer.add(b);
            canContainer.add(bnt);
        }
    }

    public void cansChange(ArrayList<String[]> cans,AtomicBoolean unlockStatus,TextFactory warning,int finalI,JTextField b){
        int testText = Integer.parseInt(b.getText());
        String type = "can";
        System.out.println(testText);
        if (!unlockStatus.get()) {
            cantChange(warning);
        } else if (testText >= 0 & testText <= 20) {
            canChange(cans, warning, finalI, b, type);
        } else {
            failToChange(warning, type);
        }
    }

    public void cantChange(TextFactory warning){
        warning.setText("The door is closed, you can't change the data");
    }
    public void canChange(ArrayList<String[]> data,TextFactory warning,int finalI,JTextField b,String type){
        String[] record = data.get(finalI);
        String info = "";
        switch (type){
            case "coin":
                record[1] = b.getText();
                info = "coin";
                break;
            case "can":
                record[2] = b.getText();
                info = "drink";
        }
        data.set(finalI, record);
        WriteCSV.writeCSV(data, info);
        warning.setText("Congratulations on your simulated modification of the number of "+type+"s");
    }

    public void failToChange(TextFactory warning,String type){
        switch (type){
            case "coin":
                warning.setText("Wrong Input, please notify the range you can change is [0-40]");
                break;
            case "can":
                warning.setText("Wrong Input, please notify the range you can change is [0-20]");
        }
    }

    //    public MachinerySimulatorPanel(String title, boolean loginStatus) {
//        super(title);
//        ArrayList<String[]> coins = ReadCSV.readCSV("./data/dwd_money_stat.csv");
//        ArrayList<String[]> cans = ReadCSV.readCSV("./data/dwd_drink_info.csv");
//        AtomicBoolean unlockStatus = new AtomicBoolean(loginStatus);
//
//
//        JLabel bigTitle = new TextFactory(title);
//
//        JPanel coinContainer = new T3Panel();
//        JPanel canContainer = new T3Panel();
//        System.out.println(coins.size());
//        int y = 50;
//        TextFactory warning = new TextFactory("No change");
//
//
//        for (int i = 1; i < coins.size(); i++) {
//            TextFactory a = new TextFactory(coins.get(i)[0]);
//            JTextField b = new JTextField(coins.get(i)[1], 5);
//            JButton bnt = new JButton("Change Simulator");
//            bnt.setEnabled(unlockStatus.get());
//
//            int finalI = i;
//            b.addKeyListener(new CheckKeyType());
//
//            bnt.addActionListener(e -> {
//                int testText = Integer.parseInt(b.getText());
//                System.out.println(testText);
//                System.out.println(unlockStatus.get());
//                if (!unlockStatus.get()) {
//                    warning.setText("the door is closed, you can't change the data");
//                } else if (testText >= 0 & testText <= 40) {
//                    String[] record = coins.get(finalI);
//                    record[1] = b.getText();
//                    coins.set(finalI, record);
//                    WriteCSV.writeCSV(coins, "coin");
//                    warning.setText("Congratulations on your simulated modification of the number of coins");
//                } else {
//                    warning.setText("Wrong Input, please notify the range you can change is [0-40]");
//                }
//
//            });
//            coinContainer.add(a);
//            coinContainer.add(b);
//            coinContainer.add(bnt);
//        }
//
//        for (int i = 1; i < cans.size(); i++) {
//            TextFactory a = new TextFactory(cans.get(i)[1]);
//            JTextField b = new JTextField(cans.get(i)[2], 5);
//            JButton bnt = new JButton("Change Simulator");
//            bnt.setEnabled(unlockStatus.get());
//
//            int finalI = i;
//            b.addKeyListener(new CheckKeyType());
//
//            bnt.addActionListener(e -> {
//
//                int testText = Integer.parseInt(b.getText());
//                System.out.println(testText);
//                if (!unlockStatus.get()) {
//                    warning.setText("the door is closed, you can't change the data");
//                } else if (testText >= 0 & testText <= 20) {
//                    String[] record = cans.get(finalI);
//                    record[2] = b.getText();
//                    cans.set(finalI, record);
//                    WriteCSV.writeCSV(cans, "drink");
//                    warning.setText("Congratulations on your simulated modification of the number of drinks");
//                } else {
//                    warning.setText("Wrong Input, please notify the range you can change is [0-20]");
//                }
//
//            });
//
//
//            canContainer.add(a);
//            canContainer.add(b);
//            canContainer.add(bnt);
//        }
//
//        JCheckBox lockBox = new JCheckBox("Door Locked", !loginStatus);
//        System.out.println("现在状态是"+unlockStatus);
//
//        if (loginStatus == true) {
//            lockBox.addItemListener(e -> {
//                unlockStatus.set(!unlockStatus.get());
//                System.out.println("hahaha");
//
//            });
//
//        }
//        JButton backBnt = ButtonFactory.buttonFactory("Back to the Main Panel", "machineryPanel");
//
//        this.add(bigTitle);
//        this.add(new TextFactory("Quantity of Coins", 1));
//        this.add(coinContainer);
//        this.add(new TextFactory("Quantity of Cans", 1));
//        this.add(canContainer);
//        this.add(lockBox);
//        this.add(warning);
//        this.add(backBnt);
//
//        backBnt.addActionListener((e -> {
//            this.dispose();
//            new SimulatorControlPanel("VMCS - Simulator Control Panel", loginStatus);
//
//        }));
//
//    }
}
