package com.joshepen.everything.objects;

import java.io.File;
import java.util.*;

public class DirectoryContents {
    File dir;
    File[] files;
    ArrayList<File> processedFiles;
    String searchTerm;
    boolean caseSensitive;
    
    public DirectoryContents(){
        searchTerm = "";
        caseSensitive = false;
        setDirectory("C:\\");
        refreshFiles();
    }

    public void refreshFiles(){
        files = dir.listFiles();
        searchName(searchTerm);
        sort();
    }

    public void setSearchTerm(String term){
        searchTerm = term;
    }

    public void setDirectory(String path){
        dir = new File(path);
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public DisplayData getDisplayData(){
        String[] columnNames = {"Name","Path","Size (MB)"};
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
            sizes.add(Double.toString(currFile.length()));
        }

        return new DisplayData(columnNames, data);
    }

    private void searchName(String term){
        if(!caseSensitive) term = term.toLowerCase();
        processedFiles = new ArrayList<>();
        String currFileName;
        for(int i=0;i<files.length;i++){
            currFileName = files[i].getName();
            if(!caseSensitive) currFileName = currFileName.toLowerCase();

            if(currFileName.contains(term)){
                processedFiles.add(files[i]);
            }
        }
    }

    private void sort(){
        Collections.sort(processedFiles, new Comparator<File>() {
            public int compare(File f1, File f2) {
                return f1.getName().compareTo(f2.getName());
            }
        });
    }
}
