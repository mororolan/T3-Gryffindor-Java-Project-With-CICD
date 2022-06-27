package team3.passpasspass.VM.controller;

import team3.passpasspass.VM.controller.GUI.ButtonFactory;
import team3.passpasspass.VM.controller.GUI.T3Frame;
import team3.passpasspass.VM.controller.GUI.TextFactory;
import team3.passpasspass.VM.controller.GUI.VerticalLayout;
import team3.passpasspass.VM.controller.model.CheckKeyType;
import team3.passpasspass.VM.controller.model.NumberObserver;
import team3.passpasspass.VM.controller.model.ReadCSV;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MaintainerPanel extends T3Frame {

    public MaintainerPanel(String title, boolean loginStatus) throws HeadlessException {
        super(title);
        this.setSize(600, 1000);
        this.setLayout(new VerticalLayout());

        ArrayList<String[]> coins = ReadCSV.readCSV("./data/dwd_money_stat.csv");
        ArrayList<String[]> cans = ReadCSV.readCSV("./data/dwd_drink_info.csv");


        Container maintainerContainer = this.getContentPane();
        final boolean[] logStatus = {loginStatus};



        JLabel jlMaintainerTitle = new TextFactory("VMCS - Maintainer Penel", 2);
        JLabel jlMaintainerPassword = new TextFactory("Password:", 2);
        JPasswordField jpfMaintainerPassword = new JPasswordField();
        JButton jbMaintainerPasswordValid = ButtonFactory.buttonFactory("Valid Password", "jbMaintainerPasswordValid");
        JButton jbMaintainerPasswordInvalid = ButtonFactory.buttonFactory("Invalid Password", "jbMaintainerPasswordInvalid"); // 没改




        JLabel jlCoins = new TextFactory("Quantity of Coins Available", 3);

//        int coinsLine = coins.size() - 1;
        JPanel jpCoins = new JPanel();
        jpCoins.setLayout(new GridLayout(coins.size() - 1, 1, 10, 0));
        jpCoins.setBounds(5, 270, 350, 140);

        JTextField jtfTotalCoins = new JTextField(null);
        jtfTotalCoins.setBounds(380, 280, 50, 20);
        jtfTotalCoins.setHorizontalAlignment(JTextField.CENTER);
        jtfTotalCoins.setForeground(Color.yellow);
        jtfTotalCoins.setBackground(Color.black);

        for (int i = 1;i<coins.size();i++) {
            String coinsName = coins.get(i)[0] + "c";
            if (coins.get(i)[0].equals("100")) {
                coinsName = "$1";
            }
            JButton jbCoin = ButtonFactory.buttonFactory(coinsName, "jbCoin");
            jpCoins.add(jbCoin);

            int finalI = i;
            jbCoin.addActionListener(e -> {
                jtfTotalCoins.setText(coins.get(finalI)[1]);
            });
        }

        JLabel jlDrinksAvailable = new TextFactory("Quantity of Drinks Available",4);

        JPanel jpDrinks = new JPanel();
        jpDrinks.setLayout(new GridLayout(cans.size()-1,1,10,0));
        jpDrinks.setBounds(5,470,350,140);

        JTextField jtfDrinksAvailable = new JTextField(null);
        jtfDrinksAvailable.setBounds(380,480,50,20);
        jtfDrinksAvailable.setHorizontalAlignment(JTextField.CENTER);
        jtfDrinksAvailable.setForeground(Color.yellow);
        jtfDrinksAvailable.setBackground(Color.black);

        JLabel jlBrandPrice = new TextFactory("Brand Price",5);

        JTextField jtfBrandPrice = new JTextField(null);
        jtfBrandPrice.setBounds(400,620,80,20);

        jtfBrandPrice.addKeyListener(new CheckKeyType());

        for (int i = 1;i<cans.size();i++) {
            String cansName = cans.get(i)[1] + "c";
            JButton jbDrink = ButtonFactory.buttonFactory(cansName, "jbCoin");
            jpDrinks.add(jbDrink);
            int finalI = i;
            jbDrink.addActionListener(e -> {
                jtfDrinksAvailable.setText(cans.get(finalI)[2]);
                jtfBrandPrice.setText(cans.get(finalI)[3] + "c");
            });
        }

        JButton jbShowTotal = ButtonFactory.buttonFactory("Show Total Cash Held","jbShowTotal");

        JTextField jtfTotalCash = new JTextField();
        jtfTotalCash.setBounds(510,705,80,20);
        jtfTotalCash.setHorizontalAlignment(JTextField.CENTER);
        jtfTotalCash.setForeground(Color.yellow);
        jtfTotalCash.setBackground(Color.black);

        final int[] coinsTotal = {0};

        jbShowTotal.addActionListener(e -> {

            for (int i = 1; i < coins.size(); i++) {
                coinsTotal[0] += Integer.parseInt(coins.get(i)[0]) * Integer.parseInt(coins.get(i)[1]);
                System.out.println(coinsTotal[0] + "钱");

            }
            jtfTotalCash.setText(String.valueOf(coinsTotal[0]));
        });

        JButton jbCollectCash = ButtonFactory.buttonFactory("Press to Collect All Cash","jbCollectCash");
        JLabel jlCollectCash = new TextFactory("Collect Cash:",6);
        JTextField jtfCollectCash = new JTextField("0 c");

        jtfCollectCash.setBounds(430,800,50,20);
        jtfCollectCash.setHorizontalAlignment(JTextField.CENTER);
        jtfCollectCash.setForeground(Color.yellow);
        jtfCollectCash.setBackground(Color.black);

        jbCollectCash.addActionListener(e -> {
            jtfCollectCash.setText(String.valueOf((coinsTotal[0]) + "c"));
            coinsTotal[0] = 0;
            new NumberObserver().maintainerCollectCoins("./Data/dwd_money_stat.csv");
        });

        JButton jbFinished = ButtonFactory.buttonFactory("Press Here when Finished","jbFinished");
        jbFinished.addActionListener(e -> {
            logStatus[0] = false;
            this.dispose();
            new SimulatorControlPanel("VMCS - Simulator Control Panel",logStatus[0]);
        });


        jpfMaintainerPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char[] passwordEntered = jpfMaintainerPassword.getPassword();
                String a = String.valueOf(passwordEntered);
                String maintainerPassword = "123456";
                jbMaintainerPasswordValid.setBackground(Color.gray);
                jbMaintainerPasswordInvalid.setForeground(Color.gray);
                jbMaintainerPasswordInvalid.setBackground(Color.red);
                if (a.equals(maintainerPassword)) {
                    logStatus[0] = true;
                    jbMaintainerPasswordValid.setBackground(Color.green);
                    jbMaintainerPasswordInvalid.setForeground(Color.black);
                    jbMaintainerPasswordInvalid.setBackground(Color.gray);
                }
                jlCoins.setVisible(logStatus[0]);
                jpCoins.setVisible(logStatus[0]);
                jtfTotalCoins.setVisible(logStatus[0]);
                jlDrinksAvailable.setVisible(logStatus[0]);
                jpDrinks.setVisible(logStatus[0]);
                jtfDrinksAvailable.setVisible(logStatus[0]);
                jlBrandPrice.setVisible(logStatus[0]);
                jtfBrandPrice.setVisible(logStatus[0]);
                jbShowTotal.setVisible(logStatus[0]);
                jtfTotalCash.setVisible(logStatus[0]);
                jbCollectCash.setVisible(logStatus[0]);
                jlCollectCash.setVisible(logStatus[0]);
                jtfCollectCash.setVisible(logStatus[0]);
                jbFinished.setVisible(logStatus[0]);
                maintainerContainer.add(jlCoins);
                maintainerContainer.add(jpCoins);
                maintainerContainer.add(jtfTotalCoins);
                maintainerContainer.add(jlDrinksAvailable);
                maintainerContainer.add(jpDrinks);
                maintainerContainer.add(jtfDrinksAvailable);
                maintainerContainer.add(jlBrandPrice);
                maintainerContainer.add(jtfBrandPrice);
                maintainerContainer.add(jbShowTotal);
                maintainerContainer.add(jtfTotalCash);
                maintainerContainer.add(jbCollectCash);
                maintainerContainer.add(jlCollectCash);
                maintainerContainer.add(jtfCollectCash);
                maintainerContainer.add(jbFinished);
            }
        });

//        jlBrandPrice.addKeyListener();


        maintainerContainer.add(jlMaintainerTitle);
        maintainerContainer.add(jlMaintainerPassword);
        maintainerContainer.add(jpfMaintainerPassword);
        maintainerContainer.add(jbMaintainerPasswordValid);
        maintainerContainer.add(jbMaintainerPasswordInvalid);


    }
}
