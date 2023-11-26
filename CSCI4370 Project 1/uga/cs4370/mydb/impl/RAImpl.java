package uga.cs4370.mydb.impl;

import java.util.ArrayList;
import java.util.List;
import uga.cs4370.mydb.*;

public class RAImpl implements uga.cs4370.mydb.RA {
    // add support for foreign and primary keys to be saved when you select and project
    RelationBuilderImpl builder;
    
    public RAImpl() {
        this.builder = new RelationBuilderImpl();
    }
    /**
     * Performs the select operation on the relation rel
     * by applying the predicate p.
     * 
     * @return The resulting relation after applying the select operation.
     */
    public Relation select(Relation rel, Predicate p) {

        List <List<Cell>> copy = rel.getRows();
        Relation result = builder.newRelation(rel.getName(), rel.getAttrs(), rel.getTypes());
        List<String> foreignKeys = new ArrayList<String>(((RelationImpl)rel).getForeignKeys().values());
        ((RelationImpl)result).addForeignKeys(foreignKeys);
        List<String> primaryKeys = new ArrayList<String>(((RelationImpl)rel).getPrimaryKeys().values());
        ((RelationImpl)result).addPrimaryKeys(primaryKeys);

        for (List <Cell> row : copy) {
            if (p.check(row)) {
                List<Cell> inserted = new ArrayList<Cell>();
                inserted.addAll(row);
                result.insert(inserted);
            }
        }
        return result;
    }

    /**
     * Performs the project operation on the relation rel
     * given the attributes list attrs.
     * 
     * @return The resulting relation after applying the project operation.
     * 
     * @throws IllegalArgumentException If attributes in attrs are not 
     * present in rel.
     */
    public Relation project(Relation rel, List<String> attrs) {
        List <Type> types = new ArrayList<>();
        List<String> foreignKeys = new ArrayList<String>(((RelationImpl)rel).getForeignKeys().values());
        List<String> newForeignKeys = new ArrayList<String>();
        List<String> primaryKeys = new ArrayList<String>(((RelationImpl)rel).getPrimaryKeys().values());
        List<String> newPrimaryKeys = new ArrayList<String>();
        for (String attr : attrs) {
            try {
                int index = rel.getAttrIndex(attr);
                types.add(rel.getTypes().get(index));
            }
            catch(IllegalArgumentException i) {
                throw new IllegalArgumentException("Attribute does not exist");
            }
        }
        for (String foreignKey : foreignKeys) {
            if (attrs.contains(foreignKey)) {
                newForeignKeys.add(foreignKey);
            }
        }
        for (String primaryKey : primaryKeys) {
            if (attrs.contains(primaryKey)) {
                newPrimaryKeys.add(primaryKey);
            }
        }
        //making new relation
        Relation newRel = builder.newRelation(rel.getName(), attrs, types);
        ((RelationImpl)newRel).addForeignKeys(newForeignKeys);
        ((RelationImpl)newRel).addPrimaryKeys(newPrimaryKeys);

        //adding the columns/attribute values to the newRel
        for (int i = 0; i < rel.getSize(); i++) {
            List <Cell> row = new ArrayList<Cell>();
            for (String attr : attrs) {
                row.add(rel.getRows().get(i).get(rel.getAttrIndex(attr)));
            }
            newRel.insert(row);
        }
        return newRel;
    }

    public Relation distinct(Relation rel, List<String> attrs) {
        Relation newRel = builder.newRelation("Distinct " + rel.getName(), rel.getAttrs(), rel.getTypes());
        List<String> foreignKeys = new ArrayList<String>(((RelationImpl)rel).getForeignKeys().values());
        List<String> primaryKeys = new ArrayList<String>(((RelationImpl)rel).getPrimaryKeys().values());
        ((RelationImpl)newRel).addForeignKeys(foreignKeys);
        ((RelationImpl)newRel).addPrimaryKeys(primaryKeys);
        for (List<Cell> row: rel.getRows()) {
            boolean same = false;
            for (int i = 0; i < attrs.size(); i++) {
                for (int j = 0; j < newRel.getSize(); j++) {
                    if (row.get(i).toString().equals(newRel.getRows().get(j).get(i).toString())) {
                        same = true;
                    }
                }
            }
            if (!same) {
                List<Cell> newRow = new ArrayList<Cell>();
                newRow.addAll(row);
                newRel.insert(newRow);
            }
        }
        return newRel;
    }

