package com.joshepen.everything.objects;
import java.util.Vector;


public class DisplayItem{
  private Vector<String> names;
  private Vector<String> values;
  public DisplayItem(){
      names = new Vector<>();
      values = new Vector<>();
  }
  public void addAttribute(String name, String value){
      if(names.contains(name)) {
          values.remove(names.indexOf(name));
          names.remove(name);
      }
      names.add(name);
      values.add(value); 
  }
  public String getAttribute(String name){
      return values.get(names.indexOf(name));
  }

  public Vector<String> getVector(){
      return values;
  }
}