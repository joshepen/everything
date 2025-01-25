package com.joshepen.everything.objects;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.regex.Pattern;

public class SearchThread extends Observable implements Runnable {
  private DisplayData displayData;
  private List<File> files;
  String searchTerm;
  String sortBy;
  boolean caseSensitive;
  boolean ascending;
  File dir;

  public SearchThread(List<File> files, String searchTerm, String sortBy, boolean caseSensitive, boolean ascending,
      File dir) {
    this.files = files;
    this.searchTerm = searchTerm;
    this.sortBy = sortBy;
    this.caseSensitive = caseSensitive;
    this.ascending = ascending;
    this.dir = dir;
  }

  public void run() {
    searchName(searchTerm);
    sort(sortBy);

    setChanged();
    notifyObservers();
  }

  private void searchName(String term) {
    String[] columnNames = { "Name", "Path", "File Size" };
    List<List<String>> data = new ArrayList<>();

    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> paths = new ArrayList<>();
    ArrayList<String> sizes = new ArrayList<>();

    data.add(names);
    data.add(paths);
    data.add(sizes);

    if (!caseSensitive)
      term = term.toLowerCase();
    if(term.equals(""))
      term = "*";
    String regex = termToRegex(term);
    String currFileName;
    File currFile;
    for (int i = 0; i < files.size(); i++) {
      currFileName = files.get(i).getName();
      if (!caseSensitive)
        currFileName = currFileName.toLowerCase();
      if (currFileName.matches(regex)) {
        currFile = files.get(i);
        names.add(currFile.getName());
        paths.add(currFile.getParent().substring(dir.getPath().length()));
        sizes.add(readableFileSize(currFile.length()));
      }
    }

    displayData = new DisplayData(columnNames, data);
  }

  /*
   * Purpose: Convert a number of bytes to a readable file size (23 MB, 1.2 GB,
   * etc)
   * Source:
   * https://stackoverflow.com/questions/3263892/format-file-size-as-mb-gb-etc
   */
  private static String readableFileSize(long size) {
    if (size <= 0)
      return "0";
    final String[] units = new String[] { "B", "kB", "MB", "GB", "TB", "PB", "EB" };
    int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
    return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
  }

  private String termToRegex(String term) {
    String regex = "";
    for (char c : term.toCharArray()) {
      switch (c) {
        case '*':
          regex += ".*";
          break;
        case '%':
          regex += ".";
          break;
        case '.':
          regex += "\\.";
          break;
        default:
          regex += Pattern.quote(String.valueOf(c));
          break;
      }
    }
    return regex;
  }

  private void sort(String columnName) {
    displayData.sortByColumn(columnName, ascending);
  }

  public DisplayData getDisplayData() {
    return displayData;
  }
}
