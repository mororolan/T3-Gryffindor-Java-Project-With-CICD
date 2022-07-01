package team3.Gryffindor.VM.panel;
import team3.Gryffindor.VM.GUI.ButtonFactory;
import team3.Gryffindor.VM.GUI.T3Frame;
import team3.Gryffindor.VM.GUI.TextFactory;
import team3.Gryffindor.VM.GUI.VerticalLayout;

import javax.swing.*;

public class  SimulatorControlPanel extends T3Frame {
    // set Buttons
    public JButton bBeginSimulation;
    public JButton bEndSimulation;
    public JButton bCustomerPanel;
    public JButton bMaintainerPanel;
    public JButton bMachineryPanel;

    public SimulatorControlPanel(String title, boolean loginStatus) {
        super(title);
        this.setLayout(new VerticalLayout());
        // set text
        this.add(new TextFactory("Simulator Control Panel",12));

        bBeginSimulation = ButtonFactory.addButton("Begin Simulation","mainPanel");
        bEndSimulation = ButtonFactory.addButton("End Simulation","mainPanel");
        bCustomerPanel = ButtonFactory.addButton("Activate Customer Panel","mainPanel");
        bMaintainerPanel = ButtonFactory.addButton("Activate Maintainer Panel","mainPanel");
        bMachineryPanel = ButtonFactory.addButton("Activate Machinery Panel","mainPanel");

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
            beginSimulation();
        });

        bEndSimulation.addActionListener(e -> {
            endSimulation();
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

    public void beginSimulation(){
        bBeginSimulation.setEnabled(false);
        bEndSimulation.setEnabled(true);
        bCustomerPanel.setEnabled(true);
        bMaintainerPanel.setEnabled(true);
        bMachineryPanel.setEnabled(true);
    }

    public void endSimulation(){
        bEndSimulation.setEnabled(false);
        bBeginSimulation.setEnabled(true);
        bCustomerPanel.setEnabled(false);
        bMaintainerPanel.setEnabled(false);
        bMachineryPanel.setEnabled(false);
    }

    public String openCustomerPanel(boolean loginStatus){
        this.dispose();
        return new CustomerPanel("VMCS - Customer Panel",loginStatus).getTitle();
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
