package uga.cs4370.mydb.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uga.cs4370.mydb.Cell;
import uga.cs4370.mydb.Type;
import uga.cs4370.mydb.Predicate;
import uga.cs4370.mydb.RA;


public class Main {
    public static void main(String[] args) {
        Database mydb = new Database("Preetam's Database");

        /* Creating Students Table */
        ArrayList<String> attributesOne = new ArrayList<String>();
        ArrayList<Type> typesOne = new ArrayList<Type>();
        ArrayList<String> primaryKeysOne = new ArrayList<String>();
        ArrayList<String> foreignKeysOne = new ArrayList<String>();

        String studentsName = "Students";
        attributesOne.addAll(Arrays.asList("StudentID", "FName", "LName", "DoB", "Major"));
        typesOne.addAll(Arrays.asList(Type.INTEGER, Type.STRING, Type.STRING, Type.STRING, Type.STRING));
        primaryKeysOne.add("StudentID");
 
        /* Adding Students */
        mydb.addRelation(studentsName, attributesOne, typesOne, primaryKeysOne, foreignKeysOne);

        List<Cell> rowOne = new ArrayList<Cell>();
        rowOne.add(new Cell(1234));
        rowOne.add(new Cell("Deven"));
        rowOne.add(new Cell("Allen"));
        rowOne.add(new Cell("2002-03-05"));
        rowOne.add(new Cell("Computer Science"));

        mydb.getRelations().get(0).insert(rowOne);

        List<Cell> rowTwo = new ArrayList<Cell>();
        rowTwo.add(new Cell(1111));
        rowTwo.add(new Cell("John"));
        rowTwo.add(new Cell("Doe"));
        rowTwo.add(new Cell("1999-05-22"));
        rowTwo.add(new Cell("Computer Science"));
 
        mydb.getRelations().get(0).insert(rowTwo);

        /* Creating Courses Table */
        ArrayList<String> attributesTwo = new ArrayList<String>();
        ArrayList<Type> typesTwo = new ArrayList<Type>();
        ArrayList<String> primaryKeysTwo = new ArrayList<String>();
        ArrayList<String> foreignKeysTwo = new ArrayList<String>();

        String coursesName = "Courses";
        attributesTwo.addAll(Arrays.asList("CourseID", "CName", "Credits"));
        typesTwo.addAll(Arrays.asList(Type.INTEGER, Type.STRING, Type.INTEGER));
        primaryKeysTwo.add("CourseID");

        mydb.addRelation(coursesName, attributesTwo, typesTwo, primaryKeysTwo, foreignKeysTwo);


        ArrayList<String> attributesThree = new ArrayList<String>();
        ArrayList<Type> typesThree = new ArrayList<Type>();
        ArrayList<String> primaryKeysThree = new ArrayList<String>();
        ArrayList<String> foreignKeysThree = new ArrayList<String>();

        String className = "Classes";
        attributesThree.addAll(Arrays.asList("CourseID", "CName", "Credits"));
        typesThree.addAll(Arrays.asList(Type.INTEGER, Type.STRING, Type.INTEGER));
        primaryKeysThree.add("CourseID");
        mydb.addRelation(className, attributesThree, typesThree, primaryKeysThree, foreignKeysThree);

        
        /* Adding Courses */
        List<Cell> rowThree = new ArrayList<Cell>();
        rowThree.add(new Cell(101));
        rowThree.add(new Cell("Introduction to Programming"));
        rowThree.add(new Cell(3));

        mydb.getRelations().get(1).insert(rowThree);

        List<Cell> rowFour = new ArrayList<Cell>();
        rowFour.add(new Cell(102));
        rowFour.add(new Cell("Database Management"));
        rowFour.add(new Cell(4));
 
        mydb.getRelations().get(1).insert(rowFour);

        List<Cell> rowFive = new ArrayList<Cell>();
        rowFive.add(new Cell(103));
        rowFive.add(new Cell("Software Engineering"));
        rowFive.add(new Cell(3));

        mydb.getRelationByName("Classes").insert(rowFive);

        List<Cell> rowSix = new ArrayList<Cell>();
        rowSix.add(new Cell(104));
        rowSix.add(new Cell("Data Structures"));
        rowSix.add(new Cell(4));
 
        mydb.getRelationByName("Classes").insert(rowSix);

        Predicate p0 = new PredicateImpl("StudentID=\"1111\"", mydb.getRelationByName("Students").getAttrs());
        Predicate p1 = new PredicateImpl("StudentID=\"1234\"", mydb.getRelationByName("Students").getAttrs());
        RA relationalAlgebra = new RAImpl();

        List<String> projectedAttributes = new ArrayList<String>();
        projectedAttributes.addAll(Arrays.asList("FName", "LName", "DoB"));


        List<String> projectedAttributes1 = new ArrayList<String>();
        projectedAttributes1.addAll(Arrays.asList("CourseID", "CName"));

        //mydb.getRelationByName("Students").print();
        //mydb.getRelationByName("Courses").print();
        //mydb.getRelationByName("Classes").print();

        //relationalAlgebra.select(mydb.getRelationByName("Students"), p0).print();
        //relationalAlgebra.select(mydb.getRelationByName("Students"), p1).print();

        //relationalAlgebra.project(mydb.getRelationByName("Students"), projectedAttributes).print();

        //relationalAlgebra.project(mydb.getRelationByName("Courses"), projectedAttributes1).print();

        relationalAlgebra.union(mydb.getRelationByName("Courses"), mydb.getRelationByName("Classes")).print();
    }
}
