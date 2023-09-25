package uga.cs4370.mydb.impl;

import java.util.List;

import uga.cs4370.mydb.Type;
import uga.cs4370.mydb.Relation;

import java.util.ArrayList;

public class Database {
    private List<Relation> relations;
    private String name;
    private RelationBuilderImpl builder = new RelationBuilderImpl();

    public Database(String name) {
        this.name = name;
        this.relations = new ArrayList<Relation>();
    }

    public void addRelation(String name,  List<String> attrs, List<Type> types, List<String> primaryKeys, List<String> foreignKeys) {
        Relation toBeAdded = builder.newRelation(name, attrs, types);
        ((RelationImpl)toBeAdded).addPrimaryKeys(primaryKeys);
        ((RelationImpl)toBeAdded).addForeignKeys(foreignKeys);
        this.relations.add(toBeAdded);
    }

    public List<Relation> getRelations() {
        return this.relations;
    }

    public Relation getRelationByName(String name) {
        for (int i = 0; i < relations.size(); i++) {
            if (relations.get(i).getName().equals(name)) {
                return relations.get(i);
            }
        }
        throw new IllegalArgumentException("No relation exists with given name");
    }

    public String getName() {
        return name;
    }
}
