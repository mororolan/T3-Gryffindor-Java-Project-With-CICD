package team3.Gryffindor.VM;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import team3.Gryffindor.VM.panel.SimulatorControlPanel;

import static org.junit.Assert.*;

public class SimulatorControlPanelTest {
    private static SimulatorControlPanel SCP = new SimulatorControlPanel("VMCS - Machinery Panel",false);
    private boolean loginStatus = true;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void beginSimulation() {
        SCP.beginSimulation(SCP.bBeginSimulation, SCP.bEndSimulation, SCP.bCustomerPanel, SCP.bMaintainerPanel, SCP.bMachineryPanel);
        assertEquals(false,SCP.bBeginSimulation.isEnabled());
        assertEquals(true,SCP.bEndSimulation.isEnabled());
        assertEquals(true,SCP.bCustomerPanel.isEnabled());
        assertEquals(true,SCP.bMaintainerPanel.isEnabled());
        assertEquals(true,SCP.bMachineryPanel.isEnabled());
    }

    @Test
    public void endSimulation() {
        SCP.endSimulation(SCP.bBeginSimulation, SCP.bEndSimulation, SCP.bCustomerPanel, SCP.bMaintainerPanel, SCP.bMachineryPanel);
        assertEquals(true,SCP.bBeginSimulation.isEnabled());
        assertEquals(false,SCP.bEndSimulation.isEnabled());
        assertEquals(false,SCP.bCustomerPanel.isEnabled());
        assertEquals(false,SCP.bMaintainerPanel.isEnabled());
        assertEquals(false,SCP.bMachineryPanel.isEnabled());
    }

//    @Test
//    public void openCustomerPanel() {
//        String actual = SCP.openCustomerPanel(loginStatus);
//        assertEquals("VMCS - Customer Panel",actual);
//    }

    @Test
    public void openMachineryPanel() {
        String actual = SCP.openMachineryPanel(loginStatus);
        assertEquals("VMCS - Machinery Panel",actual);
    }

    @Test
    public void openMaintainerPanel() {
        String actual = SCP.openMaintainerPanel(loginStatus);
        assertEquals("VMCS - Maintainer Panel",actual);
    }
}