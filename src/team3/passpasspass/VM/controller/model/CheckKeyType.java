package team3.passpasspass.VM.controller.model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CheckKeyType extends KeyAdapter {
    @Override
    public void keyTyped(KeyEvent e) {
        super.keyTyped(e);
        char ch=e.getKeyChar();
        if(ch<'0'||ch>'9') e.consume();
    }
}
