package com.joshepen.everything.logic;

import javax.swing.JFileChooser;

import java.util.Observable;
import java.util.Observer;
import com.joshepen.everything.objects.DirectoryContents;
import com.joshepen.everything.objects.DisplayData;
import com.joshepen.everything.ui.*;


/*
 * Purpose: handle passing of data and functions from the UI layer to the Display Data object.
 */
public class DirectoryHandler implements Observer{
    private DirectoryContents dirContents;
    private iUI ui;
    public DirectoryHandler(iUI ui){
        dirContents = new DirectoryContents();
        this.ui = ui;
        dirContents.addObserver(this);
    }
    private String promptDirectory(){
        JFileChooser dirChooser = new JFileChooser();
        dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        dirChooser.showOpenDialog(dirChooser);
        return dirChooser.getSelectedFile().getAbsolutePath();
    }

    public void setCaseSensitivity(boolean isCaseSensitive){
        dirContents.setCaseSensitive(isCaseSensitive);
        dirContents.search();
    }

    public void setRecursive(boolean isRecursive){
        dirContents.setRecursive(isRecursive);
        dirContents.refreshFiles();
    }

    public void setSearchDepth(int depth){
        dirContents.setSearchDepth(depth);
        dirContents.refreshFiles();
    }

    public void setAscending(boolean ascending){
        dirContents.setAscending(ascending);
        dirContents.search();
    }

    public void setSortBy(String columnName){
        dirContents.setSortBy(columnName);
        dirContents.search();
    }

    public void chooseDir(){
        dirContents.setDirectory(promptDirectory());
        dirContents.refreshFiles();
    }

    public void refresh(){
        dirContents.refreshFiles();
    }

    public void search(String term){
        dirContents.setSearchTerm(term);
        dirContents.search();
    }

    public void update(Observable o, Object arg){
        DisplayData dd = dirContents.getDisplayData();
        if(dd != null){
            ui.setResults(dd);
        }
    }
}
