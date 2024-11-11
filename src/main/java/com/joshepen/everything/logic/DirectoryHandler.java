package com.joshepen.everything.logic;

import javax.swing.JFileChooser;

import java.util.Observable;
import java.util.Observer;
import com.joshepen.everything.objects.DirectoryContents;
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
    }

    public void setRecursive(boolean isRecursive){
        dirContents.setRecursive(isRecursive);
    }

    public void setAscending(boolean ascending){
        dirContents.setAscending(ascending);
    }

    public void setSortBy(String columnName){
        dirContents.setSortBy(columnName);
    }

    public void chooseDir(){
        dirContents.setDirectory(promptDirectory());
        dirContents.refreshFiles();
        ui.setResults(dirContents.getDisplayData());
    }

    public void search(String term){
        dirContents.setSearchTerm(term);
        dirContents.refreshFiles();
        // ui.setResults(dirContents.getDisplayData());
    }

    public void update(Observable o, Object arg){
        ui.setResults(dirContents.getDisplayData());
    }
}
