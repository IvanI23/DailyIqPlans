package uk.ac.ucl.model;

import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public abstract class formatModel {
    String day;
    String filename= "data/plans.csv";

    public formatModel(String day) {
        this.day = day;
    }

    //Formats the plan in a JSP friendly format
    public HashMap<String, List<List<String>>> formatPlan(List<CSVRecord> records) {
        HashMap<String, List<List<String>>> planSorted = new HashMap<>();
        String time;

        planSorted.put(day, new ArrayList<>());
        planSorted.put("MORNING", new ArrayList<>());
        planSorted.put("AFTERNOON", new ArrayList<>());
        planSorted.put("EVENING", new ArrayList<>());

        for (CSVRecord record : records) {
            time = record.get(2);
            List<String> activity = Arrays.asList(record.get(1), record.get(3), record.get(4), record.get(5), record.get(6), record.get(0));
            planSorted.get(time).add(activity);
        }
        return planSorted;
    }

    public abstract List<CSVRecord> readPlan(String fileName);
}
