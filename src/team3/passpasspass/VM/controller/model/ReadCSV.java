package team3.passpasspass.VM.controller.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class ReadCSV {

    public static ArrayList<String[]> readCSV(String fileName) {
        try {
            BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
//            file.readLine();
            String record = file.readLine();
            ArrayList<String[]> data = new ArrayList<>();

            while (record != null) {
                String[] item = record.split(",");
                data.add(item);
                record = file.readLine();
//                System.out.println(record);
//                System.out.println(data);

            }
            return data;
        } catch (Exception e) {
            System.out.println("Exception thrown :" + e);

        }
        return null;
    }
}
