package team3.Gryffindor.VM.GUI;

import javax.swing.*;
import java.awt.*;

public class T3Frame extends JFrame {

    public T3Frame(String title) throws HeadlessException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 800);
        this.setVisible(true);

        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        JLabel T3Mark = new TextFactory("@Copyright(c) Team #3: Gryffindor", 0);
        this.add(T3Mark);

        setIconImage(Toolkit.getDefaultToolkit().getImage("./res/icon.gif"));

    }
}

