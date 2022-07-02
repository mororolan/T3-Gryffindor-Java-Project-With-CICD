package team3.Gryffindor.VM.GUI.FactoryPattern;

import javax.swing.*;
import java.awt.*;

class mainPanelButton extends JButton {
    public JButton setMainPanelButton(String buttonTitle){
        JButton button = new JButton(buttonTitle);
        button.setPreferredSize(new Dimension(300,60));
        button.setFont(new Font("Microsoft Black",Font.BOLD, 22));
        return button;
    }
}

class smallButton extends JButton {
    public JButton setSmallButton(String buttonTitle){
        JButton button = new JButton(buttonTitle);
        button.setPreferredSize(new Dimension(300,60));
        button.setFont(new Font("Microsoft Black", Font.PLAIN, 18));
        return button;
    }
}

class machineryPanelButton extends JButton {
    public JButton setMachineryPanelButton(String buttonTitle){
        JButton button = new JButton(buttonTitle);
        button.setFont(new Font("Arial", Font.PLAIN, 15));
        button.setPreferredSize(new Dimension(300, 50));
        button.setForeground(Color.white);
        button.setBackground(Color.gray);
        return button;
    }
}

class jbMaintainerPasswordValidButton extends JButton {
    public JButton setMaintainerPasswordValidButton(String buttonTitle){
        JButton button = new JButton(buttonTitle);
        button.setPreferredSize(new Dimension(300, 60));
        button.setBounds(100,150,100,30);
        button.setFont(new Font("",1,15));
        button.setForeground(Color.white);
        button.setBackground(Color.gray);
        return button;
    }
}

class jbMaintainerPasswordInvalidButton extends JButton {
    public JButton setMaintainerPasswordInvalidButton(String buttonTitle){
        JButton button = new JButton(buttonTitle);
        button.setPreferredSize(new Dimension(300, 60));
        button.setFont(new Font("",1,15));
        button.setForeground(Color.black);
        button.setBackground(Color.gray);
        return button;
    }
}

class jbCoinButton extends JButton {
    public JButton setCoinButton(String buttonTitle){
        JButton button = new JButton(buttonTitle);
        button.setPreferredSize(new Dimension(300, 60));
        button.setBackground(Color.white);
        return button;
    }
}

class showTotalButton extends JButton {
    public JButton setShowTotalButton(String buttonTitle){
        JButton button = new JButton(buttonTitle);
        button.setPreferredSize(new Dimension(300, 60));
        button.setBounds(200,700,300,30);
        button.setFont(new Font("",1,15));
        button.setBackground(Color.white);
        return button;
    }
}

class collectCashButton extends JButton {
    public JButton setCollectCashButton(String buttonTitle){
        JButton button = new JButton(buttonTitle);
        button.setPreferredSize(new Dimension(300, 60));
        button.setBounds(250,750,300,30);
        button.setFont(new Font("",1,15));
        button.setBackground(Color.white);
        return button;
    }
}

class finishedButton extends JButton {
    public JButton setFinishedButton(String buttonTitle){
        JButton button = new JButton(buttonTitle);
        button.setPreferredSize(new Dimension(300, 60));
        button.setBounds(240,880,300,30);
        button.setFont(new Font("",1,15));
        button.setBackground(Color.white);
        return button;
    }
}

class enterCoinsButton extends JButton {
    public JButton setEnterCoinsButton(String buttonTitle){
        JButton button = new JButton(buttonTitle);
        button.setPreferredSize(new Dimension(90, 30));
        button.setBackground(Color.white);
        return button;
    }
}

class chooseDrinkButton extends JButton {
    public JButton setChooseDrinkButton(String buttonTitle){
        JButton button = new JButton(buttonTitle);
        button.setPreferredSize(new Dimension(200, 40));
        button.setBackground(Color.white);
        return button;
    }
}

class continueToBuyButton extends JButton {
    public JButton setContinueToBuyButton(String buttonTitle){
        JButton button = new JButton(buttonTitle);
        button.setPreferredSize(new Dimension(150, 25));
        button.setBackground(Color.red);
        return button;
    }
}

class customerBackButton extends JButton {
    public JButton setCustomerBackButton(String buttonTitle){
        JButton button = new JButton(buttonTitle);
        button.setPreferredSize(new Dimension(250, 25));
        button.setBackground(Color.white);
        return button;
    }
}

public class ButtonFactory {

    public static JButton addButton(String buttonTitle, String buttonType) {
        if (buttonType == null) {
            return null;
        }
        JButton button = new JButton(buttonTitle);
        button.setPreferredSize(new Dimension(300, 60));
        switch (buttonType) {
            case "mainPanel":
                return new mainPanelButton().setMainPanelButton(buttonTitle);
            case "small":
                return new smallButton().setSmallButton(buttonTitle);
            case "machineryPanel":
                return new machineryPanelButton().setMachineryPanelButton(buttonTitle);
            case "jbMaintainerPasswordValid":
                return new jbMaintainerPasswordValidButton().setMaintainerPasswordValidButton(buttonTitle);
            case "jbMaintainerPasswordInvalid":
                return new jbMaintainerPasswordInvalidButton().setMaintainerPasswordInvalidButton(buttonTitle);
            case "jbCoin":
                return new jbCoinButton().setCoinButton(buttonTitle);
            case "jbShowTotal":
                return new showTotalButton().setShowTotalButton(buttonTitle);
            case "jbCollectCash":
                return new collectCashButton().setCollectCashButton(buttonTitle);
            case "jbFinished":
                return new finishedButton().setFinishedButton(buttonTitle);
            case "jbEnterCoins":
                return new enterCoinsButton().setEnterCoinsButton(buttonTitle);
            case "jbChooseDrink":
                return new chooseDrinkButton().setChooseDrinkButton(buttonTitle);
            case "jbContinueToBuy":
                return new continueToBuyButton().setContinueToBuyButton(buttonTitle);
            case "jbTerminate":
            case "jbCustomerBack":
                return new customerBackButton().setCustomerBackButton(buttonTitle);
        }
        return button;
    }

}
