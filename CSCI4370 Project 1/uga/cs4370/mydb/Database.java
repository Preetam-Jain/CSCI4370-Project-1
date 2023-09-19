package uga.cs4370.mydb;

import java.util.List;
import java.util.ArrayList;

public class Database {
    
    List<Relation> relations;
    String name;

    public Database(String name) {
        this.name = name;
        this.relations = new ArrayList<Relation>();
    }
    
}
