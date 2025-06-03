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

public class DeleteModel {
    private final String date;
    private final String period;
    private final String subject;
    private final String time;
    private final String note;
    private final String complete;

    public DeleteModel(String date, String subject, String period, String time, String note, String complete) {
        this.date = date;
        this.period = period;
        this.subject = subject;
        this.time = time;
        this.note = note;
        this.complete = complete;
    }

    //Method to delete specific record
    final public void delete() throws IOException {
        String filename = "data/plans.csv";
        List<CSVRecord> newRecord= filterRecord(filename);
        rewriteFile(filename, newRecord);
    }

    // Filters out the matching record and returns the updated list of records
    final public List<CSVRecord> filterRecord(String filename) throws IOException {
        List<CSVRecord> records = new ArrayList<CSVRecord>();
        List<String> activity = Arrays.asList(this.date, this.subject, this.period, this.time, this.note, this.complete);
        List<String> compare;

        try (FileReader reader = new FileReader(filename);
             CSVParser parser = CSVParser.parse(reader, CSVFormat.DEFAULT)) {
            for (CSVRecord record : parser) {
                compare = Arrays.asList(record.get(0), record.get(1), record.get(2), record.get(3), record.get(4), record.get(5));

                // Add record to the new list if it does not match the target record
                if (!compare.equals(activity)){
                    records.add(record);
                }
            }
        }
        return records;
    }

    // Rewrites the CSV file with the updated list of records
    final public void rewriteFile(String filename, List<CSVRecord> records) throws IOException {
        String activity;
        try (FileWriter writer = new FileWriter(filename);){
            for (CSVRecord record : records) {
                // Construct the CSV row and write it to the file
                activity = record.get(0)+","+record.get(1)+","+record.get(2)
                        +","+record.get(3)+","+record.get(4)+","+record.get(5);
                writer.write(activity+"\n");
            }
        }

    }
}
