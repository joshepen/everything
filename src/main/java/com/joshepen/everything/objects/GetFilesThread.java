package com.joshepen.everything.objects;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class GetFilesThread extends Observable implements Runnable{
  private final int maxRecursionDepth;
  private boolean recursive;
  private File dir;
  private ArrayList<File> files;
  
  public GetFilesThread(boolean recursive, File dir, int maxRecursionDepth){
    this.recursive = recursive;
    this.dir = dir;
    this.maxRecursionDepth = maxRecursionDepth;
  }

  public void run(){
    files = getFiles(dir, 1);

    setChanged();
    notifyObservers();
  }

  public ArrayList<File> getFiles(File dir, int numDirs) {
    File[] currFiles = dir.listFiles();
    ArrayList<File> outFiles = new ArrayList<>();
    if (currFiles != null) {
      for (File file : currFiles) {
        if (file.isFile()) {
          outFiles.add(file);
        }
      }

      // I need breadth first traversal so I have to loop a second time for dirs
      if (recursive) {
        for (File file : currFiles) {
          if (file.isDirectory() && numDirs < maxRecursionDepth) {
            outFiles.addAll(getFiles(file, numDirs + 1));
          }
        }
      }

    }
    return outFiles;
  }
  public ArrayList<File> getFiles(){
    return files;
  }
}
