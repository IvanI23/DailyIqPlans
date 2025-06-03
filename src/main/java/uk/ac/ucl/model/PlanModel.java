package uk.ac.ucl.model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class PlanModel extends formatModel{

    public PlanModel(String wantedDay) {
        super(wantedDay);
    }

    //Sends the JSP friendly formatted plan back to servlet
    public final HashMap<String, List<List<String>>> getDaysPlan() {
        List<CSVRecord> records = readPlan(super.filename);
        return super.formatPlan(records);
    }

    //Reads and stores records from the particular day index
    @Override
    public final List<CSVRecord> readPlan(String filename){
        List<CSVRecord> data = new ArrayList<>();
        String day;

        try (Reader reader = new FileReader(filename);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            for (CSVRecord csvRecord : csvParser) {
                day = csvRecord.get(0);
                if (day.equals(super.day)) {
                    data.add(csvRecord);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}

