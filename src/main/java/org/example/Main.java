package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Student santiago = new Student("Santiago", "Calculo", List.of(5,8,9,5,7,8,9,5));
        Student pablo = new Student("pablo", "fisica", List.of(6,5,9,10,7,6));
        Student valentina = new Student("valentina", "Calculo", List.of(8,9,7,9,10,7));
        Student leonardo = new Student("leonardo", "redes", List.of(5,8,9,7,6,9,10,10,10,7));
        Student juan = new Student("juan", "fisica", List.of(9,8,7,9,8,9,8,10,5));
        Student melissa = new Student("melissa", "redes", List.of(10,9,8,7,10,5,5,8,10));

        List<Student> students = new ArrayList<>(List.of(santiago, pablo, valentina, leonardo, juan, melissa));


        Set<Student> result1 = completeInformation(students);
        System.out.println("***Estudiantes completos***");
        result1.forEach(System.out::println);

        Set<Student> studentsThatPassed = filterStudentsThatPassed(result1);
        System.out.println("***Estudiantes que pasaron su curso***");
        studentsThatPassed.forEach(System.out::println);

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
        return students.stream().filter(student -> student.getAverage() > 7.5).collect(Collectors.toSet());
    }


    /**
     * Crea una funcion que obtenga el número de estudiantes que pasaron
     * */
    private static int getNumberOfStudentsThatPassed(Set<Student> students) {
        return (int) students.stream().filter(student -> student.getAverage() > 7.5).count();
    }

}