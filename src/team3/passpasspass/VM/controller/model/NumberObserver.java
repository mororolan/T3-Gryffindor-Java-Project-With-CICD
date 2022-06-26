package team3.passpasspass.VM.controller.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class NumberObserver {
	public void maintainerCollectCoins(String coinsFilePath) {
		Number coinsNumber = new CoinsNumber();
		VMPanel maintainerObserver = new MaintainerPanel();
		coinsNumber.add(maintainerObserver);
		maintainerObserver.changeNumber(0,coinsFilePath);
	}
}

//����Ŀ�꣺����
abstract class Number {
	protected List<VMPanel> panels = new ArrayList<VMPanel>();
	
	//���ӹ۲��߷���
	public void add(VMPanel panel) {
		panels.add(panel);
	}
	
	public abstract void change(int number);
}


//����Ŀ�꣺Ӳ������
class CoinsNumber extends Number{
	@Override
	public void change(int number) {
		for (VMPanel obs : panels) {
			((VMPanel) obs).changeNumber(number, null);
		}
	}
}


//����۲��ߣ�Vending Machine Panel
interface VMPanel{
	void changeNumber(int number, String filePath);
}


//����۲���1��Maintainer Panel
class MaintainerPanel implements VMPanel{
	@Override
	public void changeNumber(int coinsNumber, String coinsFilePath) {
		List<String[]> list = new ArrayList<>();
		String line = null;
   		try {
   			BufferedReader reader = new BufferedReader(new FileReader(coinsFilePath));
   			
    		while ((line=reader.readLine())!=null) {
    			list.add(line.split(","));
			}
    		reader.close();
    		
    		BufferedWriter writer = new BufferedWriter(new FileWriter(coinsFilePath));
    		
    		for(int i=1; i<list.size() ; i++) {
    			list.get(i)[1] = String.valueOf(coinsNumber);
    		};
    			
    		for (int i=0; i<list.size(); i++) {
    			for (int j = 0; j < list.get(i).length; j++) {
    				writer.write(list.get(i)[j].toString() + ",");
    				if (j == list.get(i).length - 1) {
    					writer.newLine();
    				}
    			}
    		}
    		writer.close();          			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


//����۲���2��Customer Panel
class CustomerPanel implements VMPanel{

	@Override
	public void changeNumber(int number, String path) {
		// TODO �Զ����ɵķ������
		
	}
}



