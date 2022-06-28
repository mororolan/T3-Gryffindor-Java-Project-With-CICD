package team3.passpasspass.VM.controller.GUI;

import javax.swing.*;
import java.awt.*;

public class TextFactory extends JLabel {
    public TextFactory(String text) {
        super(text);
        this.setPreferredSize(new Dimension(250, 60));
        this.setHorizontalAlignment(SwingConstants.CENTER);
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
//            this.setBounds(250,0,400,50);
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
        if (type == 7) {
            this.setPreferredSize(new Dimension(200,30));
            this.setFont(new Font("Microsoft Black",1,15));
        }
        if (type == 8) {
            this.setFont(new Font("Microsoft Black",1,20));
        }
        if (type == 9) {//warning
            this.setPreferredSize(new Dimension(150,20));
            this.setOpaque(true);
            this.setFont(new Font("Microsoft Black",1,13));
            this.setBackground(Color.RED);
            this.setForeground(Color.WHITE);
        }
        if (type == 10) {// no warning
            this.setPreferredSize(new Dimension(150,20));
            this.setOpaque(true);
            this.setFont(new Font("Microsoft Black",1,13));
            this.setBackground(Color.RED);
            this.setForeground(Color.GRAY);
        }
        if (type == 11) {// customer black+yellow
            this.setPreferredSize(new Dimension(100,20));
            this.setOpaque(true);
            this.setFont(new Font("Times New Roman",1,15));
            this.setBackground(Color.black);
            this.setForeground(Color.YELLOW);
        }
        if (type == 12) {
            // Title
            this.setFont(new Font("Microsoft Black",1,33));
        }
        if (type == 13) {
            this.setFont(new Font("Microsoft Black",1,15));
            this.setPreferredSize(new Dimension(200,30));
        }
        if (type == 14){
            // warning
            this.setFont(new Font("Microsoft Black",1,15));
        }
    }

}