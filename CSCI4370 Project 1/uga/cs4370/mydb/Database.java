package uga.cs4370.mydb;

import java.util.List;
import java.util.ArrayList;

public class Database {
    
    private List<Relation> relations;
    private String name;

    public Database(String name) {
        this.name = name;
        this.relations = new ArrayList<Relation>();
    }

    public List<Relation> getRelations() {
        return this.relations;
    }

    public String getName() {
        return name;
    }

}
