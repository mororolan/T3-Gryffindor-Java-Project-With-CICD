package team3.passpasspass.VM.controller.model;

import java.io.BufferedReader;
import java.io.FileReader;

public class MaintainerFileManager {

	public int getLines(String relativeFilePath) {
		int fileLines = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(relativeFilePath));
			while((reader.readLine())!=null) {
				fileLines++;
			}
			reader.close();
			fileLines--;	//For the title line
		}catch(Exception e) {
			e.printStackTrace();
		}
		return fileLines;
	}
	
	public int getTotalCash(String relativeFilePath) {
		int totalCash = 0;
		try {
			BufferedReader coinsReader = new BufferedReader(new FileReader(relativeFilePath));
			String line = null;
			int lineIndex = 0;
			while((line=coinsReader.readLine())!=null) {
				String item[] = line.split(",");
				//For the title line
				if(lineIndex > 0) {
					totalCash = totalCash + Integer.parseInt(item[0])*Integer.parseInt(item[1]);
				}
			}
			coinsReader.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return totalCash;
	}
}
