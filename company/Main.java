package com.company;

import java.util.*;
import java.util.stream.Collectors;

import static com.company.Education.HIGHER;
import static com.company.Sex.MAN;
import static com.company.Sex.WOMAN;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long count = persons.stream()
                .filter(age -> age.getAge() < 18)
                .count();
        System.out.println("количество несовершеннолетних " + count);

        List<String> recruit = persons.stream()
                .filter(sex -> sex.getSex().equals(MAN))
                .filter(age -> age.getAge() >= 18 && age.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
//      System.out.println(recruit);
        System.out.println("количество призывников " + recruit.size());

        List<String> workersMan = persons.stream()
                .filter(education -> education.getEducation().equals(HIGHER))
                .filter(sex -> sex.getSex().equals(MAN))
                .filter(age -> age.getAge() >= 18 && age.getAge() < 65)
                .map(Person::getFamily)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println("количество работоспособных мужчин " + workersMan.size());
        System.out.println(workersMan);

        List<String> workersWoman = persons.stream()
                .filter(education -> education.getEducation().equals(HIGHER))
                .filter(sex -> sex.getSex().equals(WOMAN))
                .filter(age -> age.getAge() >= 18 && age.getAge() < 60)
                .map(Person::getFamily)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println("количество работоспособных женщин " + workersWoman.size());
        System.out.println(workersWoman);
    }
}
