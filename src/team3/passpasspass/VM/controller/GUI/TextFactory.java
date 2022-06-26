package team3.passpasspass.VM.controller.GUI;

import javax.swing.*;
import java.awt.*;

public class TextFactory extends JLabel {
    public TextFactory(String text) {
        super(text);
        this.setPreferredSize(new Dimension(250, 60));
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);
        this.setFont(new Font(Font.SERIF, Font.PLAIN, 24));
    }

    public TextFactory(String text, int type) {
        this(text);
        if (type == 0) {
            this.setFont(new Font("Times New Romans", Font.BOLD, 16));
        }
        if (type == 1) {
            this.setFont(new Font("Times New Romans", Font.BOLD, 15));
        }
    }


    }
