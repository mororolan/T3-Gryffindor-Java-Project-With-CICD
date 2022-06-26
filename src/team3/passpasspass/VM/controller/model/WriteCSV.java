package team3.passpasspass.VM.controller.model;


import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteCSV {
    public static void writeCSV(ArrayList<String[]> writeList, String type) {
        String filePath;
        if (type.equals("drink")) {
            filePath = "./Data/dwd_drink_info.csv";
        } else if (type.equals("coin")) {
            filePath = "./Data/dwd_money_stat.csv";
        } else {
            throw new RuntimeException("Write Fail.");
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (int i = 0; i < writeList.size(); i++) {
                writer.write(String.join(",", writeList.get(i)) + "\n");
//                writer.newLine();
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
