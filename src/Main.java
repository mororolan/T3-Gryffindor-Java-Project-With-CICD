import team3.passpasspass.VM.controller.MachinerySimulatorPanel;
import team3.passpasspass.VM.controller.SimulatorControlPanel;
import team3.passpasspass.VM.controller.model.ReadCSV;


import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        boolean loginStatus = true;
//        new SimulatorControlPanel("VMCS - Simulator Control Panel",loginStatus);
        new MachinerySimulatorPanel("VMCS - Machinery Panel",loginStatus);
    }
}