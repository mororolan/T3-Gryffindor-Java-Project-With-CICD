package team3.Gryffindor.VM.panel;

import team3.Gryffindor.VM.GUI.*;
import team3.Gryffindor.VM.model.*;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class CustomerPanel extends T3Frame {

    JPanel contentPane;
    ArrayList<String[]> coins;
    ArrayList<String[]> cans;
    public ArrayList<JButton> btnCoins;
    public ArrayList<JButton> btnCans;
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
    public JLabel lblChangeSuccess;
    //Bottom_Top
    public JLabel lblStartDispensing;
    public JLabel lblPurchaseSuccess;
    public JButton btnTerminate;
    //Bottom_Center
    JLabel lblCollectCoins;
    JLabel lblCollectCoinsNum;
    //Bottom_Bottom
    JLabel lblCollectCan;
    public JLabel lblCollectCanInfo;
    JButton btnBackController;
    public JButton btnContinueToBuy;

    boolean haveDispensed;
    boolean isEnough;
    boolean loginStatus;

    public int totalCoins;//The sum of the value of all coins the customer has put in
    int shouldPay;//The price of the drink selected by the customer
    int chosenId;
    int totalCoinsEntered;
    public DrinkList drinkList;
    CoinList coinList;
    boolean successBuy = false;
    public TerminateMemento terminateMemento;

    /**
     * Create the frame.
     */
    public CustomerPanel(String title,Boolean loginStatus) {
        super(title);
        this.setSize(600,700);
        this.loginStatus = loginStatus;
        customerUI();
    }

    public void customerUI(){
        haveDispensed = false;
        isEnough = false;
        totalCoins = 0;
        shouldPay = 200;
        chosenId = 0;
        drinkList = new DrinkList(this);
        coinList = new CoinList(this);
        terminateMemento = new TerminateMemento();
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
            btnCoins.add(ButtonFactory.addButton(coins.get(i)[0]+"c","jbEnterCoins"));
        }
        btnInvalid = ButtonFactory.addButton("invalid","jbEnterCoins");
        btnInvalid.setEnabled(true);
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
            btnCans.add(ButtonFactory.addButton(cans.get(i)[1],"jbChooseDrink"));
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
        checkNullStock();
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
        lblPurchaseSuccess.setPreferredSize(new Dimension(300,30));
        btnContinueToBuy = ButtonFactory.addButton("Continue to Buy","jbContinueToBuy");
        btnTerminate = ButtonFactory.addButton("Terminate and Return Cash","jbTerminate");
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
        btnBackController = ButtonFactory.addButton("Back to the Main Panel","jbCustomerBack");
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
            btnCoins.get(i).setEnabled(false);
        }
        panelTop_Center.add(btnInvalid,new AfMargin(35,490,-1,-1));
        btnInvalid.setEnabled(false);
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
        panelBottom.add(btnContinueToBuy, new AfMargin(35,240,-1,-1));
        btnContinueToBuy.setVisible(false);
        panelBottom.add(btnTerminate, new AfMargin(65,190,-1,-1));
        //CENTER
        panelBottom.add(lblCollectCoins, new AfMargin(90,150,-1,-1));
        panelBottom.add(lblCollectCoinsNum, new AfMargin(95,330,-1,-1));
        //BOTTOM
        panelBottom.add(lblCollectCan, new AfMargin(120,150,-1,-1));
        panelBottom.add(lblCollectCanInfo, new AfMargin(125,330,-1,-1));
        panelBottom.add(btnBackController, new AfMargin(150,190,-1,-1));
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
                addChooseDrink(Integer.parseInt(cans.get(finalI)[3]), Integer.parseInt(cans.get(finalI)[0]));
            });
        }

        btnTerminate.addActionListener(e -> {
            addTerminate();
            customerUI();
            lblCollectCoinsNum.setText(String.valueOf(totalCoinsEntered) + "c");
        });

        btnBackController.addActionListener(e -> {
            addBackControllerPanel(loginStatus);
        });

        btnContinueToBuy.addActionListener(e -> {
            customerUI();
        });

    }

    public void addCoin(int newCoin) throws InterruptedException {
        lblWarningInvalid.setForeground(Color.GRAY);
        totalCoins += newCoin;
        terminateMemento.addCoin(newCoin);
        lblTotalMoneyNum.setText(String.valueOf(totalCoins)+" c");
//        btnInvalid.setEnabled(true);
//        // Time for the simulation machine to judge the validity of the coin
//        // after the last coin is put in, give the simulator time to click the invalid button
//        sleep(1000);

        checkStopEnter();
        if(isEnough) {
//            btnInvalid.setEnabled(false);
            startChange();
            startDispense();
            purchaseSuccess();
        }
    }

    public void addCheckInvalid(){
        lblWarningInvalid.setForeground(Color.WHITE);
        lblTotalMoneyNum.setText(String.valueOf(totalCoins)+" c");
//        btnInvalid.setEnabled(false);
        if(isEnough){
            isEnough = false;
            lblEnoughCoins.setVisible(false);
            for (int i=0; i<btnCoins.size(); i++){
                btnCoins.get(i).setEnabled(true);
            }
        }
    }

    public void addChooseDrink(int pay,int id){
        setShouldPay(pay);
        isEnough = false;
        chosenId = id;
        for(int i = 0; i < btnCans.size(); i++){
            if(i != chosenId - 1)
                btnCans.get(i).setEnabled(false);
        }
        for (int i = 0; i < btnCoins.size(); i++){
            btnCoins.get(i).setEnabled(true);
        }
        lblStartDispensing.setVisible(false);
        lblPurchaseSuccess.setVisible(false);
        btnTerminate.setEnabled(true);
        lblChangeSuccess.setVisible(false);
    }

    public void addTerminate(){
        terminateMemento();
    }

    public String addBackControllerPanel(boolean loginStatus){
        this.dispose();
        return new SimulatorControlPanel("VMCS - Simulator Control Panel", loginStatus).getTitle();
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

    public void startChange(){
        ArrayList<String> changeSol = new ArrayList<>();
        ArrayList<Integer> coinsInfo = new ArrayList<>();
        ArrayList<Integer> coinsNum = new ArrayList<>();
        for (int i=coins.size()-1; i>0; i--){
            coinsInfo.add(Integer.parseInt(coins.get(i)[0]));
            coinsNum.add(Integer.parseInt(coins.get(i)[1]));
        }
        if(totalCoins != shouldPay){
            int change = totalCoins - shouldPay;
            changeSol = coinList.returnChange(changeSol,coinsInfo,coinsNum,change);
            lblCollectCoinsNum.setText(String.valueOf(change));
            if(changeSol.get(changeSol.size()-1) =="false")
                lblNoChange.setForeground(Color.WHITE);
        }
        for (int i=1,j=coinsNum.size()-1; j>0; i++,j--){
            coinList.setNumber(i,coinsNum.get(j));
        }
        lblChangeSuccess.setVisible(true);
    }


    public void terminateMemento() {
        ArrayList<Integer> coinsEntered = terminateMemento.getState();
        for(int i = 0; i < coinsEntered.size(); i++) {
            totalCoinsEntered += coinsEntered.get(i);
        }
        terminateMemento.clearcoinsEntered();
    }

    public void updateCoinNum(){// Observer
        for (int i=1; i<coins.size(); i++){
            coins.get(i)[1] = String.valueOf(coinList.getNumber(i));
        }
        WriteCSV.writeCSV(coins,"coin");//Update database
    }

    public void startDispense() throws InterruptedException {
        btnTerminate.setEnabled(false);
        lblStartDispensing.setVisible(true);
        sleep(1000);
        lblStartDispensing.setVisible(false);
        drinkList.setNumber(chosenId,Integer.parseInt(cans.get(chosenId)[2])-1);
    }

    public void updateStock(){
        int newNum = drinkList.getNumber(chosenId);
        if(newNum == 0) {
            lblDrinkCnt.get(chosenId - 1).setForeground(Color.WHITE);//Update panel information
//            btnCans.get(chosenId - 1).setEnabled(false);
        }
        cans.get(chosenId)[2] = String.valueOf(newNum);
        WriteCSV.writeCSV(cans,"drink");//Update database
    }

    public void purchaseSuccess(){
        lblPurchaseSuccess.setVisible(true);
        btnContinueToBuy.setVisible(true);
        for(int i = 0; i < cans.size(); i++){
            if(chosenId == i + 1)
                lblCollectCanInfo.setText(cans.get(chosenId)[1]);
        }
        chosenId = 0;
        successBuy = true;
         for(int i = 0; i < terminateMemento.getState().size(); i++) {
            for (int j = 1; j < coins.size(); j++) {
                if(terminateMemento.getState().get(i) == Integer.parseInt(coins.get(j)[0]))
                    coinList.setNumber(j,Integer.parseInt(coins.get(j)[1]) + 1);
            }
        }
        for(int i = 0; i < coins.size() - 1; i++)
            System.out.println("new cnt: " + coinList.getNumber(i + 1));

    }

    public void checkNullStock() {
        for(int i = 0; i < cans.size() - 1; i++) {
            int id = i+1;
            if(drinkList.getNumber(id) == 0)
                btnCans.get(i).setEnabled(false);
        }
    }

}
