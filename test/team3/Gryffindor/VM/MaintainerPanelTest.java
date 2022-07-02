package team3.Gryffindor.VM;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import team3.Gryffindor.VM.model.ReadCSV;
import team3.Gryffindor.VM.panel.MaintainerPanel;

import javax.swing.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.*;

public class MaintainerPanelTest {
    int coinsActualTotal = 0;
    boolean logStatus = false;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testOpenMaintainerFrame_WithoutLogin() {
        String maintainerFrameTitle = "Test Maintainer Panel (Not Login)";
        JFrame maintainerFrame = new MaintainerPanel(maintainerFrameTitle,true);
        assertTrue(maintainerFrame.isVisible());
    }

    @Test
    public void testOpenMaintainerFrame_WithLogin() {
        String maintainerFrameTitle = "Test Maintainer Panel (Login)";
        JFrame maintainerFrame = new MaintainerPanel(maintainerFrameTitle,true);
        assertTrue(maintainerFrame.isVisible());
    }

    @Test
    public void successHideMaintainerFunction_WithoutLogin() {
        String maintainerFrameTitle = "Test Maintainer Panel (WithoutLogin)";
        MaintainerPanel maintainerFrame = new MaintainerPanel(maintainerFrameTitle,false);
        assertTrue(maintainerFrame.panelTop.isVisible());
        assertFalse(maintainerFrame.panelCenter.isVisible());
        assertFalse(maintainerFrame.panelBottom.isVisible());
    }

    @Test
    public void successShowMaintainerFunction_Login() {
        String maintainerFrameTitle = "Test Maintainer Panel (Login)";
        MaintainerPanel maintainerFrame = new MaintainerPanel(maintainerFrameTitle,true);
        assertTrue(maintainerFrame.panelTop.isVisible());
        assertTrue(maintainerFrame.panelCenter.isVisible());
        assertTrue(maintainerFrame.panelBottom.isVisible());
    }

    @Test
    public void correctShowCoinsList() {
        String maintainerFrameTitle = "Test Show Coins (Login)";
        MaintainerPanel maintainerFrame = new MaintainerPanel(maintainerFrameTitle,true);
        assertEquals((maintainerFrame.coins.get().size()-1),5);
        AtomicReference<ArrayList<String[]>> coinsTest = new AtomicReference<>(ReadCSV.readCSV("./data/dwd_money_stat.csv"));
        String actualCoinNameTest = coinsTest.get().get(5)[0];
        String actualCoinNumberTest = coinsTest.get().get(5)[1];
        assertEquals(maintainerFrame.coins.get().get(5)[0],actualCoinNameTest);
        assertEquals(maintainerFrame.coins.get().get(5)[1],actualCoinNumberTest);
    }

    @Test
    public void correctShowDrinkList() {
        String maintainerFrameTitle = "Test Show Drinks (Login)";
        MaintainerPanel maintainerFrame = new MaintainerPanel(maintainerFrameTitle,true);
        AtomicReference<ArrayList<String[]>> drinksTest = new AtomicReference<>(ReadCSV.readCSV("./data/dwd_drink_info.csv"));
        int actualDrinksLinesTest = drinksTest.get().size() - 1;
        String actualDrinkNameTest = drinksTest.get().get(4)[1];
        String actualDrinkNumberTest = drinksTest.get().get(5)[2];
        assertEquals((maintainerFrame.cans.get().size()-1),actualDrinksLinesTest);
        assertEquals(maintainerFrame.cans.get().get(4)[1],actualDrinkNameTest);
        assertEquals(maintainerFrame.cans.get().get(5)[2],actualDrinkNumberTest);
    }

    @Test
    public void testGetTotalCash() {
        String maintainerFrameTitle = "Test Show Total Coins (Login)";
        MaintainerPanel maintainerFrame = new MaintainerPanel(maintainerFrameTitle,true);
        for (int i = 1; i < maintainerFrame.coins.get().size(); i++) {
            maintainerFrame.coinsTotal[0] += Integer.parseInt(maintainerFrame.coins.get().get(i)[0]) * Integer.parseInt(maintainerFrame.coins.get().get(i)[1]);
        }
        AtomicReference<ArrayList<String[]>> coinsTest = new AtomicReference<>(ReadCSV.readCSV("./data/dwd_money_stat.csv"));
        coinsActualTotal = 0;
        for (int i = 1; i < coinsTest.get().size(); i++) {
            coinsActualTotal += Integer.parseInt(coinsTest.get().get(i)[0]) * Integer.parseInt(coinsTest.get().get(i)[1]);
        }
        assertEquals(maintainerFrame.coinsTotal[0],coinsActualTotal);
    }

    @Test
    public void testLogout() {
        String maintainerFrameTitle = "Test Maintainer Panel (Logout)";
        MaintainerPanel maintainerFrame = new MaintainerPanel(maintainerFrameTitle,false);
        logStatus = false;
        assertTrue(maintainerFrame.panelTop.isVisible());
        assertFalse(maintainerFrame.panelCenter.isVisible());
        assertFalse(maintainerFrame.panelBottom.isVisible());
    }
}