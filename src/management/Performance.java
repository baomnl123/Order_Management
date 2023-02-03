package management;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Performance {
    private static Scanner sc = new Scanner(System.in);

    public static String getID(String type, String data, String format) {
        String id;
        while (true) {
            System.out.print("Enter " + type + "'s ID" + data + " (X is a digit): ");
            id = sc.nextLine();
            if (!id.matches(format)) {
                System.out.println("Wrong format");
            } else {
                return id;
            }
        }
    }

    public static String getName(String type) {
        String name;
        do {
            System.out.print("Enter " + type + "'s name: ");
            name = sc.nextLine();
            if (name == null) {
                System.out.print(type + "'s name is required!");
            }
        } while (name == null);

        return type;

    }

    public static List<String> writeLineToFile(String fName, List<String> tmp) {
        try {
            File f = new File(fName);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);

            for (String x : tmp) {
                pw.println(x);
            }
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("Could not save to file!");
        }
        return null;
    }

    public static List<String> readLineFromFile(String fName) {
        List listFile = new ArrayList<>();
        File f = new File(fName);

        if (f.exists()) {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line = "";
                while (line != null) {
                    line = line.trim();
                    if (!line.isEmpty()) {
                        listFile.add(line);
                    }
                    line = br.readLine();
                }
                br.close();
                fr.close();
            } catch (Exception e) {
                System.out.println("Could not read the file!");
            }
        }
        return listFile;
    }

}
