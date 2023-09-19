package uga.cs4370.mydb.impl;

import java.util.List;
import java.util.ArrayList;

import uga.cs4370.mydb.Cell;
import uga.cs4370.mydb.Type;

public class Relation implements uga.cs4370.mydb.Relation {

    private String name; // table name
    private int size = 0; // ammount of rows in the table
    private List<List<Cell>> table; // contains the structure for the table 
    private List<String> attrs; // the names of the attributes of the table
    private List<Type> types; // the names of the types of each column

    /*
    * Constructor for a table/relation, takes in name, all of the names of the attributes,
    * and the names of the types of each column. The parameters receive the arguments from
    * the main class. However, the overall storage is only created within the constructor,
    * not the driver.
    */
    public Relation(String name, ArrayList<String> attrs, List<Type> types) {
        this.name = name;
        this.attrs = attrs;
        this.types = types;
        this.table = new ArrayList<List<Cell>>(); // creates the storage of this array 
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public List<List<Cell>> getRows() {
        return this.table;
    }

    @Override
    public List<Type> getTypes() {
        return this.types;
    }

    @Override
    public List<String> getAttrs() {
        return this.attrs;
    }

    @Override
    public boolean hasAttr(String attr) {
        if (this.attrs.contains(attr)) {
            return true;
        } 
        throw new IllegalArgumentException("Does not contain the specified attribute");
    }

    @Override
    public int getAttrIndex(String attr) {
        if (this.hasAttr(attr)) {
            return this.attrs.indexOf(attr);
        }
        throw new IllegalArgumentException("Does not contain the specified attribute");
    }

    @Override
    public void insert(Cell... cells) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @Override
    public void insert(List<Cell> cells) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @Override
    public void print() {
        throw new UnsupportedOperationException("Method not implemented yet");
    }
    
}
