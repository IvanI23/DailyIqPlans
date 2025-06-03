package uk.ac.ucl.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SortPlanModel extends formatModel {
    String period;
    String SortType;
    public SortPlanModel(String date, String period, String sortType) {
        super(date);
        this.period = period;
        this.SortType = sortType;
    }

    //Initiates the sorting process
    public final HashMap<String, List<List<String>>> getSortPlan() {
        List <CSVRecord> records = readPlan(super.filename);
        return super.formatPlan(records);
    }

    //Function to store related records
    @Override
    public final List<CSVRecord> readPlan(String fileName) {
        List<CSVRecord> data = new ArrayList<>();
        String day;
        String period;
        String SortType;

        try (Reader reader = new FileReader(filename);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            for (CSVRecord csvRecord : csvParser) {
                day = csvRecord.get(0);
                period = csvRecord.get(2);
                SortType = csvRecord.get(5);
                if (day.equals(this.day)) {
                    if (!period.equals(this.period)) {
                        data.add(csvRecord);
                    } else if (SortType.equals(this.SortType)) {
                        data.add(csvRecord);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}
