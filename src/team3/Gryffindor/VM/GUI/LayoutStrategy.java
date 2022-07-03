package team3.Gryffindor.VM.GUI;

import java.awt.*;

public interface LayoutStrategy extends LayoutManager2{
    void addLayoutComponent(Component comp, Object constraints);

    public Dimension maximumLayoutSize(Container target);

    public float getLayoutAlignmentX(Container target);

    public float getLayoutAlignmentY(Container target);

    public void invalidateLayout(Container target);
}
