package com.joshepen.everything.objects;

import java.io.File;
import java.util.*;


public class DirectoryContents extends Observable implements Observer{
    private final int MAX_NUM_DIRS = 3;
    private File dir;
    private ArrayList<File> files;
    private DisplayData displayData;
    private String searchTerm;
    private boolean caseSensitive;
    private boolean recursive;
    private boolean ascending;
    private String sortBy;
    private SearchThread searchObject;
    private Thread searchThread;
    
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
        /*
         * This fetches all the files in the current directory
         * and then updates the display data
         */
        files = getFiles(dir, 1);
        search();
    }

    public void search(){
        if(searchThread != null){
            searchThread.interrupt();
        }
        searchObject = new SearchThread(files, searchTerm, sortBy, caseSensitive, ascending,dir);
        searchObject.addObserver(this);
        searchThread = new Thread(searchObject);
        searchThread.start();
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

    public DisplayData getDisplayData(){
        return displayData;
    }

    

    private void sort(String columnName){
        displayData.sortByColumn(columnName, ascending);
    }

    public void update(Observable o, Object arg){
        displayData = searchObject.getDisplayData();
        setChanged();
        notifyObservers();
    }
}
