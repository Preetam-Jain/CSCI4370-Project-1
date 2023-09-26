 package uga.cs4370.mydb.impl;

import java.util.ArrayList;
import java.util.List;

import uga.cs4370.mydb.Cell;

public class PredicateImpl implements uga.cs4370.mydb.Predicate {

    private String condition;
    private String attribute = "";
    private String attribute2 = "";
    private String attrValue = "";
    private List <String> attributes = new ArrayList<>();
    
    public PredicateImpl (String condition, List <String> attributes) {
        this.condition = condition;
        this.attributes = attributes;
        if (condition.contains("\"")&&condition.contains(">=")) { //Ex. attr = "specific value"
            String[] parts = condition.split(">=");
            attribute = parts[0];
            attrValue = parts[1];
            attrValue = attrValue.substring(1, attrValue.length()-1);//get rid of quotations
        }
        else if (condition.contains("\"")&&condition.contains("<=")) { //Ex. attr = "specific value"
            String[] parts = condition.split("<=");
            attribute = parts[0];
            attrValue = parts[1];
            attrValue = attrValue.substring(1, attrValue.length()-1);//get rid of quotations
        }
        else if (condition.contains("\"")&&condition.contains("=")) { //Ex. attr = "specific value"
            String[] parts = condition.split("=");
            attribute = parts[0];
            attrValue = parts[1];
            attrValue = attrValue.substring(1, attrValue.length()-1);//get rid of quotations
        }
        else if (condition.contains("\"")&&condition.contains(">")) { //Ex. attr = "specific value"
            String[] parts = condition.split(">");
            attribute = parts[0];
            attrValue = parts[1];
            attrValue = attrValue.substring(1, attrValue.length()-1);//get rid of quotations
        }
        else if (condition.contains("\"")&&condition.contains("<")) { //Ex. attr = "specific value"
            String[] parts = condition.split("<");
            attribute = parts[0];
            attrValue = parts[1];
            attrValue = attrValue.substring(1, attrValue.length()-1);//get rid of quotations
        }
        else {
            String[] parts = condition.split("=");
            attribute = parts[0];
            attribute2 = parts[1];
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
        
        if (!attrValue.equals("")) { //CASE 1: "specific value"
            int maybe = -1;
            double maybe1 = -1;
            String maybe2 = null;
            try {
                maybe = Integer.parseInt(attrValue);
            }
            catch (Exception e) {
                try {
                    maybe1 = Double.parseDouble(attrValue);
                } catch (Exception f) {
                    maybe2 = attrValue;
                }
            }
            if (maybe != -1) {
                if (condition.contains(">=") && row.get(index1).getAsInt() >= (maybe)) {
                    return true;
                }
                else if (condition.contains("<=") && row.get(index1).getAsInt() <= (maybe)) {
                    return true;
                }
                else if (condition.contains("=") && row.get(index1).getAsInt() == (maybe)) {
                    return true;
                }
                else if (condition.contains(">") && row.get(index1).getAsInt() > (maybe)) {
                    return true;
                }
                else if (condition.contains("<") && row.get(index1).getAsInt() < (maybe)) {
                    return true;
                }
                return false;
            }
            else if (maybe1 != -1) {
                if (condition.contains(">=") && row.get(index1).getAsDouble() >= (maybe)) {
                    return true;
                }
                else if (condition.contains("<=") && row.get(index1).getAsDouble() <= (maybe)) {
                    return true;
                }
                else if (condition.contains("=") && row.get(index1).getAsDouble() == (maybe)) {
                    return true;
                }
                else if (condition.contains(">") && row.get(index1).getAsDouble() > (maybe)) {
                    return true;
                }
                else if (condition.contains("<") && row.get(index1).getAsDouble() < (maybe)) {
                    return true;
                }
                return false;
            }
            else if (row.get(index1).toString().equals(attrValue)) {
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

