package uga.cs4370.mydb.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import uga.cs4370.mydb.Cell;
import uga.cs4370.mydb.Type;
import uga.cs4370.mydb.Predicate;
import uga.cs4370.mydb.RA;
import uga.cs4370.mydb.Relation;


public class Main {
    public static void main(String[] args) {
        Database mydb = new Database("Rigged Data Group Database");

        /* ASSIGNMENT ONE SCHEMA CREATION PLUS ADDING VALUES */
        
        /* Creating Students Table 
         * Corresponds to the following query: 
         * CREATE TABLE Students (
         * StudentID INT PRIMARY KEY,
         * FName VARCHAR(255),
         * LName VARCHAR(255),
         * DoB DATE,
         * Major VARCHAR(255)
         * );
        */
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

        /* adding students: Associated with the following query:
         * INSERT INTO Students (StudentID, FName, LName, DoB, Major) 
         * VALUES (1234, 'Deven', 'Allen', '2002-03-05', 'Computer Science'), 
         * (1111, 'John', 'Doe', '1999-05-22', 'Computer Science'), 
         * (2222, 'Jane', 'Smith', '2000-06-15', 'Electrical Engineering'),
         * (3333, 'Sarah', 'Johnson', '1995-03-20', 'Physics'),
         * (4444, 'Mike', 'Carl', '1999-04-25', 'Computer Science');
        */
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


        /* Creating Courses Table:
         * Corresponds to the following query:
         * CREATE TABLE Courses (
         * CourseID INT PRIMARY KEY,
         * CName VARCHAR(255),
         * Credits INT
         * );
         */
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
        
        /* Adding Courses 
         * Corresponds to the following query:
         * INSERT INTO Courses (CourseID, CName, Credits)
         *  VALUES
         *  (101, 'Introduction to Programming', 3),
         *  (102, 'Database Management', 4),
         *  (103, 'PE', 1);
        */
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

        /* Creating Enrollment Table:
         * Corresponds to the following query:
         * CREATE TABLE Enrollment (
         * EnrollmentID INT PRIMARY KEY,
         * StudentID INT,
         * CourseID INT,
         * grade VARCHAR(2),
         * FOREIGN KEY (StudentID) REFERENCES Students(StudentID),
         * FOREIGN KEY (CourseID) REFERENCES Courses(CourseID)
         * );
        */
        ArrayList<String> attributesThree = new ArrayList<String>();
        ArrayList<Type> typesThree = new ArrayList<Type>();
        ArrayList<String> primaryKeysThree = new ArrayList<String>();
        ArrayList<String> foreignKeysThree = new ArrayList<String>();

        String enrollmentName = "Enrollment";
        attributesThree.addAll(Arrays.asList("EnrollmentID", "StudentID", "CourseID", "Grade"));
        typesThree.addAll(Arrays.asList(Type.INTEGER, Type.INTEGER, Type.INTEGER, Type.STRING));
        primaryKeysThree.add("EnrollmentID");
        foreignKeysThree.add("StudentID");
        foreignKeysThree.add("CourseID");


        /* adding enrollment to database*/
        mydb.addRelation(enrollmentName, attributesThree, typesThree, primaryKeysThree, foreignKeysThree);
    
        /* adding enrollment values 
         * Corresponds to the following query:
         * INSERT INTO Enrollment (EnrollmentID, StudentID, CourseID, grade) 
         * VALUES 
         * (1, 1234, 101, 'A'), 
         * (2, 1234, 102, 'B'), 
         * (3, 1111, 101, 'B'), 
         * (4, 1111, 102, 'C'), 
         * (5, 1111, 103, 'A'),
         * (6, 4444, 101, 'F');
        */
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
 
        mydb.getRelationByName("Enrollment").insert(EnrollmentSix);


        /* Creating Professors Table 
         * Corresponds to the following query:
         * CREATE TABLE Professors (
         * ProfessorID INT PRIMARY KEY,
         * FName VARCHAR(255),
         * LName VARCHAR(255),
         * department VARCHAR(255)
         * );
        */
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

        /* adding professor values 
         * Corresponds to the following query:
         * INSERT INTO Professors (ProfessorID, FName, LName, department) 
         * VALUES
         * (201, 'Carl', 'Smith', 'Computer Science'), 
         * (202, 'Steve', 'Allen', 'Electrical Engineering'), 
         * (203, 'Matthew', 'Mack', 'Computer Science');
        */
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



        /* Creating Teaches Table 
         * Corresponds to the following query:
         * CREATE TABLE Teaches (
         * TeachID INT PRIMARY KEY,
         * ProfessorID INT,
         * CourseID INT,
         * FOREIGN KEY (ProfessorID) REFERENCES Professors(ProfessorID),
         * FOREIGN KEY (CourseID) REFERENCES Courses(CourseID)
         * );
        */
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

        /* adding teaches values
         * Corresponds to the following query:
         * INSERT INTO Teaches (TeachID, ProfessorID, CourseID) 
         * VALUES 
         * (301, 201, 101), 
         * (302, 202, 102), 
         * (303, 203, 101);
        */
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


        RA relationalAlgebra = new RAImpl();

        /* IMPLEMENTING QUERIES FROM ASSIGNMENT 1 */

        /* Question 1
         * Corresponds to the following RA statement:
         * πCourseID(σStudentID=”1234”(Enrollment))
         */
        Predicate p1 = new PredicateImpl("StudentID=\"1234\"", mydb.getRelationByName("Enrollment").getAttrs());
        List<String> q1Attrs = new ArrayList<String>();
        q1Attrs.addAll(Arrays.asList("CourseID"));
        System.out.println("Question 1 Query result:");
        relationalAlgebra.project(relationalAlgebra.select(mydb.getRelationByName("Enrollment"), p1), q1Attrs).print();

        /* Question 2
         * Corresponds to the following RA statement:
         * πFName,LName,StudentID(σMajor=”Computer Science”(Students))
         */
        Predicate p2 = new PredicateImpl("Major=\"Computer Science\"", mydb.getRelationByName("Students").getAttrs());
        List<String> q2Attrs = new ArrayList<String>();
        q2Attrs.addAll(Arrays.asList("FName", "LName", "StudentID"));
        System.out.println("Question 2 Query result:");
        relationalAlgebra.project(relationalAlgebra.select(mydb.getRelationByName("Students"), p2), q2Attrs).print();

        /* Question 3
         * Corresponds to the following RA statement:
         * πCName(σStudentID=”1234”(Enrollment⨝Courses))
         */
        Relation R3 = relationalAlgebra.join(mydb.getRelationByName("Enrollment"), mydb.getRelationByName("Courses"));
        Predicate p3 = new PredicateImpl("StudentID=\"1234\"", ((RelationImpl)R3).getAttrs());
        List<String> q3Attrs = new ArrayList<String>();
        q3Attrs.addAll(Arrays.asList("CName"));
        System.out.println("Question 3 Query result:");
        relationalAlgebra.project(relationalAlgebra.select(R3, p3), q3Attrs).print();

        /* Question 4
         * Corresponds to the following RA statement:
         * πFName,LName,ProfessorID(σCredits>2(Teaches⨝Professors⨝Courses))
         */
        Relation R4_1 = relationalAlgebra.join(mydb.getRelationByName("Teaches"), mydb.getRelationByName("Professors"));
        Relation R4 = relationalAlgebra.join(mydb.getRelationByName("Courses"),R4_1);
        Predicate p4 = new PredicateImpl("Credits>\"2\"", ((RelationImpl)R4).getAttrs());
        List<String> q4Attrs = new ArrayList<String>();
        q4Attrs.addAll(Arrays.asList("FName", "LName", "ProfessorID"));
        System.out.println("Question 4 Query result:");
        relationalAlgebra.project(relationalAlgebra.select(R4, p4), q4Attrs).print();

        
        /* Question 5
         * Corresponds to the following RA statement:
         * πFName,LName,StudentID(Students) - πFName,LName,StudentID(Students⨝Enrollment)
         */
        Relation R5_1 = relationalAlgebra.join(mydb.getRelationByName("Students"), mydb.getRelationByName("Enrollment"));
        List<String> q5Attrs = new ArrayList<String>();
        q5Attrs.addAll(Arrays.asList("FName", "LName", "StudentID"));
        Relation R5_Proj1 = relationalAlgebra.project(mydb.getRelationByName("Students"), q5Attrs);
        Relation R5_Proj2 = relationalAlgebra.project(R5_1, q5Attrs);
        System.out.println("Question 5 Query result:");
        relationalAlgebra.diff(R5_Proj1, R5_Proj2).print();


        /* Question 6
         * Corresponds to the following RA statement:
         * πCourseID, CName(Courses) - πCourseID, CName(Courses⨝Teaches)
         */
        Relation R6_1 = relationalAlgebra.join(mydb.getRelationByName("Courses"), mydb.getRelationByName("Teaches"));
        List<String> q6Attrs = new ArrayList<String>();
        q6Attrs.addAll(Arrays.asList("CourseID", "CName"));
        Relation R6_Proj1 = relationalAlgebra.project(mydb.getRelationByName("Courses"), q6Attrs);
        Relation R6_Proj2 = relationalAlgebra.project(R6_1, q6Attrs);
        System.out.println("Question 6 Query result:");
        relationalAlgebra.diff(R6_Proj1, R6_Proj2).print();

        /* Question 7
         * Corresponds to the following RA statement:
         * πFName,LName,StudentID(σMajor=”Computer Science” ^ σgrade=”F” (Enrollment⨝Students))
         */
        List<String> q7Attrs = new ArrayList<String>();
        q7Attrs.addAll(Arrays.asList("FName", "LName", "StudentID"));
        Relation R7_1 = relationalAlgebra.join(mydb.getRelationByName("Enrollment"), mydb.getRelationByName("Students"));
        Predicate p7_1 = new PredicateImpl("Grade=\"F\"", ((RelationImpl)R7_1).getAttrs());
        Relation R7_2 = relationalAlgebra.select(R7_1, p7_1);
        Predicate p7_2 = new PredicateImpl("Major=\"Computer Science\"", ((RelationImpl)R7_2).getAttrs());
        Relation R7_3 = relationalAlgebra.select(R7_2, p7_2);
        System.out.println("Question 7 Query result:");
        relationalAlgebra.project(R7_3, q7Attrs).print();

        /* Question 8
         * Corresponds to the following RA statement:
         * πProfessors.FName,Professors.LName,Professors.ProfessorID (σMajor=”Computer Science”(Students ⨝ Enrollment ⨝ Teaches ⨝ Professors))
         */
        List<String> q8Attrs = new ArrayList<String>();
        mydb.getRelationByName("Professors").print();
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
        Relation R8_6 = relationalAlgebra.project(R8_5, q8Attrs_2);
        System.out.println("Question 8 Query result:");
        ((RAImpl)relationalAlgebra).distinct(R8_6, q8Attrs_2).print();


        System.out.println("Meaningful Queries:");
        
        // Select
        System.out.println("Selecting the students who have the last name Doe");
        Predicate selectTest = new PredicateImpl("LName=\"Doe\"", mydb.getRelationByName("Students").getAttrs());
        relationalAlgebra.select(mydb.getRelationByName("Students"), selectTest).print();

        // Project
        System.out.println("Projecting the list of professors:");
        relationalAlgebra.project(mydb.getRelationByName("Professors"), mydb.getRelationByName("Professors").getAttrs()).print();

        // Union
        System.out.println("Union between courses and classes:");
        /* adding classes */
        ArrayList<String> attributesExtra = new ArrayList<String>();
        ArrayList<Type> typesExtra = new ArrayList<Type>();
        ArrayList<String> primaryKeysExtra = new ArrayList<String>();
        ArrayList<String> foreignKeysExtra = new ArrayList<String>();

        String className = "Classes";
        attributesExtra.addAll(Arrays.asList("ClassID", "CName", "Credits"));
        typesExtra.addAll(Arrays.asList(Type.INTEGER, Type.STRING, Type.INTEGER));
        primaryKeysExtra.add("ClassID");
        mydb.addRelation(className, attributesExtra, typesExtra, primaryKeysExtra, foreignKeysExtra);

        List<Cell> ClassOne = new ArrayList<Cell>();
        ClassOne.add(new Cell(500));
        ClassOne.add(new Cell("Software Engineering"));
        ClassOne.add(new Cell(3));

        mydb.getRelationByName("Classes").insert(ClassOne);


        List<Cell> ClassTwo = new ArrayList<Cell>();
        ClassTwo.add(new Cell(501));
        ClassTwo.add(new Cell("Data Structures"));
        ClassTwo.add(new Cell(4));
 
        mydb.getRelationByName("Classes").insert(ClassTwo);

        relationalAlgebra.union(mydb.getRelationByName("Courses"), mydb.getRelationByName("Classes")).print();
        
        // Diff
        System.out.println("Diff between courses and classes: (Minus Software Engineering)");

        List<Cell> CourseThree = new ArrayList<Cell>();
        CourseThree.add(new Cell(500));
        CourseThree.add(new Cell("Software Engineering"));
        CourseThree.add(new Cell(3));

        mydb.getRelationByName("Courses").insert(CourseThree);

        relationalAlgebra.diff(mydb.getRelationByName("Courses"), mydb.getRelationByName("Classes")).print();
        
        // Rename
        System.out.println("Change the name of courses to Classes:");
        ArrayList<String> newAttributes = new ArrayList<String>();
        newAttributes.addAll(Arrays.asList("ClassID", "Class Name", "Total Credits"));
        mydb.getRelationByName("Courses").print();
        relationalAlgebra.rename(mydb.getRelationByName("Courses"), attributesTwo, newAttributes).print();

        // Cartesian Product
        System.out.println("Cartesian Product between Students and Courses:");
        relationalAlgebra.cartesianProduct(mydb.getRelationByName("Students"), mydb.getRelationByName("Courses")).print();


        // join
        System.out.println("Theta joining all Professors with the classes they teach:");
        Predicate join2 = new PredicateImpl(mydb.getRelationByName("Professors"), mydb.getRelationByName("Teaches"), "ProfessorID=ProfessorID");
        relationalAlgebra.join(mydb.getRelationByName("Professors"), mydb.getRelationByName("Teaches"), join2).print();

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
        // basic natural loop join
        //System.out.println("Joining all students and the courses they are enrolled in");
        System.out.println("Testing non-indexed vs indexed tables with 10 join operations\n");
        System.out.println("Testing small table performance (5 rows)");
        System.out.println("--------------------------------------------");
        System.out.println("Running short basic natural loop join");
        long shortNonIndexedStarts[] = new long[10];
        long shortNonIndexedEnds[] = new long[10];

        for (int i = 0; i < 10; i++) {
            shortNonIndexedStarts[i] = System.nanoTime();
            relationalAlgebra.join(mydb.getRelationByName("Students"), mydb.getRelationByName("Enrollment"));
            shortNonIndexedEnds[i] = System.nanoTime();
        }

        // indexed natural loop join
        System.out.println("Running short indexed natural loop join\n");
        long shortIndexedStarts[] = new long[10];
        long shortIndexedEnds[] = new long[10];

        mydb.getRelationByName("Enrollment").index("StudentID");
        for (int i = 0; i < 10; i++) {
            shortIndexedStarts[i] = System.nanoTime();
            relationalAlgebra.join(mydb.getRelationByName("Students"), mydb.getRelationByName("Enrollment"));
            shortIndexedEnds[i] = System.nanoTime();
        }

        for (int i = 1000; i < 2000; i++) {
            if (i != 1234 && i != 1111 && i != 2222 && i != 3333 && i != 4444) { 
                createRow(mydb, "Students", i, "Bob", "Ross", "2002-04-09", "Art");
            }
        }

        Random rand = new Random();
        for (int i = 150; i < 400; i++) {
            createRow(mydb, "Enrollment", i, 1000 + rand.nextInt(1001), 101, "A");
        }

        try {
            System.out.println("Testing large table performance in 5 seconds.");
            TimeUnit.SECONDS.sleep(5);
        } catch(Exception e) {}

        System.out.println("\nTesting large table performance (1000 rows)");
        System.out.println("--------------------------------------------");
        long longNonIndexedStarts[] = new long[10];
        long longNonIndexedEnds[] = new long[10];

        mydb.getRelationByName("Enrollment").removeIndex("StudentID");

        System.out.println("Running long basic natural loop join");
        for (int i = 0; i < 10; i++) {
            longNonIndexedStarts[i] = System.nanoTime();
            relationalAlgebra.join(mydb.getRelationByName("Students"), mydb.getRelationByName("Enrollment"));
            longNonIndexedEnds[i] = System.nanoTime();
        }

        mydb.getRelationByName("Enrollment").index("StudentID");
        long longIndexedStarts[] = new long[10];
        long longIndexedEnds[] = new long[10];

        System.out.println("Running long indexed natural loop join\n");
        for (int i = 0; i < 10; i++) {
            longIndexedStarts[i] = System.nanoTime();
            relationalAlgebra.join(mydb.getRelationByName("Students"), mydb.getRelationByName("Enrollment"));
            longIndexedEnds[i] = System.nanoTime();
        }
        
        double minShortNonIndexed = Double.MAX_VALUE;
        double maxShortNonIndexed = 0;
        double averageShortNonIndexed = 0;

        double minShortIndexed = Double.MAX_VALUE;
        double maxShortIndexed = 0;
        double averageShortIndexed = 0;

        double minLongNonIndexed = Double.MAX_VALUE;
        double maxLongNonIndexed = 0;
        double averageLongNonIndexed = 0;

        double minLongIndexed = Double.MAX_VALUE;
        double maxLongIndexed = 0;
        double averageLongIndexed = 0;

        for (int i = 0; i < 10; i++) {
            long shortNonIndexed = shortNonIndexedEnds[i] - shortNonIndexedStarts[i];
            minShortNonIndexed = Math.min(minShortNonIndexed, shortNonIndexed); 
            maxShortNonIndexed = Math.max(maxShortNonIndexed, shortNonIndexed); 
            averageShortNonIndexed += shortNonIndexed;

            long shortIndexed = shortIndexedEnds[i] - shortIndexedStarts[i];
            minShortIndexed = Math.min(minShortIndexed, shortIndexed); 
            maxShortIndexed = Math.max(maxShortIndexed, shortIndexed); 
            averageShortIndexed += shortIndexed;

            long longNonIndexed = longNonIndexedEnds[i] - longNonIndexedStarts[i];
            minLongNonIndexed = Math.min(minLongNonIndexed, longNonIndexed); 
            maxLongNonIndexed = Math.max(maxLongNonIndexed, longNonIndexed); 
            averageLongNonIndexed += longNonIndexed;

            long longIndexed = longIndexedEnds[i] - longIndexedStarts[i];
            minLongIndexed = Math.min(minLongIndexed, longIndexed); 
            maxLongIndexed = Math.max(maxLongIndexed, longIndexed); 
            averageLongIndexed += longIndexed;
        }

        System.out.println("Performance Report");
        System.out.println("--------------------------------------------\n");

        System.out.println("Short Non-Indexed Join");
        System.out.println("--------------------------------------------");
        System.out.printf("Minimum Time: %.2E ns\n", (double) minShortNonIndexed);
        System.out.printf("Maximum Time: %.2E ns\n", (double) maxShortNonIndexed);
        System.out.printf("Average Time: %.2E ns\n", (double) averageShortNonIndexed / 10.0);
        System.out.println();
        
        System.out.println("Short Indexed Join");
        System.out.println("--------------------------------------------");
        System.out.printf("Minimum Time: %.2E ns\n", (double) minShortIndexed);
        System.out.printf("Maximum Time: %.2E ns\n", (double) maxShortIndexed);
        System.out.printf("Average Time: %.2E ns\n", (double) averageShortIndexed / 10.0);
        System.out.println();
        
        System.out.println("Long Non-Indexed Join");
        System.out.println("--------------------------------------------");
        System.out.printf("Minimum Time: %.2E ns\n", (double) minLongNonIndexed);
        System.out.printf("Maximum Time: %.2E ns\n", (double) maxLongNonIndexed);
        System.out.printf("Average Time: %.2E ns\n", (double) averageLongNonIndexed / 10.0);
        System.out.println();
        
        System.out.println("Long Indexed Join");
        System.out.println("--------------------------------------------");
        System.out.printf("Minimum Time: %.2E ns\n", (double) minLongIndexed);
        System.out.printf("Maximum Time: %.2E ns\n", (double) maxLongIndexed);
        System.out.printf("Average Time: %.2E ns\n", (double) averageLongIndexed / 10.0);
        
    }

    private static void createRow(Database database, String tableName, Object... values) {
        Relation table = database.getRelationByName(tableName);
        List<Cell> row = new ArrayList<>();
        for (Object value : values) {
            if (value instanceof String) {
                row.add(new Cell((String)value));
            } else if (value instanceof Integer) {
                row.add(new Cell((int)value));
            } else if (value instanceof Double) {
                row.add(new Cell((double)value));
            }
        }
        table.insert(row);
    }
}
