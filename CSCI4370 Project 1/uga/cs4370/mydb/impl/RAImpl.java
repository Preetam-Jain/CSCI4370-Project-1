package uga.cs4370.mydb.impl;

import java.util.ArrayList;
import java.util.List;
import uga.cs4370.mydb.*;
import uga.cs4370.mydb.Relation;

public class RAImpl implements uga.cs4370.mydb.RA {

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
        List <List<Cell>> copy = new ArrayList<>();
        copy = rel.getRows();
        Relation result = builder.newRelation(rel.getName(), rel.getAttrs(), rel.getTypes());
        for (List <Cell> row : copy) {
            if (p.check(row)) {
                result.insert(row);
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
        //getting types
        List <Type> t = new ArrayList<>();
        for (String attr : attrs) {
            try {
                int index = rel.getAttrIndex(attr);
                t.add(rel.getTypes().get(index));
            }
            catch(IllegalArgumentException i) {
                throw new IllegalArgumentException("Attribute does not exist");
            }
        }
        //making new relation
        Relation newRel = new RelationImpl(rel.getName(), attrs, t);

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

        Relation newRel = new RelationImpl("Union of " + rel1.getName() + " and "+ rel2.getName(), unionAttrs, unionTypes);
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
        //Assume that primary keys, or IDs, are the first value
        List <List<Cell>> table1 = rel1.getRows();
        List <List<Cell>> table2 = rel2.getRows();

        List <String> attrs1 = rel1.getAttrs();
        List <String> attrs2 = rel2.getAttrs();
        List <String> unionAttr = new ArrayList<>();

        List <Type> types = new ArrayList<>();
        List <Type> a1Types = rel1.getTypes();
        List <Type> a2Types = rel2.getTypes();
        
        for (int i = 0; i < (attrs1.size() + attrs2.size()); i++) {
            if (i < attrs1.size()) {
                unionAttr.add(attrs1.get(i));
                types.add(a1Types.get(rel1.getAttrIndex(attrs1.get(i))));
            }
            if (i >= attrs1.size() && !unionAttr.contains(attrs2.get(i%attrs1.size()))) {
                unionAttr.add(attrs2.get(i%attrs1.size()));
                types.add(a2Types.get(rel2.getAttrIndex(attrs2.get(i%attrs2.size()))));
            }
        }
        Relation newRel = new RelationImpl("Difference of " + rel1 + " and " + rel2, unionAttr, types);

        for (int i = 0; i < rel1.getSize(); i++) {
            for (int j = 0; j < rel2.getSize(); j++) {
                List <Cell> row = table1.get(i);
                String id = row.get(0).toString();
                if (!table2.get(j).get(0).toString().equals(id)) {
                    newRel.insert(row);
                }
            }
        }
        for (int i = 0; i < rel2.getSize(); i++) {
            for (int j = 0; j < rel1.getSize(); j++) {
                List <Cell> row = table2.get(i);
                String id = row.get(0).toString();
                if (!table1.get(j).get(0).toString().equals(id)) {
                    newRel.insert(row);
                }
            }
        }
        if (newRel.getSize()==0) {
            throw new IllegalArgumentException("Relations are incompatible");
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
        if (!rel.getAttrs().equals(origAttr)) {
            throw new IllegalArgumentException("Attributes dont match or not present in the relation");
        }
        Relation renamed = new RelationImpl(rel.getName(), renamedAttr, rel.getTypes());
        List <List<Cell>> table = rel.getRows();
        for (List <Cell> row : table) {
            renamed.insert(row);
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
        attrs1.addAll(attrs2);

        List <Type> types = rel1.getTypes();
        types.addAll(rel2.getTypes());

        Relation newRel = new RelationImpl("Cartesion Product of " + rel1.getName() + " and " + rel2.getName(), attrs1, types);;
        
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
        List <String> attrs = new ArrayList<>();
        List <Type> types = new ArrayList<>();
        for (int i = 0; i < attrs1.size() + attrs2.size(); i++) {
            if (i < attrs1.size()) {
                attrs.add(attrs1.get(i));
                types.add(rel1.getTypes().get(i));
            }
            else if (i >= attrs1.size() && !attrs.contains(attrs2.get(i%attrs.size()))) {
                attrs.add(attrs2.get(i%attrs.size()));
                types.add(rel1.getTypes().get(i%attrs.size()));
            }
         }
        List <String> indices = new ArrayList<>();
        for (String attr : attrs2) {
            if (rel1.hasAttr(attr)) {
                indices.add(attrs2.get(rel1.getAttrIndex(attr)));
            }
        }
        // New Relation
        Relation newRel = new RelationImpl("Natural join of " + rel1.getName() + " and " + rel2.getName(),attrs, types );
        
        //Compare common attribute values and combine
        boolean same = false;
        for (List <Cell> row1 : table1) {
            for (List <Cell> row2 : table2) {
                for (String attribute : indices) {
                    if (row1.get(rel1.getAttrIndex(attribute)).toString().equals(row2.get(rel2.getAttrIndex(attribute)).toString())) {
                        same = true;
                    }
                    same = false;
                }
                if (same) {
                    //Found the row2 that matches with row1 with common attributes
                    List <Cell> commonRow = new ArrayList<>();
                    for (int i = 0; i < row1.size() + row2.size(); i++) {
                        if (i < row1.size()) {
                            commonRow.add(row1.get(i));
                        }
                        else if (i >= row1.size() && !commonRow.contains(row2.get(i%row1.size()))) {
                            commonRow.add(row2.get(i%row1.size()));
                        }
                    }
                }
            }
        }
        return newRel;
    }
    
    public Relation join(Relation rel1, Relation rel2, Predicate p) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'join'");
    }
}