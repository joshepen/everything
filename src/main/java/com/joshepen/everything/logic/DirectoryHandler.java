package com.joshepen.everything.logic;

import javax.swing.JFileChooser;

import com.joshepen.everything.objects.DirectoryContents;
import com.joshepen.everything.ui.*;

public class DirectoryHandler{
    private DirectoryContents dirContents;
    private iUI ui;
    public DirectoryHandler(iUI ui){
        dirContents = new DirectoryContents();
        this.ui = ui;
    }
    private String promptDirectory(){
        JFileChooser dirChooser = new JFileChooser();
        dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        dirChooser.showOpenDialog(dirChooser);
        return dirChooser.getSelectedFile().getAbsolutePath();
    }

    public void chooseDir(){
        dirContents.setDirectory(promptDirectory());
        dirContents.refreshFiles();
        ui.setResults(dirContents.getDisplayData());
    }
}
