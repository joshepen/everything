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

    public void sortByColumn(String columnName, boolean ascending){
        int columnIndex = -1;
        for(int i=0;i<columnNames.length;i++){
            if(columnName.equals(columnNames[i])){
                columnIndex = i;
            }
        }

        if(columnIndex < 0 || columnIndex >= columnNames.length){
            return; // TODO deal with errors here somehow
        }
        quickSort(data.get(columnIndex), 0, data.get(columnIndex).size(), ascending);
        
    }

    private void quickSort(List<String> list, int start, int end, boolean ascending){
        int pivotIndex = start;
        int evalIndex = pivotIndex + 1;
        double comparison;
        while(evalIndex < end){
            comparison = list.get(evalIndex).toLowerCase().compareTo(list.get(pivotIndex).toLowerCase());
            if((ascending && comparison < 0) || (!ascending && comparison > 0)){
                moveAllColumns(pivotIndex, evalIndex);
                pivotIndex++;
            }
            evalIndex++;
        }
        if(pivotIndex - start > 1)
            quickSort(list, start, pivotIndex, ascending);
        if(end - (pivotIndex + 1) > 1)
            quickSort(list, pivotIndex + 1, end, ascending);
    }


    private void moveAllColumns(int to, int from){
        for(List<String> column: data){
            column.add(to, column.remove(from));
        }
    }
}
