package com.joshepen.everything.logic;

import javax.swing.JFileChooser;
import java.io.File;

public class DirectoryHandler{
    private JFileChooser dirChooser;
    private File currentDirectory;

    public DirectoryHandler(){
        dirChooser = new JFileChooser();
        currentDirectory = null;
    }

    /*
     * Display new pop-up and allow user to choose directory
     */
    public File setNewDirectory(){        
        dirChooser.showOpenDialog(dirChooser);
        currentDirectory = dirChooser.getSelectedFile();
        return currentDirectory;
    }

    public File getCurrentDirectory(){
        return currentDirectory;
    }

    public File[] getDirectoryFile(){
        return currentDirectory.listFiles();
    }

}
