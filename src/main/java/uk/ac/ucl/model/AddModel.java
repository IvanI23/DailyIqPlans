package uk.ac.ucl.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AddModel{
    //Method to add entry
    final public void addEntry(List<String> data) {
        String filename = "data/plans.csv";
        add(filename, data);
    }

    //Writes new entry to CSV file
    final public void add(String fileName, List<String> data) {
        try (FileWriter writer = new FileWriter(fileName, true);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
