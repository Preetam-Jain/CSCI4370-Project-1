package uga.cs4370.mydb.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import uga.cs4370.mydb.Cell;
import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.Type;

public class RelationImpl implements Relation {

    private String name; // table name
    private int size = 0; // ammount of rows in the table
    private List<List<Cell>> table; // contains the structure for the table 
    private List<String> attrs; // the names of the attributes of the table
    private List<Type> types; // the names of the types of each column
    private HashSet<String> unique;
    private HashMap<Integer, String> primaryKeys = new HashMap<>(); // stores the indexes of the primary keys in a hashmap,
    private HashMap<Integer, String> foreignKeys = new HashMap<>();

    /*
    * Constructor for a table/relation, takes in name, all of the names of the attributes,
    * and the names of the types of each column. The parameters receive the arguments from
    * the main class. However, the overall storage is only created within the constructor,
    * not the driver.
    */
    public RelationImpl(String name, List<String> attrs, List<Type> types) {
        this.name = name;
        this.attrs = attrs;
        this.unique = new HashSet<String>(attrs);
        if (this.unique.size() < attrs.size()) {
            throw new IllegalArgumentException("Cannot have duplicate attributes in the same relation");
        }
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

    public Type getType(Cell cell) {
        try {
            cell.getAsInt();
            return Type.INTEGER;
        } catch (RuntimeException ignored) {}
            
        try {
            cell.getAsDouble();
            return Type.DOUBLE;
        } catch (RuntimeException ignored) {}
        
        return Type.STRING;
    }

    public void addForeignKeys(List<String> attr) {
        for (int i = 0; i < attr.size(); i++) {
            if (!attr.isEmpty()) {
                String attribute = attr.get(i);
                foreignKeys.put(attrs.indexOf(attribute), attribute);
            }
        }
    }

    public HashMap<Integer, String> getForeignKeys() {
        return this.foreignKeys;
    }

    public void addPrimaryKeys(List<String> attr) {
         for (int i = 0; i < attr.size(); i++) {
            if (!attr.isEmpty()) {
                String attribute = attr.get(i);
                primaryKeys.put(attrs.indexOf(attribute), attribute);
            }
        }
    }

    public HashMap<Integer, String> getPrimaryKeys() {
        return this.primaryKeys;
    }

    @Override
    public void insert(Cell... cells) {
        int index = 0;
        ArrayList<Cell> row = new ArrayList<Cell>();
        for (Cell cell : cells) {
            if (types.get(index) == getType(cell)) {
                row.add(cell);
            } else {
                throw new IllegalArgumentException("Cell does not correspond to attribute type: " + getType(cell));
            }
        }
        this.table.add(row);
        this.size++;
    }

    @Override
    public void insert(List<Cell> cells) {
        for (int i = 0; i < cells.size(); i++) {
            if (types.get(i) != getType(cells.get(i))) {
                throw new IllegalArgumentException("Cell does not correspond to attribute type: " + getType(cells.get(i)));
            } else if (primaryKeys.containsKey(i) && containsDuplicatePrimaryKey(cells.get(i), i)) {
                throw new IllegalArgumentException("Cell contains a duplicate primary key: " + cells.get(i));
            }
        }
        this.table.add(cells);
        this.size++;
    }

    public boolean containsDuplicatePrimaryKey(Cell cell, int index) {
        for (int i = 0; i < table.size(); i++) {
            if (table.get(index).get(i).equals(cell)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void print() {
        System.out.println(this.name);
        System.out.print("| ");
        for (int i = 0; i < attrs.size(); i++) {
            System.out.print(attrs.get(i));
            int len = findLengthDifference(i) - attrs.get(i).length();
            if (len > 0) {
                for (int j = 0; j < len; j++) {
                    System.out.print(" ");
                }
            }
            System.out.print(" | ");
        }
        System.out.println();
        for (int i = 0; i < table.size(); i++) {
            System.out.print("| ");
            for (int j = 0; j < table.get(0).size(); j++) {
                System.out.print(table.get(i).get(j));
                String convert = table.get(i).get(j).toString();
                int len = findLengthDifference(j) - convert.length();
                if (len < attrs.get(j).length() - convert.length()) {
                    len = attrs.get(j).length() - convert.length();
                }
                if (len > 0) {
                    for (int k = 0; k < len; k++) {
                        System.out.print(" ");
                    }
                }
                System.out.print(" | ");
            }
        System.out.println();
        }
    }

    public int findLengthDifference(int index) {
        int max = 0;
        int temp = 0;
        for (int i = 0; i < table.size(); i++) {
            try {
                temp = table.get(i).get(index).toString().length();
            } catch (IndexOutOfBoundsException e) {
                throw new IndexOutOfBoundsException();
            }
            if (max < temp) {
                max = temp;
            }
        }
        return max;
    }
    
}
