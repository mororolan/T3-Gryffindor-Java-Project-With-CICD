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
        if (type == 2) {
            this.setBounds(250,0,400,50);
            this.setFont(new Font("Microsoft Black",1,25));
        }
        if (type == 3) {
            // jlCoins
            this.setBounds(5,250,300,20);
            this.setFont(new Font("Microsoft Black",1,15));
        }
        if(type == 4) {
            //jlDrinksAvailable
            this.setBounds(5,450,300,20);
            this.setFont(new Font("",1,15));
        }
        if (type == 5) {
            this.setBounds(300,620,100,20);
            this.setFont(new Font("",1,15));
        }
        if (type == 6) {
            // jlCollectCash.setBounds(320,800,100,20);
            this.setBounds(320,800,100,20);
            this.setFont(new Font("",1,15));
        }
    }


    }
