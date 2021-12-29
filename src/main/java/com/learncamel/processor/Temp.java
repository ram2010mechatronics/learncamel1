package com.learncamel.processor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Temp {

    static class Person {

        private String name;
        private int age;
        private long salary;

        Person(String name, int age, long salary) {

            this.name = name;
            this.age = age;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return String.format("Person{name='%s', age=%d, salary=%d}", name, age, salary);
        }
    }

    public static void main(String[] args) {
        Stream<Person> people = Stream.of(new Person("Paul", 24, 20000),
                new Person("Mark", 30, 30000),
                new Person("Will", 28, 28000),
                new Person("William", 28, 28000));
        Map<Integer, List<Person>> peopleByAge;
        peopleByAge = people
                .collect(Collectors.groupingBy(p -> p.age, Collectors.mapping((Person p) -> {
                    return p;
                }, toList())));
        System.out.println(peopleByAge);
    }
}
