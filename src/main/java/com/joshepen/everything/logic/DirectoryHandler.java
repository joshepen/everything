package com.joshepen.everything.logic;

import javax.swing.JFileChooser;

import com.joshepen.everything.objects.DirectoryContents;
import com.joshepen.everything.ui.*;


/*
 * Purpose: handle passing of data and functions from the UI layer to the Display Data object.
 */
public class DirectoryHandler{
    private DirectoryContents dirContents;
    private iUI ui;
    public DirectoryHandler(iUI ui){
        dirContents = new DirectoryContents();
        this.ui = ui;
        
        ui.setResults(dirContents.getDisplayData());
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

    public void chooseDir(){
        dirContents.setDirectory(promptDirectory());
        dirContents.refreshFiles();
        ui.setResults(dirContents.getDisplayData());
    }

    public void search(String term){
        dirContents.setSearchTerm(term);
        dirContents.refreshFiles();
        ui.setResults(dirContents.getDisplayData());
    }
}
