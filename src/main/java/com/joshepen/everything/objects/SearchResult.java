/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joshepen.everything.objects;

/**
 *
 * @author joshu
 */
public class SearchResult {
    public String filename;
    public String filepath;
    public boolean isFile;
    
    public SearchResult(String filename, String filepath, boolean isFile){
        this.filename = filename;
        this.filepath = filepath;
        this.isFile = isFile;
    }
}
