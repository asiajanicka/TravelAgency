package org.jjm.trip;

import java.util.Objects;

public class Person {
    private static int counter = 0;
    private int id;
    private String firstName;
    private String lastName;
    private int age;

    public Person() {
        this.id = ++counter;
    }

    public Person(String firstName, String lastName, int age) {
        this.id = ++counter;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("%s %s (%d) age: %d", firstName, lastName, id, age);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        if (this.hashCode() != o.hashCode()) return false;
        Person p = (Person) o;
        boolean idEquals = this.id == p.id;
        boolean firstNameEquals = (this.firstName == null && p.firstName == null)
                || (this.firstName != null && this.firstName.equals(p.firstName));
        boolean lastNameEquals = (this.lastName == null && p.lastName == null)
                || (this.lastName != null && this.lastName.equals(p.lastName));
        boolean ageEquals = this.age == p.age;
        return idEquals && firstNameEquals && lastNameEquals && ageEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age);
    }
}
