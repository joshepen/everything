package com.joshepen.everything.logic;

import javax.swing.JFileChooser;

public class DirectoryHandler{
    public static String promptDirectory(){
        JFileChooser dirChooser = new JFileChooser();
        dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        dirChooser.showOpenDialog(dirChooser);
        return dirChooser.getSelectedFile().getAbsolutePath();
    }
}
