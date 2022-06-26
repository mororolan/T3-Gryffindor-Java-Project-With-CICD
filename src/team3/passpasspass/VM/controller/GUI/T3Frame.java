package team3.passpasspass.VM.controller.GUI;

import javax.swing.*;
import java.awt.*;

public class T3Frame extends JFrame {

    public T3Frame(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 800);
        this.setVisible(true);
        this.setResizable(false);
        // set location
        this.setLocation(500, 200);
        // set Container
        Container mainContainer = this.getContentPane();
//        this.setContentPane(new T3Panel());
        // set Layout
        this.setLayout(new VerticalLayout());
        // add ourTeamMark
        JLabel T3Mark = new TextFactory("@Copyright(c) Team #3 Work",0);
        this.add(T3Mark);
        T3Mark.setBounds(100,600,200,100);

    }
}
