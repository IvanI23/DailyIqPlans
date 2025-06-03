package uk.ac.ucl.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchModel extends formatModel {
    List<String> search;

    public SearchModel(String day, List<String> search) {
        super(day);
        this.search = search;
    }

    //Method initiates and returns search result
    public final HashMap<String, List<List<String>>> returnSearch() throws IOException {
        List <CSVRecord> records = readPlan(super.filename);
        return super.formatPlan(records);
    }

    //Reads plan till matches are found
    @Override
    public final List<CSVRecord> readPlan(String filename) {
        List<CSVRecord> records = new ArrayList<CSVRecord>();
        String[] deepSearch;
        try (FileReader fileReader = new FileReader(filename);
            CSVParser parser = CSVParser.parse(fileReader, CSVFormat.DEFAULT)){
            for (CSVRecord record : parser) {
                for (String search : search) {
                    for(String items : record) {
                        //Deep search allows any equivalent keywords to be detected
                        deepSearch = items.split(" ");
                        for (String item : deepSearch) {
                            if (search.equals(item)) {
                                records.add(record);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }



}
