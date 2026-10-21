

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {


    private static final String INPUT_FILE = "ace.csv";
    private static final String OUTPUT_FILE = "max_ace.txt";

 
    public static void main(String[] args) {

      
        ArrayList<HurricaneRowData> seasons = readData(INPUT_FILE);

    
        if (seasons.isEmpty()) {
            System.out.println("No data was read from " + INPUT_FILE
                    + ". Check that the file is in the project folder.");
            return;
        }

     
        System.out.println("Read " + seasons.size() + " rows of data.");

        int year = yearOfMaxAce(seasons);

        HurricaneRowData maxRow = rowForYear(seasons, year);

        String report = String.format(
                "The maximum ACE index was %.0f, which occurred in %d.",
                maxRow.getAce(), year);

        System.out.println(report);
        System.out.println("Full row -> " + maxRow);

        writeReport(OUTPUT_FILE, report + System.lineSeparator()
                + "Full row -> " + maxRow);
    }

    private static ArrayList<HurricaneRowData> readData(String fileName) {

        ArrayList<HurricaneRowData> rows = new ArrayList<HurricaneRowData>();

        try {
            Scanner fileScanner = new Scanner(new File(fileName));

            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");

                if (parts.length < 5) {
                    continue;
                }

                try {
                    int year = Integer.parseInt(clean(parts[0]));
                    double ace = Double.parseDouble(clean(parts[1]));
                    int tropicalStorms = Integer.parseInt(clean(parts[2]));
                    int hurricanes = Integer.parseInt(clean(parts[3]));
                    int majorHurricanes = Integer.parseInt(clean(parts[4]));

                    rows.add(new HurricaneRowData(year, ace, tropicalStorms,
                            hurricanes, majorHurricanes));

                } catch (NumberFormatException e) {
                    continue;
                }
            }

            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Could not find the file: " + fileName);
        }

        return rows;
    }

    private static String clean(String field) {
        return field.replace("\"", "")   // remove any double-quote characters
                    .replace(" ", "")    // remove spaces
                    .replace("*", "")    // remove footnote asterisks
                    .trim();             // remove leftover whitespace and \r
    }

    private static int yearOfMaxAce(ArrayList<HurricaneRowData> seasons) {

        HurricaneRowData best = seasons.get(0);

        for (int i = 1; i < seasons.size(); i++) {

            if (seasons.get(i).getAce() > best.getAce()) {
                best = seasons.get(i);   // found a bigger one - it's the new best
            }
        }

        return best.getYear();
    }

    private static HurricaneRowData rowForYear(ArrayList<HurricaneRowData> seasons,
                                               int year) {
        for (HurricaneRowData row : seasons) {
            if (row.getYear() == year) {
                return row;   // return immediately - no need to check the rest
            }
        }
        return null;
    }

    private static void writeReport(String fileName, String text) {
        try {
            PrintWriter writer = new PrintWriter(fileName);
            writer.println(text);
            writer.close();
            System.out.println("Results also written to " + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Could not write to the file: " + fileName);
        }
    }
}
