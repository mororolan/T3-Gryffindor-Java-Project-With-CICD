package team3.passpasspass.VM.controller;
import team3.passpasspass.VM.controller.GUI.*;
import javax.swing.*;
import java.awt.*;

public class  SimulatorControlPanel extends T3Frame {
    // set Buttons
    JButton bBeginSimulation;
    JButton bEndSimulation;
    JButton bCustomerPanel;
    JButton bMaintainerPanel;
    JButton bMachineryPanel;

    public SimulatorControlPanel(String title, boolean loginStatus) {
        super(title);
        this.setLayout(new VerticalLayout());
        // set text
        this.add(new TextFactory("Simulator Control Panel",12));

        bBeginSimulation = ButtonFactory.buttonFactory("Begin Simulation","mainPanel");
        bEndSimulation = ButtonFactory.buttonFactory("End Simulation","mainPanel");
        bCustomerPanel = ButtonFactory.buttonFactory("Activate Customer Panel","mainPanel");
        bMaintainerPanel = ButtonFactory.buttonFactory("Activate Maintainer Panel","mainPanel");
        bMachineryPanel = ButtonFactory.buttonFactory("Activate Machinery Panel","mainPanel");

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
            beginSimulation(bBeginSimulation,bEndSimulation,bCustomerPanel,bMaintainerPanel,bMachineryPanel);
        });

        bEndSimulation.addActionListener(e -> {
            endSimulation(bBeginSimulation,bEndSimulation,bCustomerPanel,bMaintainerPanel,bMachineryPanel);
        });

        bCustomerPanel.addActionListener(e -> {
            openCustomerPanel(loginStatus);
        });

        bMachineryPanel.addActionListener(e -> {
            openMachineryPanel(loginStatus);
        });

        bMaintainerPanel.addActionListener(e -> {
            openMaintainerPanel(loginStatus);
        });

        setVisible(true);
    }

    public void beginSimulation(JButton bBeginSimulation, JButton bEndSimulation, JButton bCustomerPanel, JButton bMaintainerPanel, JButton bMachineryPanel){
        bBeginSimulation.setEnabled(false);
        bEndSimulation.setEnabled(true);
        bCustomerPanel.setEnabled(true);
        bMaintainerPanel.setEnabled(true);
        bMachineryPanel.setEnabled(true);
    }

    public void endSimulation(JButton bBeginSimulation, JButton bEndSimulation, JButton bCustomerPanel, JButton bMaintainerPanel, JButton bMachineryPanel){
        bEndSimulation.setEnabled(false);
        bBeginSimulation.setEnabled(true);
        bCustomerPanel.setEnabled(false);
        bMaintainerPanel.setEnabled(false);
        bMachineryPanel.setEnabled(false);
    }

    public String openCustomerPanel(boolean loginStatus){
        this.dispose();
        return new CustomerPanel("VMCS - Customer Panel").getTitle();
    }
    public String openMachineryPanel(boolean loginStatus){
        this.dispose();
        return new MachinerySimulatorPanel("VMCS - Machinery Panel", loginStatus).getTitle();
    }

    public String openMaintainerPanel(boolean loginStatus){
        this.dispose();
        return new MaintainerPanel("VMCS - Maintainer Panel",loginStatus).getTitle();
    }
}