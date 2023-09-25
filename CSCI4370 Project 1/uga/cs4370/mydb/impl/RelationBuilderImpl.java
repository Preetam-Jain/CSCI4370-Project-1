package uga.cs4370.mydb.impl;

import java.util.List;
import uga.cs4370.mydb.Type;
import uga.cs4370.mydb.Relation;

public class RelationBuilderImpl implements uga.cs4370.mydb.RelationBuilder {
    public Relation newRelation(String name, List<String> attrs, List<Type> types) {
        Relation relation = new RelationImpl(name, attrs, types);
        return relation;
    }
}
