 package uga.cs4370.mydb.impl;

import java.util.ArrayList;
import java.util.List;

import uga.cs4370.mydb.Cell;
import uga.cs4370.mydb.Relation;

public class PredicateImpl implements uga.cs4370.mydb.Predicate {

    private String attribute = "";
    private String attribute2 = "";
    private String attrValue = "";
    private List <String> attributes = new ArrayList<>();
    private int firstIndex;
    private int lastIndex;

    public PredicateImpl (String condition, List <String> attributes) {
        this.attributes = attributes;
        if (condition.contains("\"")) { //Ex. attr = "specific value"
            String[] parts = condition.split("=");
            attribute = parts[0];
            attrValue = parts[1];
            attrValue = attrValue.substring(1, attrValue.length()-1);//get rid of quotations
        }
    }

    public PredicateImpl (Relation rel1, Relation rel2, String condition) {
        String[] parts = condition.split("=");
        this.attribute = parts[0];
        this.attribute2 = parts[1];
        this.firstIndex = rel1.getAttrIndex(this.attribute);
        this.lastIndex = rel2.getAttrIndex(this.attribute2) + rel1.getAttrs().size();
        //System.out.println("index: " + rel2.getAttrIndex(this.attribute2));
        //System.out.println("size: " + rel1.getSize());
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
            try {
                if (row.get(firstIndex).toString().equals(row.get(lastIndex).toString())) {
                    return true;
                }
            } catch (IndexOutOfBoundsException e) {
                //System.out.println("Row: " + row);
                //System.out.println("First index: " + firstIndex);
                //System.out.println("Last index: " + lastIndex);
                throw new IndexOutOfBoundsException();
            }
        }
        return false;
    }
}

