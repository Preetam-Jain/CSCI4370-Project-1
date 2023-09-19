package uga.cs4370.mydb.impl;

import java.util.List;
import uga.cs4370.mydb.Cell;
import uga.cs4370.mydb.Type;

public class Relation implements uga.cs4370.mydb.Relation {

    public String getName() {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @Override
    public int getSize() {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @Override
    public List<List<Cell>> getRows() {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @Override
    public List<Type> getTypes() {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @Override
    public List<String> getAttrs() {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @Override
    public boolean hasAttr(String attr) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @Override
    public int getAttrIndex(String attr) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @Override
    public void insert(Cell... cells) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @Override
    public void insert(List<Cell> cells) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @Override
    public void print() {
        throw new UnsupportedOperationException("Method not implemented yet");
    }
    
}
