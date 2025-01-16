package com.joshepen.everything.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;
import java.util.Vector;

/*
 * This is just an object that holds the data to be stored in the results table.
 */
public class DisplayData {
    private ArrayList<DisplayItem> displayItems;
    private String sortBy;
    public String[] columnNames;

    public DisplayData(String[] columnNames, List<List<String>> data){
        displayItems = new ArrayList<>();
        DisplayItem currDisplayItem;
        this.columnNames = columnNames;

        for(int i=0;i<data.get(0).size();i++){
            currDisplayItem = new DisplayItem();
            for(int j=0;j<data.size();j++){
                currDisplayItem.addAttribute(columnNames[j], data.get(j).get(i));
            }
            displayItems.add(currDisplayItem);
        }
    }

    public int size(){
        return displayItems.size();
    }
    public Vector<String> get(int index){
        return displayItems.get(index).getVector();
    }

    public void sortByColumn(String columnName, boolean ascending){
        sortBy = columnName;
        if(sortBy.equals("")){
            sortBy = columnNames[0];
        }
        Collections.sort(displayItems,new Comparator<DisplayItem>() {
            @Override
            public int compare(DisplayItem i1, DisplayItem i2){
                return (ascending ? 1:-1) * i1.getAttribute(sortBy).compareToIgnoreCase(i2.getAttribute(sortBy));
            }
        });     
    }
}
