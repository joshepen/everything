package com.joshepen.everything.objects;

import java.io.File;
import java.util.*;

public class DirectoryContents {
    private final int MAX_NUM_DIRS = 3;
    private File dir;
    private ArrayList<File> files;
    private ArrayList<File> processedFiles;
    private String searchTerm;
    private boolean caseSensitive;
    private boolean recursive;
    
    public DirectoryContents(){
        searchTerm = "";
        caseSensitive = false;
        setDirectory("C:\\");
        refreshFiles();
        recursive = false;
    }

    public void refreshFiles(){
        files = getFiles(dir, 1);
        searchName(searchTerm);
        sort();
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
        for(int i=0;i<files.size();i++){
            currFileName = files.get(i).getName();
            if(!caseSensitive) currFileName = currFileName.toLowerCase();

            if(currFileName.contains(term)){
                processedFiles.add(files.get(i));
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
