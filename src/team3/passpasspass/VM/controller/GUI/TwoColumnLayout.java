package team3.passpasspass.VM.controller.GUI;

import java.awt.*;

public class TwoColumnLayout implements LayoutStrategy{
    @Override
    public void layoutContainer(Container parent) {
        int width = parent.getWidth();
        System.out.println(width);
        System.out.println("xxx");
        Component[] children = parent.getComponents();
        int x = 0;
        int y = 0;
        for (int i = 0; i<children.length; i++) {
            Component c = children[i];
            c.setBounds(x,y,width,50);
            x +=50;
            y += 50;
        }
    }


    @Override
    public void addLayoutComponent(Component comp, Object constraints) {

    }

    @Override
    public Dimension maximumLayoutSize(Container target) {
        return null;
    }

    @Override
    public float getLayoutAlignmentX(Container target) {
        return 0;
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
        return 0;
    }

    @Override
    public void invalidateLayout(Container target) {

    }

    @Override
    public void addLayoutComponent(String name, Component comp) {

    }

    @Override
    public void removeLayoutComponent(Component comp) {

    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return null;
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return null;
    }


}
