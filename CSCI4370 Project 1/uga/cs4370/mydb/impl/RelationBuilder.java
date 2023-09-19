package uga.cs4370.mydb.impl;

import java.util.List;
import uga.cs4370.mydb.Type;

public class RelationBuilder {
    public Relation newRelation(String name, List<String> attrs, List<Type> types) {
        Relation relation = new Relation(name, attrs, types);
        return relation;
    }
}
