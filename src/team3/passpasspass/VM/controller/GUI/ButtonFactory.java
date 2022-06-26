package team3.passpasspass.VM.controller.GUI;

import javax.swing.*;
import java.awt.*;

public class ButtonFactory {
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
        }
        return button;
    }

}