    /**
     * Performs the union operation on the relations rel1 and rel2.
     * 
     * @return The resulting relation after applying the union operation.
     * 
     * @throws IllegalArgumentException If rel1 and rel2 are not compatible.
     */
    public Relation union(Relation rel1, Relation rel2){

        if (rel1.getAttrs().size() != rel2.getAttrs().size()) {
            throw new IllegalArgumentException("Relations are not compatible for union");
        }

        List <String> attrs1 = rel1.getAttrs();

        List <Type> a1Types = rel1.getTypes();
        List <Type> a2Types = rel2.getTypes();

        if (!a1Types.equals(a2Types)) {
            throw new IllegalArgumentException("Relations are not compatible for union");
        }

        List <String> unionAttrs = new ArrayList<>();
        unionAttrs.addAll(attrs1);
        List <Type> unionTypes = new ArrayList<>();
        unionTypes.addAll(a1Types);

        Relation newRel = builder.newRelation("Union of " + rel1.getName() + " and "+ rel2.getName(), unionAttrs, unionTypes);
        List<String> foreignKeys = new ArrayList<String>(((RelationImpl)rel1).getForeignKeys().values());
        ((RelationImpl)newRel).addForeignKeys(foreignKeys);
        List<String> primaryKeys = new ArrayList<String>(((RelationImpl)rel1).getPrimaryKeys().values());
        ((RelationImpl)newRel).addPrimaryKeys(primaryKeys);

        //Building the new union relation
        int counter = 0;
        for (int i = 0; i < (rel1.getSize() + rel2.getSize()); i++) { //building new cells array for every row
            List <Cell> cells = new ArrayList<>();
            if (i < rel1.getSize()) {
                cells.addAll(rel1.getRows().get(i));
            } else {
                cells.addAll(rel2.getRows().get(counter));
                counter++;
            }
            newRel.insert(cells);
        }
        return newRel;
    }

    /**
     * Performs the set difference operation on the relations rel1 and rel2.
     * 
     * @return The resulting relation after applying the set difference operation.
     * 
     * @throws IllegalArgumentException If rel1 and rel2 are not compatible.
     */
    public Relation diff(Relation rel1, Relation rel2) {

        if (rel1.getAttrs().size() != rel2.getAttrs().size()) {
            throw new IllegalArgumentException("Relations are not compatible for union");
        }

        List <String> attrs1 = rel1.getAttrs();

        List <Type> a1Types = rel1.getTypes();
        List <Type> a2Types = rel2.getTypes();

        if (!a1Types.equals(a2Types)) {
            throw new IllegalArgumentException("Relations are not compatible for union");
        }

        List <String> unionAttrs = new ArrayList<>();
        unionAttrs.addAll(attrs1);
        List <Type> unionTypes = new ArrayList<>();
        unionTypes.addAll(a1Types);

        Relation newRel = builder.newRelation("Difference of " + rel1.getName() + " and " + rel2.getName(),
                                             unionAttrs, unionTypes);
        List<String> foreignKeys = new ArrayList<String>(((RelationImpl)rel1).getForeignKeys().values());
        ((RelationImpl)newRel).addForeignKeys(foreignKeys);
        List<String> primaryKeys = new ArrayList<String>(((RelationImpl)rel1).getPrimaryKeys().values());
        ((RelationImpl)newRel).addPrimaryKeys(primaryKeys);

        boolean add = true;
        for (int i = 0; i < rel1.getSize(); i++) {
            add = true;
            for (int j = 0; j < rel2.getSize(); j++) {
                if (rel1.getRows().get(i).equals(rel2.getRows().get(j))) {
                    add = false;
                }
            }
            if (add) {
                List <Cell> cells = new ArrayList<>();
                cells.addAll(rel1.getRows().get(i));
                newRel.insert(cells);
            }
        }
        return newRel;
    }

