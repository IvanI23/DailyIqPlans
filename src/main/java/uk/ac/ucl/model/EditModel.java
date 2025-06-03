package uk.ac.ucl.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditModel {
    List<String> oldActivity;
    List<String> newActivity;
    String url;
    String filename = "data/plans.csv";

    public EditModel(List<String> oldActivity, List<String> newActivity) {
        this.oldActivity = oldActivity;
        this.newActivity = newActivity;
    }

    //Method that commits the edit.
    public final void edit() throws IOException {
        List<List<String>> record = editCSV(filename);
        reBuildCSV(filename, record);
    }

    //Commits the changes to the CSV.
    public final List<List<String>> editCSV(String filename) throws IOException {
        List<List<String>> records = new ArrayList<>();
        List<String> compare;

        try (FileReader reader = new FileReader(filename);
             CSVParser parser = CSVParser.parse(reader, CSVFormat.DEFAULT)) {
            for (CSVRecord record : parser) {
                //Records are added via for loop to maintain order
                compare = new ArrayList<>(Arrays.asList(record.get(0), record.get(1), record.get(2), record.get(3), record.get(4), record.get(5)));
                if (compare.equals(oldActivity)) {
                    records.add(this.newActivity);
                }
                else{
                    compare.add(record.get(6));
                    records.add(compare);
                }
            }
        }
        return records;
    }

    //Rewrites the CSV file with the edit commited
    public final void reBuildCSV(String filename, List<List<String>> records) throws IOException {
        String activity;
        try (FileWriter writer = new FileWriter(filename);){
            for (List<String> record: records) {
                activity = record.get(0) + "," + record.get(1) + "," + record.get(2)
                        + "," + record.get(3) + "," + record.get(4) + "," + record.get(5) +  "," + record.get(6);
                writer.write(activity + "\n");
            }
        }
    }
}
