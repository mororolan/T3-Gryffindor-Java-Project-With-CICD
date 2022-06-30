package team3.Gryffindor.VM.panel;

import team3.Gryffindor.VM.GUI.*;
import team3.Gryffindor.VM.model.CheckKeyType;
import team3.Gryffindor.VM.model.NumberObserver;
import team3.Gryffindor.VM.model.ReadCSV;
import team3.Gryffindor.VM.model.WriteCSV;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class MaintainerPanel extends T3Frame {

    public MaintainerPanel(String title, boolean loginStatus) throws HeadlessException {

        super(title);
        AtomicInteger clickCoinBnt = new AtomicInteger();
        AtomicInteger clickDrinkBnt = new AtomicInteger();
        AtomicReference<ArrayList<String[]>> coins = new AtomicReference<>(ReadCSV.readCSV("./data/dwd_money_stat.csv"));
        AtomicReference<ArrayList<String[]>> cans = new AtomicReference<>(ReadCSV.readCSV("./data/dwd_drink_info.csv"));
        final boolean[] logStatus = {loginStatus};

        this.setSize(600, 1000);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new AfAnyWhereLayout());
        setContentPane(contentPane);

        //-------------------Top------------------------//
        JPanel panelTop = new JPanel();
        panelTop.setPreferredSize(new Dimension(600,150));
        panelTop.setLayout(new AfAnyWhereLayout());
        //----Top_Top----//
        JPanel panelTop_Top = new JPanel();
        panelTop_Top.setPreferredSize(new Dimension(600,30));
        panelTop_Top.setLayout(new AfAnyWhereLayout());
        JLabel jlMaintainerTitle = new TextFactory("VMCS - Maintainer Penel", 15);
        //----Top_Bottom----//
        JPanel panelTop_Bottom = new JPanel();
        panelTop_Bottom.setPreferredSize(new Dimension(600,120));
        panelTop_Bottom.setLayout(new AfAnyWhereLayout());
        JLabel jlMaintainerPassword = new TextFactory("Password:", 2);
        JPasswordField jpfMaintainerPassword = new JPasswordField();
        jpfMaintainerPassword.setPreferredSize(new Dimension(100,30));
        JButton jbMaintainerPasswordValid = ButtonFactory.changeButton("Valid Password", "jbMaintainerPasswordValid");
        JButton jbMaintainerPasswordInvalid = ButtonFactory.changeButton("Invalid Password", "jbMaintainerPasswordInvalid"); // 没改
        jbMaintainerPasswordValid.setPreferredSize(new Dimension(200,30));
        jbMaintainerPasswordInvalid.setPreferredSize(new Dimension(200,30));

        //----Add Top----//
        panelTop.add(panelTop_Top, new AfMargin().TOP_CENTER);
        panelTop.add(panelTop_Bottom, new AfMargin().BOTTOM_CENTER);

        panelTop_Top.add(jlMaintainerTitle, new AfMargin().CENTER);
        panelTop_Bottom.add(jlMaintainerPassword, new AfMargin(25,120,-1,-1));
        panelTop_Bottom.add(jpfMaintainerPassword, new AfMargin(42,320,-1,-1));
        panelTop_Bottom.add(jbMaintainerPasswordValid, new AfMargin(90,85,-1,-1));
        panelTop_Bottom.add(jbMaintainerPasswordInvalid, new AfMargin(90,-1,-1,85));

        //----------------------------TOP END-------------------------------//


        //----------------------------CENTER--------------------------------//
        JPanel panelCenter = new JPanel();
        panelCenter.setPreferredSize(new Dimension(600,540));
        panelCenter.setLayout(new AfAnyWhereLayout());
        //------CENTER TOP------//
        JPanel panelCenter_Top = new JPanel();
        panelCenter_Top.setPreferredSize(new Dimension(600,250));
        panelCenter_Top.setLayout(new AfAnyWhereLayout());
        JLabel jlCoins = new TextFactory("Quantity of Coins Available", 3);
        jlCoins.setPreferredSize(new Dimension(200,30));

        //------CENTER jpCoins and CoinsTextField-----//
        JPanel jpCoins = new JPanel();
        jpCoins.setLayout(new GridLayout(coins.get().size() - 1, 1, 10, 0));
        jpCoins.setPreferredSize(new Dimension(280, 200));

        JTextField jtfTotalCoins = new JTextField(null);
        jtfTotalCoins.setPreferredSize(new Dimension(60, 20));
        jtfTotalCoins.setHorizontalAlignment(JTextField.CENTER);
        jtfTotalCoins.setForeground(Color.yellow);
        jtfTotalCoins.setBackground(Color.black);
        jtfTotalCoins.addKeyListener(new CheckKeyType());
        jtfTotalCoins.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char checkAns = e.getKeyChar();
                String text;
                super.keyTyped(e);
                if(checkAns == KeyEvent.VK_ENTER) {
                    text=jtfTotalCoins.getText();

                    checkPressEnter(coins.get(),text,clickCoinBnt.get(),"coin");
                }}});





        for (int i = 1; i< coins.get().size(); i++) {
            String coinsName = coins.get().get(i)[0] + "c";
            if (coins.get().get(i)[0].equals("100")) {
                coinsName = "$1";
            }
            JButton jbCoin = ButtonFactory.changeButton(coinsName, "jbCoin");
            jpCoins.add(jbCoin);

            int finalI = i;
            jbCoin.addActionListener(e -> {
                coins.set(ReadCSV.readCSV("./data/dwd_money_stat.csv"));
                jtfTotalCoins.setText(coins.get().get(finalI)[1]);
                clickCoinBnt.set(finalI);

            });
        }

        //-----------CENTER_CENTER----------//
        JPanel panelCenter_Center = new JPanel();
        panelCenter_Center.setPreferredSize(new Dimension(600,230));
        panelCenter_Center.setLayout(new AfAnyWhereLayout());
        JLabel jlDrinksAvailable = new TextFactory("Quantity of Drinks Available",4);
        jlDrinksAvailable.setPreferredSize(new Dimension(200,30));

        //------CENTER jpDrinks and DrinksTextField-----//
        JPanel jpDrinks = new JPanel();
        jpDrinks.setLayout(new GridLayout(cans.get().size()-1,1,10,0));
        jpDrinks.setPreferredSize(new Dimension(280, 200));

        JTextField jtfDrinksAvailable = new JTextField(null);
        jtfDrinksAvailable.setPreferredSize(new Dimension(60, 20));
        jtfDrinksAvailable.setHorizontalAlignment(JTextField.CENTER);
        jtfDrinksAvailable.setForeground(Color.yellow);
        jtfDrinksAvailable.setBackground(Color.black);
        jtfDrinksAvailable.addKeyListener(new CheckKeyType());
        jtfDrinksAvailable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char checkAns = e.getKeyChar();
                String text;
                super.keyTyped(e);
                if(checkAns == KeyEvent.VK_ENTER) {
                    text=jtfDrinksAvailable.getText();
                    checkPressEnter(cans.get(),text,clickDrinkBnt.get(),"drink");
                }}});

        JLabel jlBrandPrice = new TextFactory("Brand Price",5);
        JTextField jtfBrandPrice = new JTextField(null);
        jtfBrandPrice.setPreferredSize(new Dimension(80, 20));
        jtfBrandPrice.addKeyListener(new CheckKeyType());
        jtfBrandPrice.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char checkAns = e.getKeyChar();
                String text;
                super.keyTyped(e);
                if(checkAns == KeyEvent.VK_ENTER) {
                    text=jtfBrandPrice.getText();
                    checkPressEnter(cans.get(),text,clickDrinkBnt.get(),"drink_price");
                }}});




        for (int i = 1; i< cans.get().size(); i++) {
            String cansName = cans.get().get(i)[1];
            JButton jbDrink = ButtonFactory.changeButton(cansName, "jbCoin");
            jpDrinks.add(jbDrink);
            int finalI = i;
            jbDrink.addActionListener(e -> {
                cans.set(ReadCSV.readCSV("./data/dwd_drink_info.csv"));
                jtfDrinksAvailable.setText(cans.get().get(finalI)[2]);
                jtfBrandPrice.setText(cans.get().get(finalI)[3]);
                clickDrinkBnt.set(finalI);
            });
        }

        //-----Add Center-----//
        panelCenter.add(panelCenter_Top, new AfMargin(0,0,-1,-1));
        panelCenter.add(panelCenter_Center, new AfMargin(250,0,-1,-1));

        panelCenter_Top.add(jlCoins, new AfMargin(0,10,-1,-1));
        panelCenter_Top.add(jpCoins, new AfMargin(30,10,-1,-1));
        panelCenter_Top.add(jtfTotalCoins, new AfMargin(40,310,-1,-1));

        panelCenter_Center.add(jlDrinksAvailable, new AfMargin(0,10,-1,-1));
        panelCenter_Center.add(jpDrinks, new AfMargin(30,10,-1,-1));
        panelCenter_Center.add(jtfDrinksAvailable, new AfMargin(40,310,-1,-1));
        panelCenter.add(jlBrandPrice, new AfMargin(475,110,-1,-1));
        panelCenter.add(jtfBrandPrice, new AfMargin(495,-1,-1,225));
        //----------------------------CENTER END--------------------------------//


        //----------------------------BOTTOM--------------------------------//
        JPanel panelBottom = new JPanel();
        panelBottom.setPreferredSize(new Dimension(600,200));
        panelBottom.setLayout(new AfAnyWhereLayout());

        //----Bottom Top----//
        JPanel panelBottom_Top = new JPanel();
        panelBottom_Top.setPreferredSize(new Dimension(600,50));
        panelBottom_Top.setLayout(new AfAnyWhereLayout());
        JButton jbShowTotal = ButtonFactory.changeButton("Show Total Cash Held","jbShowTotal");
        jbShowTotal.setPreferredSize(new Dimension(200,30));

        JTextField jtfTotalCash = new JTextField();
        jtfTotalCash.setPreferredSize(new Dimension(80,20));
        jtfTotalCash.setHorizontalAlignment(JTextField.CENTER);
        jtfTotalCash.setForeground(Color.yellow);
        jtfTotalCash.setBackground(Color.black);
        jtfTotalCash.addKeyListener(new CheckKeyType());

        final int[] coinsTotal = {0};

        jbShowTotal.addActionListener(e -> {
            coins.set(ReadCSV.readCSV("./data/dwd_money_stat.csv"));
            coinsTotal[0] = 0;//Peijun
            for (int i = 1; i < coins.get().size(); i++) {
                coinsTotal[0] += Integer.parseInt(coins.get().get(i)[0]) * Integer.parseInt(coins.get().get(i)[1]);


            }
            jtfTotalCash.setText(String.valueOf(coinsTotal[0])+"c");
        });

        //----Bottom Center----//
        JPanel panelBottom_Center = new JPanel();
        panelBottom_Center.setPreferredSize(new Dimension(600,100));
        panelBottom_Center.setLayout(new AfAnyWhereLayout());
        JButton jbCollectCash = ButtonFactory.changeButton("Press to Collect All Cash","jbCollectCash");
        jbCollectCash.setPreferredSize(new Dimension(300,30));
        JLabel jlCollectCash = new TextFactory("Collect Cash:",6);
        jlCollectCash.setPreferredSize(new Dimension(100,30));
        JTextField jtfCollectCash = new JTextField("0 c");
        jtfCollectCash.setPreferredSize(new Dimension(80,20));

        jtfCollectCash.setBounds(430,800,50,20);
        jtfCollectCash.setHorizontalAlignment(JTextField.CENTER);
        jtfCollectCash.setForeground(Color.yellow);
        jtfCollectCash.setBackground(Color.black);
        jtfCollectCash.addKeyListener(new CheckKeyType());

        jbCollectCash.addActionListener(e -> {
            jtfCollectCash.setText(String.valueOf((coinsTotal[0]) + "c"));
            coinsTotal[0] = 0;
            new NumberObserver().maintainerCollectCoins("./Data/dwd_money_stat.csv");
        });

        JButton btnMachinery = ButtonFactory.changeButton("Go to the Machinery Panel","jbFinished");
        btnMachinery.setPreferredSize(new Dimension(300,30));
        btnMachinery.addActionListener(e -> {
            this.dispose();
            new MachinerySimulatorPanel("VMCS - Machinery Panel", true);
        });

        //----Bottom Bottom----//
        JPanel panelBottom_Bottom = new JPanel();
        panelBottom_Bottom.setPreferredSize(new Dimension(600,100));
        panelBottom_Bottom.setLayout(new AfAnyWhereLayout());
        JButton jbFinished = ButtonFactory.changeButton("Press Here when Finished","jbFinished");
        jbFinished.setPreferredSize(new Dimension(300,30));
        jbFinished.addActionListener(e -> {
            logStatus[0] = false;
            this.dispose();
            new SimulatorControlPanel("VMCS - Simulator Control Panel",logStatus[0]);
        });

        //----Add Bottom----//
        panelBottom.add(panelBottom_Top, new AfMargin().TOP_CENTER);
        panelBottom.add(panelBottom_Center, new AfMargin().CENTER);
        panelBottom.add(panelBottom_Bottom, new AfMargin().BOTTOM_CENTER);

        panelBottom_Top.add(jbShowTotal, new AfMargin(0,120,-1,-1));
        panelBottom_Top.add(jtfTotalCash, new AfMargin(5,340,-1,-1));
        panelBottom_Center.add(jbCollectCash, new AfMargin().TOP_CENTER);
        panelBottom_Center.add(jlCollectCash, new AfMargin(40,200,-1,-1));
        panelBottom_Center.add(jtfCollectCash, new AfMargin(45,310,-1,-1));
        panelBottom_Center.add(jbFinished, new AfMargin().BOTTOM_LEFT);
        panelBottom_Center.add(btnMachinery,new AfMargin().BOTTOM_RIGHT);








        panelCenter.setVisible(logStatus[0]);
        panelBottom.setVisible(logStatus[0]);

        jpfMaintainerPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char passwordChar = e.getKeyChar();
                super.keyTyped(e);
                char[] passwordEntered = jpfMaintainerPassword.getPassword();
                String a = String.valueOf(passwordEntered);
                String maintainerPassword = "123abc";
                if(passwordChar == KeyEvent.VK_ENTER) {
                    if(passwordEntered.length == 6 && a.equals(maintainerPassword)) {
                        logStatus[0] = true;
                        jbMaintainerPasswordValid.setBackground(Color.green);
                        jbMaintainerPasswordInvalid.setForeground(Color.black);
                        jbMaintainerPasswordInvalid.setBackground(Color.gray);
                    }
                    else{
                        logStatus[0] = false;
                        jbMaintainerPasswordValid.setBackground(Color.gray);
                        jbMaintainerPasswordInvalid.setForeground(Color.gray);
                        jbMaintainerPasswordInvalid.setBackground(Color.red);
                    }
                };
                panelCenter.setVisible(logStatus[0]);
                panelBottom.setVisible(logStatus[0]);
            }
        });

        //Last, add panels
        contentPane.add(panelTop, new AfMargin().TOP_CENTER);
        contentPane.add(panelCenter, new AfMargin().CENTER_LEFT);
        contentPane.add(panelBottom, new AfMargin().BOTTOM_CENTER);



        setVisible(true);

    }
    public void checkPressEnter(ArrayList<String[]> writeList, String num, int index, String type) {
        String[] record = writeList.get(index);
        if (type.equals("coin")) {
            record[1]=num;
        } else if (type.equals("drink")){
            record[2]=num;
        } else {
            record[3]=num;
            type = "drink";
        }
        writeList.set(index, record);
        WriteCSV.writeCSV(writeList, type);
    }
}