    /**
     * Renames the attributes in origAttr of relation rel to corresponding 
     * names in renamedAttr.
     * 
     * @return The resulting relation after renaming the attributes.
     * 
     * @throws IllegalArgumentException If attributes in origAttr are not present in 
     * rel or origAttr and renamedAttr do not have matching argument counts.
     */
    public Relation rename(Relation rel, List<String> origAttr, List<String> renamedAttr) {
        if (origAttr.size() != renamedAttr.size()) {
            throw new IllegalArgumentException("Uneven attribute counts");
        }
        if (!rel.getAttrs().containsAll(origAttr)) {
            throw new IllegalArgumentException("Attributes dont match or not present in the relation");
        }
        List<String> newAttrs = new ArrayList<String>();
        for (int i = 0; i < rel.getAttrs().size(); i++) {
            if (origAttr.contains(rel.getAttrs().get(i))) {
                newAttrs.add(renamedAttr.get(origAttr.indexOf(rel.getAttrs().get(i))));
            } else {
                newAttrs.add(rel.getAttrs().get(i));
            }
        }
        Relation renamed = builder.newRelation(rel.getName(), newAttrs, rel.getTypes());
        List<String> foreignKeys = new ArrayList<String>(((RelationImpl)rel).getForeignKeys().values());
        ((RelationImpl)renamed).addForeignKeys(foreignKeys);
        List<String> primaryKeys = new ArrayList<String>(((RelationImpl)rel).getPrimaryKeys().values());
        ((RelationImpl)renamed).addPrimaryKeys(primaryKeys);

        List <List<Cell>> table = rel.getRows();

        for (List <Cell> row : table) {
            List<Cell> cells = new ArrayList<Cell>();
            cells.addAll(row);
            renamed.insert(cells);
        }

        return renamed;
    }

    /**
     * Performs cartisian product on relations rel1 and rel2.
     * 
     * @return The resulting relation after applying cartisian product.
     * 
     * @throws IllegalArgumentException if rel1 and rel2 have common attibutes.
     */
    public Relation cartesianProduct(Relation rel1, Relation rel2) {
        //Creating the new relation
        List <String> attrs1 = rel1.getAttrs();
        List <String> attrs2 = rel2.getAttrs();
        for (String attr : attrs2) {
            if (attrs1.contains(attr)) {
                throw new IllegalArgumentException("Common attributes present");
            }
        }

        List<String> combinedAttrs = new ArrayList<String>();
        combinedAttrs.addAll(attrs1);
        combinedAttrs.addAll(attrs2);

        List <Type> combinedTypes = new ArrayList<Type>();
        combinedTypes.addAll(rel1.getTypes());
        combinedTypes.addAll(rel2.getTypes());

        Relation newRel = builder.newRelation("Cartesian Product of " + rel1.getName() + " and " + rel2.getName(), combinedAttrs, combinedTypes);
        
        //Cartesian product with double for each loop
        List <List<Cell>> table1 = rel1.getRows();
        List <List<Cell>> table2 = rel2.getRows();
        for (List<Cell> row1 : table1) {
            for (List<Cell> row2 : table2) {
                List <Cell> combined = new ArrayList<>();
                combined.addAll(row1);
                combined.addAll(row2);
                newRel.insert(combined);
            }
        }
        return newRel;
    }

