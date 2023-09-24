package uga.cs4370.mydb.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import uga.cs4370.mydb.Cell;
import uga.cs4370.mydb.Type;

public class Relation implements uga.cs4370.mydb.Relation {

    private String name; // table name
    private int size = 0; // ammount of rows in the table
    private List<List<Cell>> table; // contains the structure for the table 
    private List<String> attrs; // the names of the attributes of the table
    private List<Type> types; // the names of the types of each column
    private HashMap<Integer, String> primaryKeys = new HashMap<>(); // stores the indexes of the primary keys in a hashmap,
    private HashMap<Integer, String> foreignKeys = new HashMap<>();

    /*
    * Constructor for a table/relation, takes in name, all of the names of the attributes,
    * and the names of the types of each column. The parameters receive the arguments from
    * the main class. However, the overall storage is only created within the constructor,
    * not the driver.
    */
    public Relation(String name, List<String> attrs, List<Type> types) {
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
        int index = 0;
        ArrayList<Cell> row = new ArrayList<Cell>();
        for (Cell cell : cells) {
            if (types.get(index) == cell.getType()) {
                row.add(cell);
            } else {
                throw new IllegalArgumentException("Cell does not correspond to attribute type" + cell.getType());
            }
        }
        this.table.add(row);
    }

    @Override
    public void insert(List<Cell> cells) {
        for (int i = 0; i < cells.size(); i++) {
            if (types.get(i) != cells.get(i).getType()) {
                throw new IllegalArgumentException("Cell does not correspond to attribute type" + cells.get(i).getType());
            } else if (primaryKeys.containsKey(i)) {

            }
        }
        this.table.add(cells);
    }

    public boolean containsDuplicatePrimaryKey(Cell cell) {
            
    }

    @Override
    public void print() {
        for (int i = 0; i < table.size(); i++) {
            System.out.print(" | ");
            for (int j = 0; j < table.get(0).size(); j++) {
                System.out.print(attrs.get(j));
                System.out.print(table.get(i).get(j).toString());
                System.out.print(" | ");
            }
            System.out.println();

        }
    }
    
}
