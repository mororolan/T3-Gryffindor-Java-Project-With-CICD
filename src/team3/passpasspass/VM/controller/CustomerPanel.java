package team3.passpasspass.VM.controller;

import team3.passpasspass.VM.controller.GUI.*;
import team3.passpasspass.VM.controller.model.ReadCSV;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.util.ArrayList;

public class CustomerPanel extends T3Frame {

    JPanel contentPane;
    ArrayList<String[]> coins;
    ArrayList<String[]> cans;
    ArrayList<JButton> btnCoins;
    ArrayList<JButton> btnCans;
    ArrayList<JLabel> lblDrinkPrice;
    ArrayList<JLabel> lblDrinkCnt;

    JPanel panelTop;
    JPanel panelTop_Top;
    JPanel panelTop_Center;
    JPanel panelCenter;
    JPanel panelBottom;

    //Top_Top
    JLabel lblTitle;
    //Top_Center
    JButton btnInvalid;
    JLabel lblEnterCoins;
    //Top_Bottom
    JPanel panelTop_Bottom;
    JLabel lblWarningInvalid;
    //Center_Top
    JLabel lblTotalMoney;
    JLabel lblTotalMoneyNum;
    //Center_Center
    JLabel lblNoChange;
    //Bottom_Top
    JButton btnTerminate;
    //Bottom_Center
    JLabel lblCollectCoins;
    JLabel lblCollectCoinsNum;
    //Bottom_Bottom
    JLabel lblCollectCan;
    JLabel lblCollectCanInfo;

    boolean haveDispensed;

    /**
     * Create the frame.
     */
    public CustomerPanel(String title) {
        super(title);
        haveDispensed = false;
        coins = ReadCSV.readCSV("./data/dwd_money_stat.csv");
        cans = ReadCSV.readCSV("./data/dwd_drink_info.csv");
        btnCoins = new ArrayList<>();
        btnCans = new ArrayList<>();
        lblDrinkPrice = new ArrayList<>();
        lblDrinkCnt = new ArrayList<>();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new AfAnyWhereLayout());
        setContentPane(contentPane);

        showTop();
        showCenter();
        showBottom();

        addListener();

        addTop();
        addCenter();
        addBottom();