    /**
     * Peforms natural join on relations rel1 and rel2.
     * 
     * @return The resulting relation after applying natural join.
     */
    public Relation join(Relation rel1, Relation rel2) {
        //Tables
        List <List<Cell>> table1 = rel1.getRows();
        List <List<Cell>> table2 = rel2.getRows();

        //Atrributes
        List <String> attrs1 = rel1.getAttrs();
        List <String> attrs2 = rel2.getAttrs();

        //Common attribute indexes in rel1 and types
        List <String> joinedAttrs = new ArrayList<>();
        List <Type> joinedTypes = new ArrayList<>();

        int counter = 0;
        for (int i = 0; i < attrs1.size() + attrs2.size(); i++) {
            if (i < attrs1.size()) {
                joinedAttrs.add(attrs1.get(i));
                joinedTypes.add(rel1.getTypes().get(i));
            }
            else if (i >= attrs1.size() && !joinedAttrs.contains(attrs2.get(counter))) {
                joinedAttrs.add(attrs2.get(counter));
                joinedTypes.add(rel2.getTypes().get(counter));
            }
            if (i >= attrs1.size()) {
                counter++;
            }
        }

        List <String> indices = new ArrayList<>();
        for (String attr : attrs1) {
            try {
                if (rel2.hasAttr(attr)) {
                    indices.add(attr);
                }
            } catch (IllegalArgumentException e) {}
        }

        // New Relation
        Relation newRel = builder.newRelation("Natural join of " + rel1.getName() + " and " + rel2.getName(), joinedAttrs, joinedTypes );
        
        // get all attributes for which there is an index
        List<String> hasIndex = new ArrayList<>();
        for (String attribute : indices) {
            HashTable index = rel2.getIndex(attribute);
            if (index != null) {
                hasIndex.add(attribute);
            }
        }
        indices.removeAll(hasIndex); // remove the attributes for which there are indices, as do not need regular comparison for those

        for (List<Cell> row1 : table1) { // iterating through every row in the first table
            List<List<Cell>> vetted = new ArrayList<List<Cell>>(); // rows which pass the comparsion on attributes that are indexed
            boolean same = true;
            
            List<Integer> rowIndices = new ArrayList<>(); // indices to rows that pass

            for (String attribute : hasIndex) {
                HashTable index = rel2.getIndex(attribute);
                rowIndices = index.search(row1.get(rel1.getAttrIndex(attribute)).toString()); // getting the indices for all rows

                if (rowIndices.isEmpty()) { // there is no existing row that has a corresponding value in table 1
                    same = false;
                    break;
                } 
            }

            if (hasIndex.isEmpty()) { // if there were no indices, have to compare the entire table
                vetted = table2;
            } else if (!rowIndices.isEmpty() && same == true) { 
                for (Integer i : rowIndices) {
                    vetted.add(table2.get(i));
                } // adding all the rows from table 2 using the indices previous gathered from searching the index
            } 
            for (List <Cell> row2 : vetted) {
                same = true;
                for (String attribute : indices) { // need to check any unindexed attributes
                    if (!(row1.get(rel1.getAttrIndex(attribute)).toString().equals(row2.get(rel2.getAttrIndex(attribute)).toString()))) {
                        same = false;
                    } 
                }

                if (same) { 
                    //Found the row2 that matches with row1 with common attributes
                    List <Cell> commonRow = new ArrayList<>(); // creating the row to be added
                    int start = 0;
                    for (int i = 0; i < row1.size() + row2.size(); i++) {
                        if (i < row1.size()) {
                            commonRow.add(row1.get(i));
                        }
                        else if (i >= row1.size() && !rel1.getAttrs().contains(rel2.getAttrs().get(start))) {
                            commonRow.add(row2.get(start));
                        }
                        if (i >= row1.size()) {
                            start++;
                        }
                    }
                    newRel.insert(commonRow);
                }
            } 
        }
        return newRel; // joined relation
    }

    public Relation join(Relation rel1, Relation rel2, Predicate p) {
        List<String> combinedAttrs = new ArrayList<String>();
        List<Type> combinedTypes = new ArrayList<Type>();
        int counter = 0;
        int sameIndex = -1; 
        for (int i = 0; i < rel1.getAttrs().size() + rel2.getAttrs().size(); i++) {
            if (i < rel1.getAttrs().size()) {
                combinedAttrs.add(rel1.getAttrs().get(i));
                combinedTypes.add(rel1.getTypes().get(i));
            } else if (i >= rel1.getAttrs().size() && !combinedAttrs.contains(rel2.getAttrs().get(counter))) {
                combinedAttrs.add(rel2.getAttrs().get(counter));
                combinedTypes.add(rel2.getTypes().get(counter));
            } else if (combinedAttrs.contains(rel2.getAttrs().get(counter))) {
                sameIndex = rel1.getAttrs().size() + counter;
            }
            if (i >= rel1.getAttrs().size()) {
                counter++;
            }
        }
        Relation joinedRel = builder.newRelation("Theta join of " + rel1.getName() + " and " + rel2.getName(), combinedAttrs, combinedTypes);
        //if (rel2.getIndex(null)
        List<Cell> checkCombined = new ArrayList<Cell>();
        for (List<Cell> row1 : rel1.getRows()) {
            for (List<Cell> row2 : rel2.getRows()) {
                checkCombined.addAll(row1);
                checkCombined.addAll(row2);
                if (p.check(checkCombined)) {
                    List<Cell> confirmed = new ArrayList<Cell>();
                    for (int i = 0; i < checkCombined.size(); i++) {
                        if (!(i == sameIndex)) {
                            confirmed.add(checkCombined.get(i));
                        }
                    }
                    joinedRel.insert(confirmed);
                }
                checkCombined.clear();
            }
        }
        return joinedRel;
    }
}