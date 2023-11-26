package uga.cs4370.mydb.impl;

public class Node {

    private int index; // index in the table
    private Long key; // hashed key

    public Node(int index, Long key) {
        this.index = index;
        this.key = key;
    }

    public int getIndex() {
        return this.index;
    }

    public Long getKey() {
        return this.key;
    }
    
}
