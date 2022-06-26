package team3.passpasspass.VM.controller;

import sun.applet.Main;
import team3.passpasspass.VM.controller.GUI.*;
import team3.passpasspass.VM.controller.model.ReadCSV;

import javax.swing.*;
import java.util.ArrayList;

// TODO
public class MachinerySimulatorPanel extends T3Frame {
    public MachinerySimulatorPanel(String title, boolean loginStatus) {
        super(title);
        JLabel bigTitle = new TextFactory(title);
        ArrayList<String[]> coins = ReadCSV.readCSV("./data/dwd_money_stat.csv");
        ArrayList<String[]> cans = ReadCSV.readCSV("./data/dwd_drink_info.csv");

        this.add(bigTitle);
        this.add(new TextFactory("Quantity of Coins",1));
//        Container mainContainer = this.getContentPane();
//        Container mainContainer = this.getContentPane(new T3Panel());
//        this.setContentPane(new T3Panel());
//        mainContainer.setLayout(new TwoColumnLayout());
//        int x = 50;
//        int y = 300;
        TextFactory a = new TextFactory(coins.get(0)[0]);
        JTextField b = new JTextField(coins.get(0)[1]);
        JButton bnt = new JButton("new");
        bnt.addActionListener(e -> {
            System.out.println(b.getText());
        });

//        for (int i = 0; i < coins.size(); i++) {
//            TextFactory a = new TextFactory(coins.get(i)[0]);
//            JTextField b = new JTextField(coins.get(i)[1]);
//            JButton bnt = new JButton("new");
//            bnt.addActionListener(e -> {
//                System.out.println(b.getText());
//            });
//            this.add(a,0);
//            this.add(b,1);
//            a.setBounds(x,y,100,50);
//            b.setBounds(x+50,y,100,50);
//            x+=100;
//            y+=50;
//
//        }

//        this.add(new TextFactory("Quantity of Cans",1));
//
//        for (int i = 1; i < cans.size(); i++) {
//            this.add(new TextFactory(cans.get(i)[1], 0));
//            this.add(new JTextField(cans.get(i)[2],1));
//        }
//
//        JCheckBox lockBox = new JCheckBox("Door Locked",!loginStatus);
//        this.add(lockBox);

        // set back
        JButton button = ButtonFactory.buttonFactory("Back to the Main Panel","machineryPanel");
        this.add(button);
        button.addActionListener((e -> {
            this.dispose();
            new SimulatorControlPanel("VMCS - Simulator Control Panel",loginStatus);

        }));

    }
}
