package team3.passpasspass.VM.controller;

import team3.passpasspass.VM.controller.GUI.*;
import team3.passpasspass.VM.controller.model.ReadCSV;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

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
    JLabel lblEnoughCoins;
    //Top_Bottom
    JPanel panelTop_Bottom;
    JLabel lblWarningInvalid;
    //Center_Top
    JLabel lblTotalMoney;
    JLabel lblTotalMoneyNum;
    //Center_Center
    JLabel lblNoChange;
    JLabel lblChangeSuccess;
    //Bottom_Top
    JLabel lblStartDispensing;
    JLabel lblPurchaseSuccess;
    JButton btnTerminate;
    //Bottom_Center
    JLabel lblCollectCoins;
    JLabel lblCollectCoinsNum;
    //Bottom_Bottom
    JLabel lblCollectCan;
    JLabel lblCollectCanInfo;

    boolean haveDispensed;
    boolean isEnough;

    int totalCoins;//The sum of the value of all coins the customer has put in
    int shouldPay;//The price of the drink selected by the customer
    ArrayList<Integer> justCoin;//The value of the coin the customer just put in

    /**
     * Create the frame.
     */
    public CustomerPanel(String title) {
        super(title);
        haveDispensed = false;
        isEnough = false;
        totalCoins = 0;
        shouldPay = 0;
        coins = ReadCSV.readCSV("./data/dwd_money_stat.csv");
        cans = ReadCSV.readCSV("./data/dwd_drink_info.csv");
        btnCoins = new ArrayList<>();
        btnCans = new ArrayList<>();
        lblDrinkPrice = new ArrayList<>();
        lblDrinkCnt = new ArrayList<>();
        justCoin = new ArrayList<>();

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
        contentPane.add(panelBottom, new AfMargin(470,-1,5,-1));
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
        lblEnoughCoins = new TextFactory("Coins Entered is Enough",7);
        for (int i=1; i<coins.size(); i++){
            btnCoins.add(ButtonFactory.buttonFactory(coins.get(i)[0]+"c","jbEnterCoins"));
        }
        btnInvalid = ButtonFactory.buttonFactory("invalid","jbEnterCoins");
        btnInvalid.setEnabled(false);
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
        lblChangeSuccess = new TextFactory("Change Successfully", 7);
    }

    public void showBottom(){
        panelBottom = new JPanel();
        panelBottom.setPreferredSize(new Dimension(600,180));
        panelBottom.setLayout(new AfAnyWhereLayout());
        //TOP
        lblStartDispensing = new TextFactory("Start Dispensing",2);
        lblPurchaseSuccess = new TextFactory("Purchase Successfully",2);
        lblPurchaseSuccess.setPreferredSize(new Dimension(300,60));
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
            int finalI = i+1;
            btnCoins.get(i).addActionListener(e -> {
                try {
                    addCoin(Integer.parseInt(coins.get(finalI)[0]));
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }

        btnInvalid.addActionListener(e -> {
            addCheckInvalid();
        });

        for (int i = 0; i < btnCans.size(); i++){
            int finalI = i+1;
            btnCans.get(i).addActionListener(e -> {
                addChooseDrink(Integer.parseInt(cans.get(finalI)[3]));
            });
        }
    }

    public void addTop(){
        panelTop.add(panelTop_Top,new AfMargin().TOP_CENTER);
        panelTop.add(panelTop_Center,new AfMargin(30, -1, -1, -1));
        panelTop.add(panelTop_Bottom,new AfMargin().BOTTOM_CENTER);
        //TOP
        panelTop_Top.add(lblTitle,new AfMargin().CENTER);
        //CENTER
        panelTop_Center.add(lblEnterCoins,new AfMargin().TOP_LEFT);
        panelTop_Center.add(lblEnoughCoins,new AfMargin().TOP_RIGHT);
        lblEnoughCoins.setVisible(false);
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
        panelCenter.add(lblChangeSuccess, new AfMargin(315,380,-1,-1));
        lblChangeSuccess.setVisible(false);
        //BOTTOM
    }

    public void addBottom(){
        //TOP
        panelBottom.add(lblStartDispensing, new AfMargin(0,190,-1,-1));
        lblStartDispensing.setVisible(false);
        panelBottom.add(lblPurchaseSuccess, new AfMargin(0,170,-1,-1));
        lblPurchaseSuccess.setVisible(false);
        panelBottom.add(btnTerminate, new AfMargin(65,190,-1,-1));
        //CENTER
        panelBottom.add(lblCollectCoins, new AfMargin(110,150,-1,-1));
        panelBottom.add(lblCollectCoinsNum, new AfMargin(115,330,-1,-1));
        //BOTTOM
        panelBottom.add(lblCollectCan, new AfMargin(150,150,-1,-1));
        panelBottom.add(lblCollectCanInfo, new AfMargin(155,330,-1,-1));
    }

    public void addCoin(int newCoin) throws InterruptedException {
        lblWarningInvalid.setForeground(Color.GRAY);
        totalCoins += newCoin;
        justCoin.add(newCoin);
        lblTotalMoneyNum.setText(String.valueOf(totalCoins)+" c");
        btnInvalid.setEnabled(true);
        // Time for the simulation machine to judge the validity of the coin
        // after the last coin is put in, give the simulator time to click the invalid button
        sleep(1000);
        checkStopEnter();
        if(isEnough) {
            startChange();
            startDispense();
            purchaseSuccess();
        }
    }

    public void addCheckInvalid(){
        lblWarningInvalid.setForeground(Color.WHITE);
        totalCoins -= justCoin.get(justCoin.size()-1);
        System.out.println(justCoin);
        justCoin.remove(justCoin.size()-1);
        System.out.println(justCoin);
        lblTotalMoneyNum.setText(String.valueOf(totalCoins)+" c");
        btnInvalid.setEnabled(false);
        if(isEnough){
            isEnough = false;
            lblEnoughCoins.setVisible(false);
            for (int i=0; i<btnCoins.size(); i++){
                btnCoins.get(i).setEnabled(true);
            }
        }
    }

    public void addChooseDrink(int pay){
        setShouldPay(pay);
        for (int i=0; i<btnCoins.size(); i++){
            btnCoins.get(i).setEnabled(true);
        }
        lblStartDispensing.setVisible(false);
        lblPurchaseSuccess.setVisible(false);
        btnTerminate.setEnabled(true);
    }

    public void setShouldPay(int pay){
        shouldPay = pay;
    }

    public void checkStopEnter(){
        checkCoinsEnough();
        if(isEnough) {
            lblEnoughCoins.setVisible(true);
            for (int i=0; i<btnCoins.size(); i++){
                btnCoins.get(i).setEnabled(false);
            }
        }
        else {
            lblEnoughCoins.setVisible(false);
            for (int i=0; i<btnCoins.size(); i++){
                btnCoins.get(i).setEnabled(true);
            }
        }
    }

    public void checkCoinsEnough(){
        if(totalCoins<shouldPay)
            isEnough = false;
        else
            isEnough = true;
    }

    public void startChange(){//未完成
        if(totalCoins != shouldPay){
            int change = totalCoins - shouldPay;

        }
        lblChangeSuccess.setVisible(true);
    }

    public void startDispense() throws InterruptedException {
        btnTerminate.setEnabled(false);
        lblStartDispensing.setVisible(true);
        sleep(1000);
        lblChangeSuccess.setVisible(false);
    }

    public void purchaseSuccess(){
        lblPurchaseSuccess.setVisible(true);
//        sleep(1000);
    }
}
