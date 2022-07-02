package team3.Gryffindor.VM.panel;

import team3.Gryffindor.VM.GUI.*;
import team3.Gryffindor.VM.GUI.FactoryPattern.ButtonFactory;
import team3.Gryffindor.VM.GUI.TextFactory;
import team3.Gryffindor.VM.GUI.LayoutStrategy.VerticalLayout;
import team3.Gryffindor.VM.model.CheckKeyType;
import team3.Gryffindor.VM.model.ReadCSV;
import team3.Gryffindor.VM.model.WriteCSV;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

// TODO
public class MachinerySimulatorPanel extends T3Frame {

    public MachinerySimulatorPanel(String title, boolean loginStatus) {
        super(title);
        this.setLayout(new VerticalLayout());
        ArrayList<String[]> coins = ReadCSV.readCSV("./data/dwd_money_stat.csv");
        ArrayList<String[]> cans = ReadCSV.readCSV("./data/dwd_drink_info.csv");
        AtomicBoolean unlockStatus = new AtomicBoolean(loginStatus);


        JLabel bigTitle = new TextFactory(title);

        JPanel coinContainer = new T3Panel();
        coinContainer.setLayout(new GridLayout(coins.size() - 1, 1, 40, 1));
        coinContainer.setPreferredSize(new Dimension(350,80));

        JPanel canContainer = new T3Panel();
        canContainer.setLayout(new GridLayout(cans.size() - 1, 1, 40, 1));
        canContainer.setPreferredSize(new Dimension(350,100));

        TextFactory warning = new TextFactory("No change",14);

        showCoinsChange(coins, unlockStatus, warning, coinContainer);
        showCansChange(cans, unlockStatus, warning, canContainer);

        JCheckBox lockBox = new JCheckBox("Door Locked", !loginStatus);
        lockBox.setEnabled(unlockStatus.get());

        if (loginStatus == true) {
            lockBox.addItemListener(e -> {
                changeUnlockStatus(unlockStatus);
            });
        }


        JButton backBnt = ButtonFactory.addButton("Back to the Main Panel", "machineryPanel");



        this.add(bigTitle);
        this.add(new TextFactory("Quantity of Coins", 1));
        this.add(coinContainer);
        this.add(new TextFactory("Quantity of Cans", 1));
        this.add(canContainer);
        this.add(lockBox);
        this.add(warning);
        this.add(backBnt);

        backBnt.addActionListener((e -> {
            openControllerPanel(loginStatus);
        }));

        setVisible(true);

    }

    public String openControllerPanel(boolean loginStatus){
        this.dispose();
        return new SimulatorControlPanel("VMCS - Simulator Control Panel", loginStatus).getTitle();
    }
    public void showCoinsChange(ArrayList<String[]> coins,AtomicBoolean unlockStatus,TextFactory warning,JPanel coinContainer){
        for (int i = 1; i < coins.size(); i++) {
            String coinsName = coins.get(i)[0] + "c";
            if (coins.get(i)[0].equals("100")) {
                coinsName = "$1";
            }
            TextFactory a = new TextFactory(coinsName,13);
            JTextField b = new JTextField(coins.get(i)[1], 5);
            b.setBackground(Color.PINK);
            JButton bnt = new JButton("Change Simulator");
            bnt.setEnabled(unlockStatus.get());

            int finalI = i;
            b.addKeyListener(new CheckKeyType());

            bnt.addActionListener(e -> {
                int testText = Integer.parseInt(b.getText());
                coinsChange(coins,unlockStatus,warning,finalI,testText);
            });
            coinContainer.add(a);
            coinContainer.add(b);
            coinContainer.add(bnt);
        }
    }
    public void coinsChange(ArrayList<String[]> coins,AtomicBoolean unlockStatus,TextFactory warning,int finalI,int testText){
        String type = "coin";
        if (!unlockStatus.get()) {
            cantChange(warning);
        } else if (testText >= 0 & testText <= 40) {
            canChange(coins, warning, finalI, testText, type);
        } else {
            failToChange(warning, type);
        }
    }

    public void showCansChange(ArrayList<String[]> cans,AtomicBoolean unlockStatus,TextFactory warning,JPanel canContainer){
        for (int i = 1; i < cans.size(); i++) {
            TextFactory a = new TextFactory(cans.get(i)[1],13);
            JTextField b = new JTextField(cans.get(i)[2], 5);
            b.setBackground(Color.PINK);
            JButton bnt = new JButton("Change Simulator");
            bnt.setEnabled(unlockStatus.get());

            int finalI = i;
            b.addKeyListener(new CheckKeyType());


            bnt.addActionListener(e -> {
                int testText = Integer.parseInt(b.getText());
                cansChange(cans,unlockStatus,warning,finalI,testText);
            });

            canContainer.add(a);
            canContainer.add(b);
            canContainer.add(bnt);
        }
    }

    public void cansChange(ArrayList<String[]> cans,AtomicBoolean unlockStatus,TextFactory warning,int finalI,int testText){
        String type = "can";
        if (!unlockStatus.get()) {
            cantChange(warning);
        } else if (testText >= 0 & testText <= 20) {
            canChange(cans, warning, finalI, testText, type);
        } else {
            failToChange(warning, type);
        }
    }

    public void cantChange(TextFactory warning){
        warning.setText("The door is closed, you can't change the data");
    }
    public void canChange(ArrayList<String[]> data,TextFactory warning,int finalI,int testText,String type){
        String[] record = data.get(finalI);
        String info = "";

        switch (type){
            case "coin":
                record[1] = String.valueOf(testText);
                info = "coin";
                break;
            case "can":
                record[2] = String.valueOf(testText);
                info = "drink";
        }
        data.set(finalI, record);
        for (int i = 0; i < data.size(); i++) {

            WriteCSV.writeCSV(data, info);
        }

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

    public void changeUnlockStatus(AtomicBoolean unlockStatus){
        unlockStatus.set(!unlockStatus.get());
    }

}
