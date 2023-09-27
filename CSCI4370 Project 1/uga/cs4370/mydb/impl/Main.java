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
        Database mydb = new Database("Rigged Data Group Database");

        /* ASSIGNMENT ONE SCHEMA CREATION PLUS ADDING VALUES */
        
        /* Creating Students Table */
        ArrayList<String> attributesOne = new ArrayList<String>();
        ArrayList<Type> typesOne = new ArrayList<Type>();
        ArrayList<String> primaryKeysOne = new ArrayList<String>();
        ArrayList<String> foreignKeysOne = new ArrayList<String>();

        String studentsName = "Students";
        attributesOne.addAll(Arrays.asList("StudentID", "FName", "LName", "DoB", "Major"));
        typesOne.addAll(Arrays.asList(Type.INTEGER, Type.STRING, Type.STRING, Type.STRING, Type.STRING));
        primaryKeysOne.add("StudentID");
 
        /* Adding Students to database*/
        mydb.addRelation(studentsName, attributesOne, typesOne, primaryKeysOne, foreignKeysOne);

        /* adding students */
        List<Cell> studentOne = new ArrayList<Cell>();
        studentOne.add(new Cell(1234));
        studentOne.add(new Cell("Deven"));
        studentOne.add(new Cell("Allen"));
        studentOne.add(new Cell("2002-03-05"));
        studentOne.add(new Cell("Computer Science"));

        mydb.getRelations().get(0).insert(studentOne);


        List<Cell> StudentTwo = new ArrayList<Cell>();
        StudentTwo.add(new Cell(1111));
        StudentTwo.add(new Cell("John"));
        StudentTwo.add(new Cell("Doe"));
        StudentTwo.add(new Cell("1999-05-22"));
        StudentTwo.add(new Cell("Computer Science"));
 
        mydb.getRelations().get(0).insert(StudentTwo);


        List<Cell> StudentThree = new ArrayList<Cell>();
        StudentThree.add(new Cell(2222));
        StudentThree.add(new Cell("Jane"));
        StudentThree.add(new Cell("Smith"));
        StudentThree.add(new Cell("2000-06-15"));
        StudentThree.add(new Cell("Electrical Engineering"));
 
        mydb.getRelations().get(0).insert(StudentThree);


        List<Cell> StudentFour = new ArrayList<Cell>();
        StudentFour.add(new Cell(3333));
        StudentFour.add(new Cell("Sarah"));
        StudentFour.add(new Cell("Johnson"));
        StudentFour.add(new Cell("1995-03-20"));
        StudentFour.add(new Cell("Physics"));
 
        mydb.getRelations().get(0).insert(StudentFour);


        List<Cell> StudentFive = new ArrayList<Cell>();
        StudentFive.add(new Cell(4444));
        StudentFive.add(new Cell("Mike"));
        StudentFive.add(new Cell("Carl"));
        StudentFive.add(new Cell("1999-04-25"));
        StudentFive.add(new Cell("Computer Science"));
 
        mydb.getRelations().get(0).insert(StudentFive);


        /* Creating Courses Table */
        ArrayList<String> attributesTwo = new ArrayList<String>();
        ArrayList<Type> typesTwo = new ArrayList<Type>();
        ArrayList<String> primaryKeysTwo = new ArrayList<String>();
        ArrayList<String> foreignKeysTwo = new ArrayList<String>();

        String coursesName = "Courses";
        attributesTwo.addAll(Arrays.asList("CourseID", "CName", "Credits"));
        typesTwo.addAll(Arrays.asList(Type.INTEGER, Type.STRING, Type.INTEGER));
        primaryKeysTwo.add("CourseID");

        /* adding courses to database*/
        mydb.addRelation(coursesName, attributesTwo, typesTwo, primaryKeysTwo, foreignKeysTwo);
        
        /* Adding Courses */
        List<Cell> CourseOne = new ArrayList<Cell>();
        CourseOne.add(new Cell(101));
        CourseOne.add(new Cell("Introduction to Programming"));
        CourseOne.add(new Cell(3));

        mydb.getRelationByName("Courses").insert(CourseOne);


        List<Cell> CourseTwo = new ArrayList<Cell>();
        CourseTwo.add(new Cell(102));
        CourseTwo.add(new Cell("Database Management"));
        CourseTwo.add(new Cell(4));
 
        mydb.getRelationByName("Courses").insert(CourseTwo);


        List<Cell> CoursePE = new ArrayList<Cell>();
        CoursePE.add(new Cell(103));
        CoursePE.add(new Cell("PE"));
        CoursePE.add(new Cell(1));
 
        mydb.getRelationByName("Courses").insert(CoursePE);

        /* 
        List<Cell> CourseThree = new ArrayList<Cell>();
        CourseThree.add(new Cell(103));
        CourseThree.add(new Cell("Software Engineering"));
        CourseThree.add(new Cell(3));

        mydb.getRelationByName("Courses").insert(CourseThree);


        List<Cell> CourseFour = new ArrayList<Cell>();
        CourseFour.add(new Cell(104));
        CourseFour.add(new Cell("Data Structures"));
        CourseFour.add(new Cell(4));
 
        mydb.getRelationByName("Courses").insert(CourseFour);
        */

        /* Creating Enrollment Table */
        ArrayList<String> attributesThree = new ArrayList<String>();
        ArrayList<Type> typesThree = new ArrayList<Type>();
        ArrayList<String> primaryKeysThree = new ArrayList<String>();
        ArrayList<String> foreignKeysThree = new ArrayList<String>();

        String enrollmentName = "Enrollment";
        attributesThree.addAll(Arrays.asList("EnrollmentID", "StudentID", "CourseID", "Grade"));
        typesThree.addAll(Arrays.asList(Type.INTEGER, Type.INTEGER, Type.INTEGER, Type.STRING));
        primaryKeysThree.add("EnrollmentID");

        /* adding enrollment to database*/
        mydb.addRelation(enrollmentName, attributesThree, typesThree, primaryKeysThree, foreignKeysThree);

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


        List<Cell> EnrollmentSix = new ArrayList<Cell>();
        EnrollmentSix.add(new Cell(6));
        EnrollmentSix.add(new Cell(4444));
        EnrollmentSix.add(new Cell(101));
        EnrollmentSix.add(new Cell("F"));
 
        // THIS IS THE LINE WHERE THE OOB ERROR HAPPENS
        mydb.getRelationByName("Enrollment").insert(EnrollmentSix);


        /* Creating Professors Table */
        ArrayList<String> attributesFour = new ArrayList<String>();
        ArrayList<Type> typesFour = new ArrayList<Type>();
        ArrayList<String> primaryKeysFour = new ArrayList<String>();
        ArrayList<String> foreignKeysFour = new ArrayList<String>();

        String professorsName = "Professors";
        attributesFour.addAll(Arrays.asList("ProfessorID", "FName", "LName", "Department"));
        typesFour.addAll(Arrays.asList(Type.INTEGER, Type.STRING, Type.STRING, Type.STRING));
        primaryKeysFour.add("ProfessorID");

        /* adding professors to database*/
        mydb.addRelation(professorsName, attributesFour, typesFour, primaryKeysFour, foreignKeysFour);

        /* adding professor values */
        List<Cell> ProfessorOne = new ArrayList<Cell>();
        ProfessorOne.add(new Cell(201));
        ProfessorOne.add(new Cell("Carl"));
        ProfessorOne.add(new Cell("Smith"));
        ProfessorOne.add(new Cell("Computer Science"));

        mydb.getRelationByName("Professors").insert(ProfessorOne);


        List<Cell> ProfessorTwo = new ArrayList<Cell>();
        ProfessorTwo.add(new Cell(202));
        ProfessorTwo.add(new Cell("Steve"));
        ProfessorTwo.add(new Cell("Allen"));
        ProfessorTwo.add(new Cell("Electrical Engineering"));

        mydb.getRelationByName("Professors").insert(ProfessorTwo);


        List<Cell> ProfessorThree = new ArrayList<Cell>();
        ProfessorThree.add(new Cell(203));
        ProfessorThree.add(new Cell("Matthew"));
        ProfessorThree.add(new Cell("Mack"));
        ProfessorThree.add(new Cell("Computer Science"));

        mydb.getRelationByName("Professors").insert(ProfessorThree);



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

        /* THIS CONCLUDES ADDING ALL THE TABLES AND VALUES FROM ASSIGNMENT 1 */


        /* adding classes??? */
        ArrayList<String> attributesExtra = new ArrayList<String>();
        ArrayList<Type> typesExtra = new ArrayList<Type>();
        ArrayList<String> primaryKeysExtra = new ArrayList<String>();
        ArrayList<String> foreignKeysExtra = new ArrayList<String>();

        String className = "Classes";
        attributesExtra.addAll(Arrays.asList("CourseID", "CName", "Credits"));
        typesExtra.addAll(Arrays.asList(Type.INTEGER, Type.STRING, Type.INTEGER));
        primaryKeysExtra.add("CourseID");
        mydb.addRelation(className, attributesExtra, typesExtra, primaryKeysExtra, foreignKeysExtra);


        RA relationalAlgebra = new RAImpl();

        List<String> projectedAttributes = new ArrayList<String>();
        projectedAttributes.addAll(Arrays.asList("FName", "LName", "DoB"));


        List<String> projectedAttributes1 = new ArrayList<String>();
        projectedAttributes1.addAll(Arrays.asList("CourseID", "CName"));


        /* IMPLEMENTING QUERIES FROM ASSIGNMENT 1 */

        // Question 1
        Predicate p1 = new PredicateImpl("StudentID=\"1234\"", mydb.getRelationByName("Enrollment").getAttrs());
        List<String> q1Attrs = new ArrayList<String>();
        q1Attrs.addAll(Arrays.asList("CourseID"));
        relationalAlgebra.project(relationalAlgebra.select(mydb.getRelationByName("Enrollment"), p1), q1Attrs).print();

        // Question 2
        Predicate p2 = new PredicateImpl("Major=\"Computer Science\"", mydb.getRelationByName("Students").getAttrs());
        List<String> q2Attrs = new ArrayList<String>();
        q2Attrs.addAll(Arrays.asList("FName", "LName", "StudentID"));
        relationalAlgebra.project(relationalAlgebra.select(mydb.getRelationByName("Students"), p2), q2Attrs).print();

        // Question 3
        Relation R3 = relationalAlgebra.join(mydb.getRelationByName("Enrollment"), mydb.getRelationByName("Courses"));
        Predicate p3 = new PredicateImpl("StudentID=\"1234\"", ((RelationImpl)R3).getAttrs());
        List<String> q3Attrs = new ArrayList<String>();
        q3Attrs.addAll(Arrays.asList("CName"));
        relationalAlgebra.project(relationalAlgebra.select(R3, p3), q3Attrs).print();

        // Question 4
        Relation R4_1 = relationalAlgebra.join(mydb.getRelationByName("Teaches"), mydb.getRelationByName("Professors"));
        Relation R4 = relationalAlgebra.join(mydb.getRelationByName("Courses"),R4_1);
        Predicate p4 = new PredicateImpl("Credits>\"2\"", ((RelationImpl)R4).getAttrs());
        List<String> q4Attrs = new ArrayList<String>();
        q4Attrs.addAll(Arrays.asList("FName", "LName", "ProfessorID"));
        relationalAlgebra.project(relationalAlgebra.select(R4, p4), q4Attrs).print();

        
        // Question 5
        Relation R5_1 = relationalAlgebra.join(mydb.getRelationByName("Students"), mydb.getRelationByName("Enrollment"));
        List<String> q5Attrs = new ArrayList<String>();
        q5Attrs.addAll(Arrays.asList("FName", "LName", "StudentID"));
        Relation R5_Proj1 = relationalAlgebra.project(mydb.getRelationByName("Students"), q5Attrs);
        Relation R5_Proj2 = relationalAlgebra.project(R5_1, q5Attrs);
        relationalAlgebra.diff(R5_Proj1, R5_Proj2).print();


        // Question 6
        Relation R6_1 = relationalAlgebra.join(mydb.getRelationByName("Courses"), mydb.getRelationByName("Teaches"));
        List<String> q6Attrs = new ArrayList<String>();
        q6Attrs.addAll(Arrays.asList("CourseID", "CName"));
        Relation R6_Proj1 = relationalAlgebra.project(mydb.getRelationByName("Courses"), q6Attrs);
        Relation R6_Proj2 = relationalAlgebra.project(R6_1, q6Attrs);
        relationalAlgebra.diff(R6_Proj1, R6_Proj2).print();

        // Question 7
        List<String> q7Attrs = new ArrayList<String>();
        q7Attrs.addAll(Arrays.asList("FName", "LName", "StudentID"));
        Relation R7_1 = relationalAlgebra.join(mydb.getRelationByName("Enrollment"), mydb.getRelationByName("Students"));
        Predicate p7_1 = new PredicateImpl("Grade=\"F\"", ((RelationImpl)R7_1).getAttrs());
        Relation R7_2 = relationalAlgebra.select(R7_1, p7_1);
        Predicate p7_2 = new PredicateImpl("Major=\"Computer Science\"", ((RelationImpl)R7_2).getAttrs());
        Relation R7_3 = relationalAlgebra.select(R7_2, p7_2);
        relationalAlgebra.project(R7_3, q7Attrs).print();

        // Question 8
        List<String> q8Attrs = new ArrayList<String>();
        q8Attrs.addAll(Arrays.asList("Professor.ProfessorID", "Professor.FName", "Professor.LName", "Department"));
        Relation R8_rename = relationalAlgebra.rename(mydb.getRelationByName("Professors"), mydb.getRelationByName("Professors").getAttrs(), q8Attrs);
        Relation R8_1 = relationalAlgebra.join(mydb.getRelationByName("Teaches"), R8_rename);
        Relation R8_2 = relationalAlgebra.join(mydb.getRelationByName("Courses"), R8_1);
        Relation R8_3 = relationalAlgebra.join(mydb.getRelationByName("Enrollment"), R8_2);
        Relation R8_4 = relationalAlgebra.join(mydb.getRelationByName("Students"), R8_3);
        Predicate p8_1 = new PredicateImpl("Major=\"Computer Science\"", ((RelationImpl)R8_4).getAttrs());
        Relation R8_5 = relationalAlgebra.select(R8_4, p8_1);
        List<String> q8Attrs_2 = new ArrayList<String>();
        q8Attrs_2.addAll(Arrays.asList("Professor.ProfessorID", "Professor.FName", "Professor.LName"));
        relationalAlgebra.project(R8_5, q8Attrs_2).print();




        //mydb.getRelationByName("Students").print();
        //mydb.getRelationByName("Courses").print();
        //mydb.getRelationByName("Classes").print();
        //mydb.getRelationByName("Enrollment").print();

        //relationalAlgebra.select(mydb.getRelationByName("Students"), p0).print();
        //relationalAlgebra.select(mydb.getRelationByName("Students"), p1).print();

        //relationalAlgebra.project(mydb.getRelationByName("Students"), projectedAttributes).print();

        //relationalAlgebra.project(mydb.getRelationByName("Courses"), projectedAttributes1).print();

        //relationalAlgebra.union(mydb.getRelationByName("Courses"), mydb.getRelationByName("Classes")).print();

        // TOUCH UP THIS
        //relationalAlgebra.diff(mydb.getRelationByName("Students"), mydb.getRelationByName("Courses")).print();

        /* Rename works 
        ArrayList<String> newAttributes = new ArrayList<String>();
        newAttributes.addAll(Arrays.asList("CourseID", "Course Name", "Total Credits"));
        mydb.getRelationByName("Courses").print();
        relationalAlgebra.rename(mydb.getRelationByName("Courses"), attributesTwo, newAttributes).print();
        */


        // SHOULD WORK, BUT DOUBLE CHECK
        //relationalAlgebra.cartesianProduct(mydb.getRelationByName("Students"), mydb.getRelationByName("Courses")).print();


        // WORKING THROUGH THETA JOIN
        //Predicate p2 = new PredicateImpl("StudentID=\"1111\"", mydb.getRelationByName("Students").getAttrs());
        //relationalAlgebra.join(mydb.getRelationByName("Students"), mydb.getRelationByName("Courses"), p2).print();


    }
}
