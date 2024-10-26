package com.joshepen.everything.objects;

import java.io.File;
import java.util.*;

public class DirectoryContents {
    File dir;
    File[] files;

    public void setFileList(String path){
        dir = new File(path);
        refreshFiles();
    }
    

    public void refreshFiles(){
        files = dir.listFiles();
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
        for(int i=0; i<files.length; i++){
            currFile = files[i];
            names.add(currFile.getName());
            paths.add(currFile.getParent().substring(dir.getPath().length()));
            sizes.add(Double.toString(currFile.length()));
        }

        return new DisplayData(columnNames, data);
    }
}
