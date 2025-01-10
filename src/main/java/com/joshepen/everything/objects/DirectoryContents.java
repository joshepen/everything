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
    private GetFilesThread getFilesObject;
    private Thread getFilesThread;
    
    public DirectoryContents(){
        searchTerm = "";
        caseSensitive = false;
        setDirectory("C:\\");
        recursive = false;
        ascending = true;
        sortBy = "";
        getFiles();
    }

    public void refreshFiles(){
        /*
         * This fetches all the files in the current directory
         * and then updates the display data
         */
        getFiles();
    }

    public void getFiles(){
        if(getFilesThread != null){
            getFilesThread.interrupt();
        }
        getFilesObject = new GetFilesThread(recursive,dir,MAX_NUM_DIRS);
        getFilesObject.addObserver(this);
        getFilesThread = new Thread(getFilesObject);
        getFilesThread.start();
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

    public void update(Observable o, Object arg){
        if(o.getClass() == getFilesObject.getClass()){
            files = getFilesObject.getFiles();
            search();
        }
        if(searchObject != null){
            displayData = searchObject.getDisplayData();
        }
        
        setChanged();
        notifyObservers();
    }
}
