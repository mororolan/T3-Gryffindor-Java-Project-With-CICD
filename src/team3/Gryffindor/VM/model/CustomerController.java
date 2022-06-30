package team3.Gryffindor.VM.model;

//import javafx.scene.control.TextFormatter;

import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    private List<ChangeMemento> mementoList = new ArrayList<ChangeMemento>();

    public void add(ChangeMemento state){
        mementoList.add(state);
    }

    public ChangeMemento get(int index){
        return mementoList.get(index);
    }
}
