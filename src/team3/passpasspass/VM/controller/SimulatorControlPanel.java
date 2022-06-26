package team3.passpasspass.VM.controller;
import team3.passpasspass.VM.controller.GUI.*;
import javax.swing.*;
import java.awt.*;

public class  SimulatorControlPanel extends T3Frame {

    public SimulatorControlPanel(String title, boolean loginStatus) {
        super(title);
        this.setLayout(new FlowLayout());
        // set text
        this.add(new TextFactory("Simulator Control Panel"));

        // set Buttons
        JButton bBeginSimulation = ButtonFactory.buttonFactory("Begin Simulation","mainPanel");
        JButton bEndSimulation = ButtonFactory.buttonFactory("End Simulation","mainPanel");
        JButton bCustomerPanel = ButtonFactory.buttonFactory("Activate Customer Panel","mainPanel");
        JButton bMaintainerPanel = ButtonFactory.buttonFactory("Activate Maintainer Panel","mainPanel");
        JButton bMachineryPanel = ButtonFactory.buttonFactory("Activate Machinery Panel","mainPanel");

        bEndSimulation.setEnabled(false);
        bCustomerPanel.setEnabled(false);
        bMaintainerPanel.setEnabled(false);
        bMachineryPanel.setEnabled(false);

        this.add(bBeginSimulation);
        this.add(bEndSimulation);
        this.add(bCustomerPanel);
        this.add(bMaintainerPanel);
        this.add(bMachineryPanel);

        bBeginSimulation.addActionListener(e -> {
            bBeginSimulation.setEnabled(false);
            bEndSimulation.setEnabled(true);
            bCustomerPanel.setEnabled(true);
            bMaintainerPanel.setEnabled(true);
            bMachineryPanel.setEnabled(true);
        });

        bEndSimulation.addActionListener(e -> {
            bEndSimulation.setEnabled(false);
            bBeginSimulation.setEnabled(true);
            bCustomerPanel.setEnabled(false);
            bMaintainerPanel.setEnabled(false);
            bMachineryPanel.setEnabled(false);
        });

        bMachineryPanel.addActionListener(e -> {
            this.dispose();
            new MachinerySimulatorPanel("VMCS - Machinery Panel", loginStatus);
        });

        bMaintainerPanel.addActionListener(e -> {
            this.dispose();
//            new MaintainerPanel().createMaintainerJFrame("VMCS - Maintainer Panel");
            new MaintainerPanel("VMCS - Maintainer Panel",loginStatus);
        });

    }
}
