package team3.Gryffindor.VM;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import team3.Gryffindor.VM.GUI.ButtonFactory;
import team3.Gryffindor.VM.model.ReadCSV;
import team3.Gryffindor.VM.panel.CustomerPanel;
import team3.Gryffindor.VM.panel.SimulatorControlPanel;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CustomerPanelTest {

    ArrayList<String[]> coins = ReadCSV.readCSV("./data/dwd_money_stat.csv");
    ArrayList<String[]> cans = ReadCSV.readCSV("./data/dwd_drink_info.csv");
    private static ArrayList<JButton> btnCoins = new ArrayList<>();
    private static CustomerPanel customerPanel = new CustomerPanel("VMCS - Customer Panel", true);
    private static ArrayList<JButton> btnCans = new ArrayList<>();
    private static int chosenID;



    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addCoin() throws InterruptedException {
        for (int i=1; i<coins.size(); i++){
            btnCoins.add(ButtonFactory.addButton(coins.get(i)[0]+"c","jbEnterCoins"));
        }

        for(int i = 1; i < coins.size(); i++){
            customerPanel.addCoin(Integer.parseInt(coins.get(i)[0]));
        }
        assertEquals(185,customerPanel.totalCoins);
    }


    @Test
    public void addChooseDrink() {
        chosenID = 1;
        customerPanel.addChooseDrink(Integer.parseInt(cans.get(chosenID)[3]), Integer.parseInt(cans.get(chosenID)[0]));
        for(int i = 0; i < btnCans.size(); i++){
            if(i != chosenID - 1)
                assertEquals(false, customerPanel.btnCans.get(i).isEnabled());
        }
        for (int i = 0; i < btnCoins.size(); i++){
            assertEquals(true,customerPanel.btnCoins.get(i).isEnabled());
        }
        assertEquals(false,customerPanel.lblStartDispensing.isVisible());
        assertEquals(false,customerPanel.lblPurchaseSuccess.isVisible());
        assertEquals(true,customerPanel.btnTerminate.isEnabled());
        assertEquals(false,customerPanel.lblChangeSuccess.isVisible());
    }

    @Test
    public void startChange() {
        customerPanel.startChange();
        assertEquals(true,customerPanel.lblChangeSuccess.isVisible());

    }

    @Test
    public void startDispense() {
        CustomerPanel customerPanel1 = new CustomerPanel1("VMCS - Customer Panel", true);
        assertEquals(false,customerPanel.btnTerminate.isEnabled());
        assertEquals(false,customerPanel.lblStartDispensing.isVisible());
    }

    @Test
    public void terminateMemento() {
        customerPanel.terminateMemento();
        ArrayList<Integer> a = new ArrayList<>();
        assertEquals(a,customerPanel.terminateMemento.getState());
    }

    @Test
    public void purchaseSuccess() {
        chosenID = 1;
        customerPanel.purchaseSuccess();
        assertEquals(true,customerPanel.lblPurchaseSuccess.isVisible());
        assertEquals(true,customerPanel.btnContinueToBuy.isVisible());
        assertEquals("Coca-Cola", customerPanel.lblCollectCanInfo.getText());
    }

    @Test
    public void checkNullStock() {
        chosenID = 2;
        if(customerPanel.drinkList.getNumber(chosenID) == 0)
            assertEquals(false,customerPanel.btnCans.get(chosenID).isEnabled());
        else
            assertEquals(true,customerPanel.btnCans.get(chosenID).isEnabled());

    }

    @Test
    public void addBackControllerPanel() {
        assertEquals(new SimulatorControlPanel("VMCS - Simulator Control Panel", true).getTitle(), customerPanel.addBackControllerPanel(true));
    }

}
