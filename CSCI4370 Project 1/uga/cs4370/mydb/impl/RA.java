package uga.cs4370.mydb.impl;

import java.util.List;

import javax.management.relation.RelationException;

import java.util.ArrayList;
import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.Predicate;
import uga.cs4370.mydb.Cell;

public class RA implements uga.cs4370.mydb.RA{
    @Override
    public Relation select(Relation rel, Predicate p){
        List <List<Cell>> copy = new ArrayList<>();
        copy = rel.getRows();
        Relation result = new Relation(rel.getName(), rel.getAttrs(), rel.getTypes());
        for(List<Cell> row : copy){
            if(p.check(row)){
                result.insert(row);
            }
        }
        return result;

    }
    @Override
    public Relation project(Relation rel, List<String> attrs){
        for(String attr : attrs){
            if(!rel.hasAttr(attr)){
                throw new IllegalArgumentException("Attribute " + attr + "not found in the relation.");
            }
        }
        Relation result = new Relation(attrs, rel.getTypes());
        for(List<Cell> row : rel.getRows()){
            List<Cell> projectedRow = new ArrayList<>();
            for(String attr : attrs){
                int attrIndex = rel.getAttrIndex(attr);
                projectedRow.add(row.get(attrIndex));
            }
            result.insert(projectedRow);
        }
        return result;
    }
    @Override
    public Relation diff(Relation rel1, Relation rel2){
        if(!rel1.getAttrs().equals(rel2.getAttrs())){
            throw new IllegalArgumentException("Relations are not compatible for a set difference.");
        }
        Relation result = new Relation(rel1.getAttrs(), rel1.getTypes());
        for(List<Cell> row1 : rel1.getRows()){
            boolean inRel2 = false;
            
            for(List<Cell> row2 : rel2.getRows()){
                if(row1.equals(row2)){
                    inRel2 = true;
                    break;
                }
            }
            if(!inRel2){
                result.insert(row1);
            }
        }
        return result;
    }
    @Override
    public Relation union(Relation rel1, Relation rel2){
        if(!rel1.getAttrs().equals(rel2.getAttrs()) || !rel1.getTypes().equals(rel2.getTypes())){
            throw new IllegalArgumentException("Relations are not compatible for a union.");
        }
        Relation result = new Relation(rel1.getAttrs(), rel1.getTypes());
        for(List<Cell> row : rel1.getRows()){
            result.insert(row);
        }
        for(List<Cell> row : rel2.getRows()){
            if(!result.getRows().contains(row)){
                result.insert(row);
            }
        }
        return result;
    }
    @Override
    public Relation rename(Relation rel, List<String> origAttr, List<String> renamedAttr){
        if(!rel.getAttrs().containsAll(origAttr) || origAttr.size() != renamedAttr.size()){
            throw new IllegalArgumentException("Invalid attribute rename.");
        }
        Relation result = new Relation(rel.getName(), rel.getAttrs(), rel.getTypes());
        for(List<Cell> row : rel.getRows()){
            List<Cell> renamedRow = new ArrayList<>();
            for(int i = 0; i < rel.getAttrs().size(); i++){
                String orig = rel.getAttrs().get(i);
                String renamed = origAttr.contains(orig) ? renamedAttr.get(origAttr.indexOf(orig)) : orig;
                renamedRow.add(row.get(i));
            }
            result.insert(renamedRow);
        }
        return result;
    }
}
