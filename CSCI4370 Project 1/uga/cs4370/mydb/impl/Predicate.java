package uga.cs4370.mydb.impl;

import java.util.ArrayList;
import java.util.List;

import uga.cs4370.mydb.Cell;

public class Predicate implements uga.cs4370.mydb.Predicate {

    private String condition;
    private String attribute = "";
    private String attribute2 = "";
    private String attrValue = "";
    private List <String> attributes = new ArrayList<>();
    public Predicate (String condition, List <String> attributes) {
        this.condition = condition;
        this.attributes = attributes;
        if (condition.contains("\"")) { //Ex. attr = "specific value"
            attribute = condition.split("=")[0];
            attrValue = condition.split("=")[1];
            attrValue = condition.substring(1, attrValue.length()-1);//get rid of quotations
        }
        else {
            attribute = condition.split("=")[0];
            attribute2 = condition.split("=")[1];
        }
    }

    public String getAttribute() {
        return attribute;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public String getAttribute2() {
        return attribute2;
    }
    /**
     * Checks a row for a condition and returns true
     * if the row passes the predicate.
     */
    public boolean check(List<Cell> row) {
        int index1 = -1;
        int index2 = -1;
        
        if (!attrValue.equals("")) { //CASE 1: attr = "specific value"
            for (int i = 0; i < attributes.size(); i++) {//find index of attribute1
                if (attributes.get(i).equals(attribute)) {
                    index1 = i;
                }
            }
            //found the index
            if (row.get(index1).toString().equals(attrValue)) {
                return true;
            }
        }
        else { //CASE 2: attr = attr2
            for (int i = 0; i < attributes.size(); i++) {//find index of attribute1
                if (attributes.get(i).equals(attribute)) {
                    index1 = i;
                }
            }
            for (int i = 0; i < attributes.size(); i++) {//find index of attribute2
                if (attributes.get(i).equals(attribute2)) {
                    index2 = i;
                }
            }
            if (row.get(index1).equals(row.get(index2))) { //if data of cell at index1 == data of cell at index2
                return true;
            }
        }
        
        return false;
    }
    
    
}

