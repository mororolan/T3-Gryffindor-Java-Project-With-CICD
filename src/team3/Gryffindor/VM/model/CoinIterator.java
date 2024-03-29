package team3.Gryffindor.VM.model;

import java.util.ArrayList;

public class CoinIterator {
    ArrayList<String> changeSol;

    public CoinIterator(){
        changeSol = new ArrayList<>();
    }

    public ArrayList<String> changeIterator(ArrayList<Integer> coinsInfo, ArrayList<Integer> coinsNum, int change){

        if(findWay(coinsInfo,coinsNum,change))
            changeSol.add("True");
        else
            changeSol.add("false");

        return changeSol;
    }

    public boolean findWay(ArrayList<Integer> coinsInfo, ArrayList<Integer> coinsNum, int change){
        if (change<0)
            return false;
        if (change==0)
            return true;
        for(int i = 0; i < coinsInfo.size(); i++){ //next
            if(change < coinsInfo.get(i) || coinsNum.get(i)==0)
                continue;
            for (int j=0; j<coinsNum.get(i); j++){ //next
                coinsNum.set(i,coinsNum.get(i)-1);
                changeSol.add( String.valueOf(coinsInfo.get(i)) );
                if( findWay(coinsInfo,coinsNum,change-coinsInfo.get(i)) )
                    return true;
                changeSol.remove(changeSol.size()-1);
                coinsNum.set(i,coinsNum.get(i)+1);
            }
        }
        return false;
    }

}
