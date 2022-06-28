package team3.passpasspass.VM.controller.GUI;

import javax.swing.*;
import java.awt.*;

public class ButtonFactory {
    public ButtonFactory(String valid_password, String jbMaintainerPasswordValid) {
    }

    public static JButton buttonFactory(String buttonTitle, String buttonType) {
        if (buttonType == null) {
            return null;
        }
        JButton button = new JButton(buttonTitle);
        button.setPreferredSize(new Dimension(300, 60));
        switch (buttonType) {
            case "mainPanel":
                button.setFont(new Font("Microsoft Black", Font.BOLD, 22));
                return button;
            case "small":
                button.setFont(new Font("Microsoft Black", Font.PLAIN, 18));
                return button;
            case "machineryPanel":
                button.setFont(new Font("Arial", Font.PLAIN, 15));
                button.setPreferredSize(new Dimension(300, 50));
                button.setForeground(Color.white);
                button.setBackground(Color.gray);
                return button;
            case "jbMaintainerPasswordValid":
                button.setBounds(100,150,100,30);
                button.setFont(new Font("",1,15));
                button.setForeground(Color.white);
                button.setBackground(Color.gray);
                return button;
            case "jbMaintainerPasswordInvalid":
//                button.setBounds(360,150,280,50);
                button.setFont(new Font("",1,15));
                button.setForeground(Color.black);
                button.setBackground(Color.gray);
                return button;
            case "jbCoin":
                button.setBackground(Color.white);
                return button;
            case "jbShowTotal":
                button.setBounds(200,700,300,30);
                button.setFont(new Font("",1,15));
                button.setBackground(Color.white);
                return button;
            case "jbCollectCash":
                button.setBounds(250,750,300,30);
                button.setFont(new Font("",1,15));
                button.setBackground(Color.white);
                return button;
            case "jbFinished":
                button.setBounds(240,880,300,30);
                button.setFont(new Font("",1,15));
                button.setBackground(Color.white);
                return button;
            case "jbEnterCoins":
                button.setPreferredSize(new Dimension(90, 30));
                button.setBackground(Color.white);
                return button;
            case "jbChooseDrink":
                button.setPreferredSize(new Dimension(200, 40));
                button.setBackground(Color.white);
                return button;
            case "jbTerminate":
                button.setPreferredSize(new Dimension(250, 25));
                button.setBackground(Color.white);
                return button;
        }
        return button;
    }

}
