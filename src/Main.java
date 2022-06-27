import team3.passpasspass.VM.controller.CustomerPanel;
import team3.passpasspass.VM.controller.GUI.TextFactory;
import team3.passpasspass.VM.controller.MachinerySimulatorPanel;
import team3.passpasspass.VM.controller.SimulatorControlPanel;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        boolean loginStatus = false;
//        CustomerPanel customerPanel = new CustomerPanel("VMCS - Customer Panel");
        new SimulatorControlPanel("VMCS - Simulator Control Panel",loginStatus);
//        new NewMaintainerPanel("test",loginStatus);
//        new MachinerySimulatorPanel("VMCS - Machinery Panel",loginStatus);
    }
}

