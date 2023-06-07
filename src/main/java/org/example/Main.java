package org.example;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Student> students = MainWithFunctionalInterfaces.studentsSupplier.get();


        Set<Student> result1 = MainWithFunctionalInterfaces.studentsAutoComplete.apply(students);
        System.out.println("***Estudiantes completos***");
        result1.forEach(MainWithFunctionalInterfaces.studentPrinter);

        Set<Student> studentsThatPassed = filterStudentsThatPassed(result1);
        System.out.println("***Estudiantes que pasaron su curso***");
        studentsThatPassed.forEach(MainWithFunctionalInterfaces.studentPrinter);

        int numberOfStudentsThatPassed = getNumberOfStudentsThatPassed(studentsThatPassed);
        System.out.println("El numero de estudiantes que pasaron su curso fueron: "+ numberOfStudentsThatPassed);



    }



    /**
     * Recorre la lista i agrega los atributos que no están como parámetro del constructor que son:
     * promedio(average) y número de notas (numberOfGrades)
     * */
    private static Set<Student> completeInformation(List<Student> students) {
        return students.stream().map(student -> {
            Student newStudent = new Student(student.getName(), student.getCourse(), student.getGrades());
            int numberOfGrades = student.getGrades().size();
            double average = student.getGrades().stream().reduce(0, Integer::sum).doubleValue() / numberOfGrades;
            newStudent.setAverage(average);
            newStudent.setNumberOfGrades(numberOfGrades);
            return newStudent;
        }).collect(Collectors.toSet());
    }

    /**
     * Una vez completada la información de los estudiantes genere una lista de los que han superado su curso,
     * se supera el curso con una nota mayor a 7.5
     * */
    private static Set<Student> filterStudentsThatPassed(Set<Student> students){
        return students.stream().filter(MainWithFunctionalInterfaces.gradeValidator).collect(Collectors.toSet());
    }


    /**
     * Crea una funcion que obtenga el número de estudiantes que pasaron
     * */
    private static int getNumberOfStudentsThatPassed(Set<Student> students) {
        return (int) students.stream().filter(MainWithFunctionalInterfaces.gradeValidator).count();
    }

}