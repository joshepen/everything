package com.joshepen.everything.objects;

import java.io.File;
import java.util.*;
import java.text.DecimalFormat;


public class DirectoryContents {
    private final int MAX_NUM_DIRS = 3;
    private File dir;
    private ArrayList<File> files;
    private ArrayList<File> processedFiles;
    private DisplayData displayData;
    private String searchTerm;
    private boolean caseSensitive;
    private boolean recursive;
    private boolean ascending;
    private String sortBy;
    
    public DirectoryContents(){
        searchTerm = "";
        caseSensitive = false;
        setDirectory("C:\\");
        recursive = false;
        ascending = true;
        sortBy = "";
        refreshFiles();
    }

    public void refreshFiles(){
        files = getFiles(dir, 1);
        searchName(searchTerm);
        setDisplayData();
        sort(sortBy);
    }

    public ArrayList<File> getFiles(File dir, int numDirs){
        File[] currFiles = dir.listFiles();
        ArrayList<File> outFiles = new ArrayList<>();
        if(currFiles != null){
            for(File file : currFiles){
                if (file.isFile()){
                    outFiles.add(file);
                }
            }

            // I need breadth first traversal so I have to loop a second time for dirs
            if(recursive){
                for(File file : currFiles){
                    if(file.isDirectory() && numDirs < MAX_NUM_DIRS){
                        outFiles.addAll(getFiles(file, numDirs + 1));
                    }
                }
            }
            
        }
        return outFiles;
    }

    public void setSearchTerm(String term){
        searchTerm = term;
    }

    public void setDirectory(String path){
        dir = new File(path);
    }

    public void setRecursive(boolean recursive){
        this.recursive = recursive;
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public void setAscending(boolean ascending){
        this.ascending = ascending;
    }

    public void setSortBy(String columnName){
        sortBy = columnName;
    }

    private void setDisplayData(){
        String[] columnNames = {"Name","Path","File Size"};
        List<List<String>> data = new ArrayList<>();
        
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> paths = new ArrayList<>();
        ArrayList<String> sizes = new ArrayList<>();

        data.add(names);
        data.add(paths);
        data.add(sizes);

        File currFile;
        for(int i=0; i<processedFiles.size(); i++){
            currFile = processedFiles.get(i);
            names.add(currFile.getName());
            paths.add(currFile.getParent().substring(dir.getPath().length()));
            sizes.add(readableFileSize(currFile.length()));
        }

        displayData = new DisplayData(columnNames, data);
    }

    public DisplayData getDisplayData(){
        return displayData;
    }

    /*
     * Purpose: Convert a number of bytes to a readable file size (23 MB, 1.2 GB, etc)
     * Source: https://stackoverflow.com/questions/3263892/format-file-size-as-mb-gb-etc
     */
    private static String readableFileSize(long size) {
        if(size <= 0) return "0";
        final String[] units = new String[] { "B", "kB", "MB", "GB", "TB", "PB", "EB" };
        int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }


    private void searchName(String term){
        if(!caseSensitive) term = term.toLowerCase();
        processedFiles = new ArrayList<>();
        String currFileName;
        for(int i=0;i<files.size();i++){
            currFileName = files.get(i).getName();
            if(!caseSensitive) currFileName = currFileName.toLowerCase();

            if(currFileName.contains(term)){
                processedFiles.add(files.get(i));
            }
        }
    }

    private void sort(String columnName){
        displayData.sortByColumn(columnName, ascending);
    }
}
