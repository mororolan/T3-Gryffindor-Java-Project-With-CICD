package team3.passpasspass.VM.controller.customer;

import javax.swing.*;
import java.awt.*;

public class customerPanel {
    public customerPanel() {
        JFrame frame = new JFrame("VMCS-Customer Panel");
        frame.setSize(400,700);
        frame.setLayout(null);

//        frame.add(container1);
//        title.setBounds(100,0,300,50);
        frame.add(panel1());
//        panel_title.setLayout(null);
//        panel_title.setBounds(0,0,400,50);
////        panel_title.setBackground(Color.blue);
////        container.setLayout(null);
//        JLabel title = new JLabel("Soft Drink Dispenser");
//        title.setFont(new Font("Times New Roman",1,22));
////        title.setBounds(100,0,300,50);
//        panel_title.add(title);
//        frame.add(panel_title);



//        frame.setLayout(null);

//        JPanel panel_enter = new JPanel();
//        panel_enter.setLayout(null);
//        panel_enter.setBounds(0,0,400,150);
////        panel_enter.setBackground(Color.red);
//        JLabel enterCoins = new JLabel("Enter Coins Here");
////        enterCoins.setBackground(Color.red);
//        enterCoins.setFont(new Font("Times New Roman",1,20));
////        enterCoins.setBounds(0,0,100,20);
////        frame.add(enterCoins);
//        panel_enter.add(enterCoins);

        frame.add(panel2());

//        enterCoins.setBounds(0,0,);
//        container.add(title);

//        container.setBounds(100,100,400,50);
        frame.setVisible(true);

    }
    public JPanel panel1() {
        JPanel panel_title = new JPanel();
        panel_title.setBounds(0,0,400,50);
//        panel_title.setBackground(Color.blue);
//        container.setLayout(null);
        JLabel title = new JLabel("Soft Drink Dispenser");
        title.setFont(new Font("Times New Roman",1,22));
        JLabel enterCoins = new JLabel("Enter Coins Here");
        enterCoins.setFont(new Font("Times New Roman",1,22));
        enterCoins.setBounds(0,100,400,70);
//        title.setBounds(100,0,300,50);
        panel_title.add(title);
        panel_title.add(enterCoins);
        return panel_title;
    }

    public JPanel panel2() {
        JPanel panel_enter = new JPanel();
//        panel_enter.setLayout(null);
        panel_enter.setBounds(0,0,400,150);
//        panel_enter.setBackground(Color.red);
        JLabel enterCoins = new JLabel("Enter Coins Here");
        enterCoins.setLayout(null);
//        enterCoins.setBackground(Color.red);
        enterCoins.setFont(new Font("Times New Roman",1,20));
//        enterCoins.setBounds(0,0,100,20);
//        frame.add(enterCoins);
        panel_enter.add(enterCoins);
//        panel_enter.setBackground(Color.blue);
        return  panel_enter;
    }
}