        contentPane.add(panelTop, new AfMargin().TOP_CENTER);
        contentPane.add(panelCenter, new AfMargin(130,-1,180,-1));
        contentPane.add(panelBottom, new AfMargin(500,-1,5,-1));
        setVisible(true);
    }

    public void showTop(){
        panelTop = new JPanel();
        panelTop.setPreferredSize(new Dimension(600,130));
        panelTop.setLayout(new AfAnyWhereLayout());
        //TOP
        panelTop_Top = new JPanel();
        panelTop_Top.setPreferredSize(new Dimension(600,30));
        panelTop_Top.setLayout(new AfAnyWhereLayout());
        lblTitle = new TextFactory("Soft Drink Dispenser",8);
        //CENTER
        panelTop_Center = new JPanel();
        panelTop_Center.setPreferredSize(new Dimension(600,70));
        panelTop_Center.setLayout(new AfAnyWhereLayout());
        lblEnterCoins = new TextFactory("Enter Coins Here",7);
        for (int i=1; i<coins.size(); i++){
            btnCoins.add(ButtonFactory.buttonFactory(coins.get(i)[0]+"c","jbEnterCoins"));
        }
        btnInvalid = ButtonFactory.buttonFactory("invalid","jbEnterCoins");
        //BOTTOM
        panelTop_Bottom = new JPanel();
        panelTop_Bottom.setPreferredSize(new Dimension(600,30));
        panelTop_Bottom.setLayout(new AfAnyWhereLayout());
        lblWarningInvalid = new TextFactory("Invalid Coin",10);
    }

    public void showCenter(){
        panelCenter = new JPanel();
        panelCenter.setPreferredSize(new Dimension(600,390));
        panelCenter.setLayout(new AfAnyWhereLayout());
        //TOP
        lblTotalMoney = new TextFactory("Total Money Inserted:",7);
        lblTotalMoneyNum = new TextFactory("0 c",11);
        //CENTER
        for (int i=1; i<cans.size(); i++){
            btnCans.add(ButtonFactory.buttonFactory(cans.get(i)[1],"jbChooseDrink"));
        }
        for (int i=1; i<cans.size(); i++){
            lblDrinkPrice.add(new TextFactory(cans.get(i)[3]+"c",11));
        }
        for (int i=1; i<cans.size(); i++){
            if(Integer.parseInt(cans.get(i)[2])>0)
                lblDrinkCnt.add(new TextFactory("Not in Stock",10));
            else
                lblDrinkCnt.add(new TextFactory("Not in Stock",9));
        }
        lblNoChange = new TextFactory("No Change Available",10);
    }

    public void showBottom(){
        panelBottom = new JPanel();
        panelBottom.setPreferredSize(new Dimension(600,180));
        panelBottom.setLayout(new AfAnyWhereLayout());
        //TOP
        btnTerminate = ButtonFactory.buttonFactory("Terminate and Return Cash","jbTerminate");
        if(!haveDispensed)
            btnTerminate.setEnabled(false);
        else
            btnTerminate.setEnabled(true);
        //CENTER
        lblCollectCoins = new TextFactory("Collect Coins:",7);
        lblCollectCoinsNum = new TextFactory("0 c",11);
        //BOTTOM
        lblCollectCan = new TextFactory("Collect Can Here:",7);
        lblCollectCanInfo = new TextFactory("NO CAN",11);
    }

    public void addListener(){
        for (int i = 0; i < btnCoins.size(); i++){
            int finalI = i;
            btnCoins.get(i).addActionListener(e -> {
                addCoin(Integer.parseInt(lblTotalMoneyNum.getText()), Integer.parseInt(coins.get(finalI)[0]));
            });
        }
    }

    public void addCoin(int total, int newCoin){
        total += newCoin;
        lblTotalMoneyNum.setText(String.valueOf(total));
    }

    public void addTop(){
        panelTop.add(panelTop_Top,new AfMargin().TOP_CENTER);
        panelTop.add(panelTop_Center,new AfMargin(30, -1, -1, -1));
        panelTop.add(panelTop_Bottom,new AfMargin().BOTTOM_CENTER);
        //TOP
        panelTop_Top.add(lblTitle,new AfMargin().CENTER);
        //CENTER
        panelTop_Center.add(lblEnterCoins,new AfMargin().TOP_LEFT);
        for (int i=0; i<btnCoins.size(); i++){
            panelTop_Center.add(btnCoins.get(i),new AfMargin(35,15+i*95,-1,-1));
        }
        panelTop_Center.add(btnInvalid,new AfMargin(35,490,-1,-1));
        //BOTTOM
        panelTop_Bottom.add(lblWarningInvalid,new AfMargin().BOTTOM_CENTER);
    }

    public void addCenter(){
        //TOP
        panelCenter.add(lblTotalMoney, new AfMargin(5,150,-1,-1));
        panelCenter.add(lblTotalMoneyNum, new AfMargin(10,340,-1,-1));
        //CENTER
        for (int i=0; i<btnCans.size(); i++){
            panelCenter.add(btnCans.get(i),new AfMargin(40+i*45,15,-1,-1));
        }
        for (int i=0; i<lblDrinkPrice.size(); i++){
            panelCenter.add(lblDrinkPrice.get(i),new AfMargin(50+i*45,270,-1,-1));
        }
        for (int i=0; i<lblDrinkCnt.size(); i++){
            panelCenter.add(lblDrinkCnt.get(i),new AfMargin(52+i*45,420,-1,-1));
        }
        panelCenter.add(lblNoChange, new AfMargin(320,240,-1,-1));
        //BOTTOM
    }

    public void addBottom(){
        //TOP
        panelBottom.add(btnTerminate, new AfMargin(5,190,-1,-1));
        //CENTER
        panelBottom.add(lblCollectCoins, new AfMargin(50,150,-1,-1));
        panelBottom.add(lblCollectCoinsNum, new AfMargin(55,330,-1,-1));
        //BOTTOM
        panelBottom.add(lblCollectCan, new AfMargin(90,150,-1,-1));
        panelBottom.add(lblCollectCanInfo, new AfMargin(95,330,-1,-1));
    }
}
