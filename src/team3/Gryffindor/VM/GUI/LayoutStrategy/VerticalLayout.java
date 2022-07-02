package team3.Gryffindor.VM.GUI.LayoutStrategy;

import team3.Gryffindor.VM.GUI.LayoutStrategy.LayoutStrategy;

import java.awt.*;

public class VerticalLayout implements LayoutStrategy {
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

    @Override
    public void layoutContainer(Container parent) {
        int width = parent.getWidth();
        int height = parent.getHeight();
        Component[] children = parent.getComponents();
        int x = 0;
        int y = 0;
        for (int i = 0; i<children.length; i++) {
            Component c = children[i];
            c.setBounds(x,y,width,height/children.length);
            y += height/children.length;
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
}
