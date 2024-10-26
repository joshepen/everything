package com.joshepen.everything.objects;

import java.util.List;

/*
 * This is just an object that holds the data to be stored in the results table.
 */
public class DisplayData {
    public String[] columnNames;
    public List<List<String>> data;

    public DisplayData(String[] columnNames, List<List<String>> data){
        this.columnNames = columnNames;
        this.data = data;
    }
}
