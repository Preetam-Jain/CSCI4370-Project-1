package uga.cs4370.mydb.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uga.cs4370.mydb.Cell;
import uga.cs4370.mydb.Type;
import uga.cs4370.mydb.Predicate;
import uga.cs4370.mydb.RA;
import uga.cs4370.mydb.Relation;


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
        rowOne.add(new Cell(1111));
        rowOne.add(new Cell("Deven"));
        rowOne.add(new Cell("Allen"));
        rowOne.add(new Cell("2002-03-05"));
        rowOne.add(new Cell("Computer Science"));

        mydb.getRelations().get(0).insert(rowOne);

        List<Cell> rowTwo = new ArrayList<Cell>();
        rowTwo.add(new Cell(1234));
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

        mydb.getRelationByName("Courses").insert(rowThree);

        List<Cell> rowFour = new ArrayList<Cell>();
        rowFour.add(new Cell(102));
        rowFour.add(new Cell("Database Management"));
        rowFour.add(new Cell(4));
 
        mydb.getRelationByName("Courses").insert(rowFour);

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

        List<Cell> rowSeven = new ArrayList<Cell>();
        rowSeven.add(new Cell(105));
        rowSeven.add(new Cell("Introduction to Programming"));
        rowSeven.add(new Cell(4));
 
        mydb.getRelationByName("Classes").insert(rowSeven);

        //Predicate p0 = new PredicateImpl("StudentID=\"1111\"", mydb.getRelationByName("Students").getAttrs());
        //Predicate p1 = new PredicateImpl("StudentID=\"1234\"", mydb.getRelationByName("Students").getAttrs());
        RA relationalAlgebra = new RAImpl();

        List<String> projectedAttributes = new ArrayList<String>();
        projectedAttributes.addAll(Arrays.asList("FName", "LName", "DoB"));


        List<String> projectedAttributes1 = new ArrayList<String>();
        projectedAttributes1.addAll(Arrays.asList("CName"));

        List<String> originalAttributes = new ArrayList<String>();
        originalAttributes.addAll(Arrays.asList("CName", "Credits"));

        List<String> renamedAttributes = new ArrayList<String>();
        renamedAttributes.addAll(Arrays.asList("Course Name", "Hours"));

        //mydb.getRelationByName("Students").print();
        //mydb.getRelationByName("Courses").print();
        //mydb.getRelationByName("Classes").print();

        //relationalAlgebra.select(mydb.getRelationByName("Students"), p0).print();
        //relationalAlgebra.select(mydb.getRelationByName("Students"), p1).print();

        //relationalAlgebra.project(mydb.getRelationByName("Students"), projectedAttributes).print();

        //Relation relation1 = relationalAlgebra.project(mydb.getRelationByName("Courses"), projectedAttributes1);
        //Relation relation2 = relationalAlgebra.project(mydb.getRelationByName("Classes"), projectedAttributes1);

        //relationalAlgebra.union(mydb.getRelationByName("Courses"), mydb.getRelationByName("Classes")).print();

        //relationalAlgebra.diff(relation1, relation2).print();

        //relationalAlgebra.rename(mydb.getRelationByName("Courses"), originalAttributes, renamedAttributes).print();

        //relationalAlgebra.cartesianProduct(mydb.getRelationByName("Classes"), mydb.getRelationByName("Students")).print();

        //relationalAlgebra.join(mydb.getRelationByName("Students"), mydb.getRelationByName("Courses")).print();
                /* Creating Teaches Table */
                ArrayList<String> attributesFive = new ArrayList<String>();
                ArrayList<Type> typesFive = new ArrayList<Type>();
                ArrayList<String> primaryKeysFive = new ArrayList<String>();
                ArrayList<String> foreignKeysFive = new ArrayList<String>();
        
                String teachesName = "Teaches";
                attributesFive.addAll(Arrays.asList("TeachID", "ProfessorID", "CourseID"));
                typesFive.addAll(Arrays.asList(Type.INTEGER, Type.INTEGER, Type.INTEGER));
                primaryKeysFive.add("TeachID");
        
                /* adding teaches to database*/
                mydb.addRelation(teachesName, attributesFive, typesFive, primaryKeysFive, foreignKeysFive);
        
                List<Cell> TeachesOne = new ArrayList<Cell>();
                TeachesOne.add(new Cell(301));
                TeachesOne.add(new Cell(201));
                TeachesOne.add(new Cell(101));
        
                mydb.getRelationByName("Teaches").insert(TeachesOne);
        
        
                List<Cell> TeachesTwo = new ArrayList<Cell>();
                TeachesTwo.add(new Cell(302));
                TeachesTwo.add(new Cell(202));
                TeachesTwo.add(new Cell(102));
        
                mydb.getRelationByName("Teaches").insert(TeachesTwo);
        
        
                List<Cell> TeachesThree = new ArrayList<Cell>();
                TeachesThree.add(new Cell(303));
                TeachesThree.add(new Cell(203));
                TeachesThree.add(new Cell(101));
        
                mydb.getRelationByName("Teaches").insert(TeachesThree);

        /* Creating Enrollment Table */
        ArrayList<String> attributesSix = new ArrayList<String>();
        ArrayList<Type> typesSix = new ArrayList<Type>();
        ArrayList<String> primaryKeysSix = new ArrayList<String>();
        ArrayList<String> foreignKeysSix = new ArrayList<String>();

        String enrollmentName = "Enrollment";
        attributesSix.addAll(Arrays.asList("EnrollmentID", "StudentID", "CourseID", "Grade"));
        typesSix.addAll(Arrays.asList(Type.INTEGER, Type.INTEGER, Type.INTEGER, Type.STRING));
        primaryKeysSix.add("EnrollmentID");

        /* adding enrollment to database*/
        mydb.addRelation(enrollmentName, attributesSix, typesSix, primaryKeysSix, foreignKeysSix);

        /* adding enrollment values */
        List<Cell> EnrollmentOne = new ArrayList<Cell>();
        EnrollmentOne.add(new Cell(1));
        EnrollmentOne.add(new Cell(1234));
        EnrollmentOne.add(new Cell(101));
        EnrollmentOne.add(new Cell("A"));

        mydb.getRelationByName("Enrollment").insert(EnrollmentOne);

        List<Cell> EnrollmentTwo = new ArrayList<Cell>();
        EnrollmentTwo.add(new Cell(2));
        EnrollmentTwo.add(new Cell(1234));
        EnrollmentTwo.add(new Cell(102));
        EnrollmentTwo.add(new Cell("B"));
 
        mydb.getRelationByName("Enrollment").insert(EnrollmentTwo);


        List<Cell> EnrollmentThree = new ArrayList<Cell>();
        EnrollmentThree.add(new Cell(3));
        EnrollmentThree.add(new Cell(1111));
        EnrollmentThree.add(new Cell(101));
        EnrollmentThree.add(new Cell("B"));

        mydb.getRelationByName("Enrollment").insert(EnrollmentThree);


        List<Cell> EnrollmentFour = new ArrayList<Cell>();
        EnrollmentFour.add(new Cell(4));
        EnrollmentFour.add(new Cell(1111));
        EnrollmentFour.add(new Cell(102));
        EnrollmentFour.add(new Cell("C"));
 
        mydb.getRelationByName("Enrollment").insert(EnrollmentFour);


        List<Cell> EnrollmentFive = new ArrayList<Cell>();
        EnrollmentFive.add(new Cell(5));
        EnrollmentFive.add(new Cell(1111));
        EnrollmentFive.add(new Cell(103));
        EnrollmentFive.add(new Cell("A"));
 
        mydb.getRelationByName("Enrollment").insert(EnrollmentFive);

        //mydb.getRelationByName("Enrollment").print();
        mydb.getRelationByName("Enrollment").print();

        //Predicate p3 = new PredicateImpl(mydb.getRelationByName("Enrollment"), mydb.getRelationByName("Courses"), "CourseID=CourseID");
        //relationalAlgebra.join(mydb.getRelationByName("Enrollment"), mydb.getRelationByName("Teaches"), p3).print();
        //relationalAlgebra.join(mydb.getRelationByName("Enrollment"), mydb.getRelationByName("Courses")).print();

        Predicate p4 = new PredicateImpl("StudentID=\"1111\"", mydb.getRelationByName("Enrollment").getAttrs());
        relationalAlgebra.select(mydb.getRelationByName("Enrollment"), p4).print();
    }
}
